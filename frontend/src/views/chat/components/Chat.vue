<template>
  <div class="chat">
    <div class="chat__header">
      <img
        src="@/img/angle-circle-left.svg"
        alt=""
        @click="moveBack"
        style="margin-left:-450px; margin-top: 5px"
      />
    </div>
    <div class="chat__body" id="chat__body" onscroll="chat_on_scroll()">
      <chat-message
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
            <h5 class="chat__yourmessage__user" style="margin:3px">
              {{ m.senderId }}
            </h5>
            <div class="chat__yourmessage__p">
              <p class="chat__yourmessage__paragraph">{{ m.content }}</p>
            </div>
          </div>
        </div>
      </chat-message>
    </div>
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
    <chat-form ></chat-form>
  </div>
</template>

<script>
import axios from 'axios'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import ChatForm from './ChatForm.vue'
let preDiffHeight = 0
let bottomFlag = true

export default {
  components: { ChatForm },
  name: 'ChatView',
  data: () => {
    return {
      id: -1,
      nickname: '',
      roomid: -1,
      idx: 0,
      msg: [],
      content: '',
      stompClient: null
    }
  },
  updated () {
    const objDiv = document.getElementById('chat__body')
    if (bottomFlag) {
      // 채팅창 스크롤 바닥 유지
      objDiv.scrollTop = objDiv.scrollHeight
    }
  },
  created () {
    this.id = this.$route.params.id
    // this.roomId = this.$route.params.roomid
    // this.nickname = this.$route.params.nickname
    // 방 제목 가져오기
    // axios({
    //   method: 'get',
    //   url: '/api/v1/chat/room/' + this.roomid,
    //   baseURL: 'http://localhost:8080/'
    // }).then(
    //   res => {
    //     this.title = res.data
    //   },
    //   err => {
    //     console.log(err)
    //     this.$router.push({ name: 'Home' })
    //   }
    // )

    // 채팅방 내용 불러오기
    axios({
      method: 'get',
      url: `/api/v1/chat/room/allMessage/${this.id}`,
      baseURL: 'http://localhost:8080/'
    }).then(
      res => {
        this.msg = []
        for (let i = res.data.length - 1; i > -1; i--) {
          const m = {
            senderId: res.data[i].senderId,
            content: res.data[i].content,
            style: res.data[i].senderId === this.id ? 'myMsg' : 'otherMsg'
          }
          this.msg.push(m)
        }
      },
      err => {
        console.log(err)
        alert('error : 새로고침하세요')
      }
    )
    // socket 연결
    const socket = new SockJS('http://localhost:8080/ws')
    this.stompClient = Stomp.over(socket)
    this.stompClient.connect(
      {},
      frame => {
        console.log('success', frame)
        this.stompClient.subscribe('/sub/' + this.roomid, res => {
          const jsonBody = JSON.parse(res.body)
          const m = {
            senderId: jsonBody.senderId,
            content: jsonBody.content,
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
  methods: {
    submitMessage () {
      if (this.content.trim() !== '' && this.stompClient != null) {
        const chatMessage = {
          content: this.content,
          chatroomId: this.roomid,
          senderNickname: this.nickname,
          senderId: this.id,
          id: '0'
        }
        this.stompClient.send('/pub/message', JSON.stringify(chatMessage), {})

        this.content = ''
      }
    },
    moveBack () {
      this.$router.push({ name: 'chatlist' })
    },
    chat_on_scroll () {
      const objDiv = document.getElementById('app_chat_list')

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
.chat{
  width: 505px;
  height: 712px;
  background-color: #bad8da;
  margin: 5rem auto 0rem;
  border-radius: 1.5rem;
  box-shadow: 0px 1px 20px #9c9cc855;
}
.room {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
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
}
.chat__header__greetings {
  color: #292929;
  margin-left: 10px;
}
.chat__body {
  padding: 2rem;
  overflow: scroll;
  scroll-behavior: smooth;
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

.form {
  display: flex;
  justify-content: space-between;
  padding: 1.4rem;
  background: #ffffff;
  border-radius: 30px 30px 24px 24px;
  box-shadow: 0px -5px 30px rgba(0, 0, 0, 0.05);
  margin-top: 80px;
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
