<template>
    <div class="chat">
        <div class="chat__header">
    <img
      src="@/img/angle-circle-left.svg"
      alt=""
      @click="moveBack"
      style="margin-left:-450px; margin-top: -15px"
    />
        </div>
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
              <div v-else class="chat__yourmessage">
                <div class="box"><img class="photo" :src=photo /></div>
                <h5 class="chat__yourmessage__user" style="margin:3px">
                  {{ m.userId }}
                </h5>
                <div class="chat__yourmessage__p">
                  <p class="chat__yourmessage__paragraph">{{ m.content }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 메시지 전송하는 부분 -->
        <!-- <button @click="imgBtnClick">사진</button>
          <button @click="videoBtnClick">동영상</button>
          <v-file-input
            id="img"
            multiple
            accept="image/jpeg,image/jpg,image/png"
            style="display:none"
            @change="uploadImg"
          />
          <v-file-input
            id="video"
            accept="video/*"
            style="display:none"
            @change="uploadVideo"
        /> -->
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
import axios from 'axios'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import api from '@/api/api'
let preDiffHeight = 0
let bottomFlag = true

export default {
  name: 'ChatView',
  data: () => {
    return {
      id: -1,
      name: '',
      roomid: -1,
      idx: 0,
      msg: [],
      content: '',
      stompClient: null,
      photo: null
    }
  },
  created () {
    this.id = this.$route.query.id
    this.roomid = this.$route.query.roomid
    this.name = this.$route.query.name
    this.userId = this.$route.query.userId
    this.photo = this.$route.query.photo
    // 대화 불러오기
    axios.get(api.chat.getMessage() + `${this.roomid}`).then(
      res => {
        console.log(res)
        this.msg = []
        for (let i = 0; i <= res.data.length - 1; i++) {
          const m = {
            senderId: res.data[i].senderId,
            content: res.data[i].content,
            userId: res.data[i].userId,
            style: res.data[i].senderId === this.id ? 'myMsg' : 'otherMsg'
          }
          this.msg.push(m)
        }
      }
    ).catch(() => {})
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
    moveBack () {
      this.$router.push({
        name: 'chatlist',
        query: { id: this.id, name: this.name, userId: this.userId }
      })
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
    // imgBtnClick() {
    //   document.getElementById("img").click();
    // },
    // videoBtnClick() {
    //   document.getElementById("video").click();
    // },
    // uploadImg(e) {
    //   if (e == undefined) {
    //     alert("파일 오류");
    //     return;
    //   }
    //   const formData = new FormData();
    //   e.forEach(v => {
    //     formData.append("file", v);
    //   });
    //   this.uploadImgToS3({ formData: formData, roomId: this.roomId });
    // },
    // uploadVideo(e) {
    //   if (e == undefined) {
    //     alert("파일 오류");
    //     return;
    //   }
    //   const formData = new FormData();
    //   formData.append("file", e);
    //   this.uploadVideoToS3({ formData: formData, roomId: this.roomId });
    // }
  }
}
</script>
<style scoped>
.chat {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 505px;
  height: 712px;
  background-color: #bad8da;
  margin: 5rem auto 0rem;
  border-radius: 1.5rem;
  box-shadow: 0px 1px 20px #9c9cc855;
}
.myMsg {
  text-align: right;
  color: gray;
}
.otherMsg {
  text-align: left;
}
.chat__header {
  background: #ffffff;
  box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
  border-radius: 24px 24px 0px 0px;
  padding: 1.8rem;
  font-size: 16px;
  font-weight: 700;
  height:50px
}
.chat__header__greetings {
  color: #292929;
  margin-left: 10px;
}
.chat__body {
  padding: 2rem;
  overflow: scroll;
  scroll-behavior: smooth;
  display:flex;
  flex-direction: column;
  height: 80%;
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
  background-color: #bbc4ef;
  color: #ffffff;
  padding: 0.8rem;
  font-size: 14px;
}

.chat__yourmessage {
  display: flex;
}

.chat__mymessage__user {
  font-size: 14px;
  font-weight: 700;
  color: #292929;
  margin-top: 0;
  margin-block-end: 0rem;
}
.chat__yourmessage__user {
  font-size: 14px;
  font-weight: 700;
  color: #292929;
  margin-top: 0;
  margin-block-end: 0rem;
}

.chat__yourmessage__p {
  display: flex;
  align-items: flex-end;
  line-break: anywhere;
}

.chat__yourmessage__paragraph {
  margin: 0.4rem 1rem 0 0;
  border-radius: 0px 20px 20px 20px;
  background-color: #39f5e2;
  max-width: 180px;
  color: #414141;
  padding: 0.8rem;
  font-size: 14px;
}
/* 메시지 전송 input */
.form {
  display: flex;
  justify-content: space-between;
  padding: 1.4rem;
  background: #ffffff;
  border-radius: 30px 30px 24px 24px;
  box-shadow: 0px -5px 30px rgba(0, 0, 0, 0.05);
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
.box{
  width: 30px;
  height: 30px;
  border-radius: 70%;
  overflow: hidden;
  background-color: white;
  margin-right: 5px;
  margin-left: -20px;
}
.photo {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
