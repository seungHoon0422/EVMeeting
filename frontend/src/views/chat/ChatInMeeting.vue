<template>
    <div class="chat" id="chat">
        <!-- 메시지 보여주는 부분 -->
        <div class="chat__body" id="chat__body">
          <div
            v-for="(m, idx) in msg"
            :key="idx"
            :m="m"
            :prev="[idx == 0 ? null : msg[idx - 1]]"
          >
            <div v-bind:class="m.style">
              <div v-if="m.senderId == id" class="chat__mymessage">
                <p class="chat__mymessage__paragraph">{{ m.content }}</p>
              </div>
              <div v-else class="chat__yourmessage" style="position:relative; height: 80px; margin-left:-10px ">
                <h5 class="chat__yourmessage__user" style="position:absolute; left:20px; top:0;">
                  {{ m.userId }}
                </h5>
                <div class="chat__yourmessage__p" style="position:absolute; left:20px; top:20px ">
                  <p class="chat__yourmessage__paragraph">{{ m.content }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="form">
        <input
          class="form__input"
          type="text"
          placeholder="메세지를 입력하세요."
          v-model="content"
          @keyup.enter="submitMessage"
        />
        <div @click="submitMessage" class="form__submit">
          <svg
            width="30"
            height="30"
            viewBox="0 0 68 68"
            fill="#CCCCCC"
            xmlns="http://www.w3.org/2000/svg"
          >
            <g clip-path="url(#clip0_26_10)">
              <path
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M48.0833 19.799C48.619 20.3347 48.806 21.127 48.5665 21.8457L35.8385 60.0294C35.5946 60.7614 34.9513 61.2877 34.1855 61.382C33.4198 61.4763 32.6681 61.1217 32.2539 60.4707L22.593 45.2893L7.41158 35.6285C6.76065 35.2142 6.40604 34.4625 6.50031 33.6968C6.59458 32.931 7.12092 32.2878 7.85287 32.0438L46.0366 19.3159C46.7553 19.0763 47.5476 19.2633 48.0833 19.799ZM26.5903 44.1204L33.3726 54.7782L42.0926 28.6181L26.5903 44.1204ZM39.2642 25.7897L23.7619 41.292L13.1041 34.5097L39.2642 25.7897Z"
                fill=""
              />
            </g>
            <defs>
              <clipPath id="clip0_26_10">
                <rect
                  width="48"
                  height="48"
                  fill="white"
                  transform="translate(33.9412) rotate(45)"
                />
              </clipPath>
            </defs>
          </svg>
        </div>
        </div>
    </div>
</template>

<script>
// import axios from 'axios'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import api from '@/api/api'
import { mapGetters, mapActions } from 'vuex'
let preDiffHeight = 0
let bottomFlag = true

export default {
  props: ['roomid'],
  name: 'ChatView',
  data: () => {
    return {
      id: -1,
      name: '',
      idx: 0,
      msg: [],
      content: '',
      stompClient: null,
      photo: null
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn', 'currentUser'])
  },
  mounted () {
    console.log(this.$props.roomid)
  },
  created () {
    this.fetchCurrentUser()
    this.id = this.currentUser.id
    this.name = this.currentUser.username
    this.userId = this.currentUser.userid
    // socket 연결
    const socket = new SockJS(api.chat.connectionSock())
    const options = { debug: false, protocols: Stomp.VERSIONS.supportedProtocols() }
    this.stompClient = Stomp.over(socket, options)
    this.stompClient.connect(
      {},
      frame => {
        console.log('success', frame)
        this.stompClient.subscribe('/sub/' + this.roomid, res => {
          const jsonBody = JSON.parse(res.body)
          const m = {
            senderId: jsonBody.senderId,
            content: jsonBody.content,
            userId: jsonBody.userId,
            style: jsonBody.senderId === this.id ? 'myMsg' : 'otherMsg'
          }
          this.msg.push(m)
        })
      },
      err => {
        console.log('fail', err)
      }
    )
  },
  updated () {
    const objDiv = document.getElementById('chat__body')
    if (bottomFlag) {
      // 채팅창 스크롤 바닥 유지
      objDiv.scrollTop = objDiv.scrollHeight
    }
  },
  methods: {
    ...mapActions(['fetchCurrentUser']),
    submitMessage () {
      if (this.content.trim() !== '' && this.stompClient != null) {
        const chatMessage = {
          content: this.content,
          chatroomId: this.roomid,
          senderId: this.id,
          userId: this.userId,
          id: '0'
        }
        this.stompClient.send('/pub/message', JSON.stringify(chatMessage), {})

        this.content = ''
      }
    },
    chat_on_scroll () {
      document.getElementById('chat__body').scrollTop(0)
      const objDiv = document.getElementById('chat__body')

      if (objDiv.scrollTop + objDiv.clientHeight === objDiv.scrollHeight) {
        // 채팅창 전체높이 + 스크롤높이가 스크롤 전체높이와 같다면
        // 이는 스크롤이 바닥을 향해있다는것이므로
        // 스크롤 바닥을 유지하도록 플래그 설정
        bottomFlag = true
      }

      if (preDiffHeight > objDiv.scrollTop + objDiv.clientHeight) {
        // 스크롤이 한번이라도 바닥이 아닌 위로 상승하는 액션이 발생할 경우
        // 스크롤 바닥유지 플래그 해제
        bottomFlag = false
      }
      //
      preDiffHeight = objDiv.scrollTop + objDiv.clientHeight
    }
  }
}
</script>
<style scoped>
@font-face {
    font-family: 'GangwonEdu_OTFBoldA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
.chat {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 100%;
  height: 100%;
  background-color: #5b7b8d;
  margin: auto;
  box-shadow: 0px 1px 20px #9c9cc855;
  border-radius: 10px;
}
.myMsg {
  text-align: right;
  color: gray;
}
.otherMsg {
  text-align: left;
}
.chat__body {
  padding: 2rem;
  overflow: scroll;
  scroll-behavior: smooth;
  display:flex;
  flex-direction: column;
  height: 300px;
}

.chat__body::-webkit-scrollbar {
  display: none;
}
.chat__mymessage {
  display: flex;
  justify-content: right;
  align-items: flex-end;
  margin: 0;
  min-height: 40px;
  line-break: anywhere;
}

.chat__mymessage__paragraph {
  margin: 0.4rem 0 0 1rem;
  border-radius: 20px 20px 0px 20px;
  max-width: 180px;
  background-color: #b9729e;
  color: #ffffff;
  padding: 0.8rem;
  font-size: 14px;
  font-family: 'GangwonEdu_OTFBoldA';
}

.chat__yourmessage {
  display: flex;
}

.chat__yourmessage__user {
  font-size: 14px;
  font-weight: 700;
  color: #292929;
  margin-top: 0;
  margin-block-end: 0rem;
  font-family: 'GangwonEdu_OTFBoldA';
}

.chat__yourmessage__p {
  display: flex;
  align-items: flex-end;
  line-break: anywhere;
}

.chat__yourmessage__paragraph {
  margin: 0.4rem 1rem 0 0;
  border-radius: 0px 20px 20px 20px;
  background-color: #ede0cf;
  max-width: 180px;
  color: #414141;
  padding: 0.8rem;
  font-size: 14px;
  font-family: 'GangwonEdu_OTFBoldA';
}
/* 메시지 전송 input */
.form {
  display: flex;
  justify-content: space-between;
  padding: 1.4rem;
  height: 70px;
  background: #ffffff;
  box-shadow: 0px -5px 30px rgba(0, 0, 0, 0.05);
  border-radius: 10px;
  font-family: 'GangwonEdu_OTFBoldA';
}
.form__input {
  border: none;
  padding: 0.5rem;
  font-size: 16px;
  width: calc(100% - 60px);
}

.form__input:focus {
  outline: none;
}

.form__submit {
  display: flex;
  align-items: center;
  cursor: pointer;
}

svg {
  transition: 0.3s;
}

svg:hover {
  fill: #999999;
}
</style>
