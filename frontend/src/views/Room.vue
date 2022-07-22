<template>
  <div class="room">
    <h1>{{ title }}</h1>
    <hr />
    <div v-for="(m, idx) in msg" :key="idx">
      <div v-bind:class="m.style">
        <h5 style="margin:3px">
          {{ m.senderNickname }}
        </h5>
        {{ m.content }}
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
    }
  }
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
