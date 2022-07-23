<template>
  <div class="room">
    <div class="chat__header">
      <img src="../assets/angle-circle-left.svg" alt="" @click="moveBack" style="margin-left: 0px; margin-top: 5px" />
      <span class="chat__header__greetings">{{ title }}</span>
    </div>
    <div class="chat__body" id="chat__body">
      <chat-message
        v-for="(m, idx) in msg"
        :key="idx"
        :m="m"
        :prev="[idx == 0 ? null : msg[idx - 1]]"
      >
        <div v-bind:class="m.style">
          <div v-if="masterId == senderId" class="chat__mymessage">
            <h5 class="chat__mymessage__user" style="margin:3px">
              {{ m.senderNickname }}
            </h5>
            <p class="chat__mymessage__paragraph">{{ m.content }}</p>
          </div>
          <div v-else class="chat__yourmessage">
            <h5 class="chat__yourmessage__user" style="margin:3px">
              {{ m.senderNickname }}
            </h5>
            <div class="chat__yourmessage__p">
              <p class="chat__yourmessage__paragraph">{{ m.content }}</p>
            </div>
          </div>
        </div>
      </chat-message>
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
import axios from "axios";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
export default {
  name: "Room",
  data: () => {
    return {
      id: -1,
      nickname: "",
      title: "",
      roomid: -1,
      idx: 0,
      msg: [],
      content: "",
      stompClient: null
    };
  },
  created() {
    this.id = this.$route.params.id;
    this.roomid = this.$route.params.roomid;
    this.nickname = this.$route.params.nickname;
    // 방 제목 가져오기
    axios({
        method:'get',
        url:'/api/chat/room/'+this.roomid,
        baseURL:'http://localhost:8080/'
      }).then(res=>{
        this.title = res.data
      }, err=>{
        console.log(err)
        this.$router.push({ name: "Home" });
      })

    // 채팅방 내용 불러오기
    axios({
      method: "get",
      url: "/api/chat/room/message/" + this.roomid + "?page=" + this.idx,
      baseURL: "http://localhost:8080/"
    }).then(
      res => {
        this.msg = [];
        for (let i = res.data.length - 1; i > -1; i--) {
          let m = {
            senderNickname: res.data[i].senderNickname,
            content: res.data[i].content,
            style: res.data[i].senderId == this.id ? "myMsg" : "otherMsg"
          };
          this.msg.push(m);
        }
      },
      err => {
        console.log(err);
        alert("error : 새로고침하세요");
      }
    );
    // socket 연결
    let socket = new SockJS("http://localhost:8080/ws");
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect(
      {},
      frame => {
        console.log("success", frame);
        this.stompClient.subscribe("/sub/" + this.roomid, res => {
          let jsonBody = JSON.parse(res.body);
          let m = {
            senderNickname: jsonBody.senderNickname,
            content: jsonBody.content,
            style: jsonBody.senderId == this.id ? "myMsg" : "otherMsg"
          };
          this.msg.push(m);
        });
      },
      err => {
        console.log("fail", err);
      }
    );
  },
  methods: {
    submitMessage() {
      if (this.content.trim() != "" && this.stompClient != null) {
        let chatMessage = {
          content: this.content,
          chatroomId: this.roomid,
          senderNickname: this.nickname,
          senderId: this.id,
          id: "0"
        };
        this.stompClient.send("/pub/message", JSON.stringify(chatMessage), {});

        this.content = "";
      }
    },
    moveBack() {
      this.$router.go(-1);
    },
  },
};
</script>
<style scoped>
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
