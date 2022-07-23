<template>
  <div class="room_list">
    <h2>채팅방 목록</h2>
    <h3>{{ nickname }}님</h3>
    <input class="search_room" type="text" placeholder="방 제목" v-model="title" />&nbsp;&nbsp;
    <img src="../assets/add.svg" @click="createRoom()" style="margin-left: 0px; margin-top: 5px" />
    <hr />
    <div v-if="room_list.length == 0">
      방 없다
    </div>
    <div class="roomList" v-else-if="room_list.length > 0">
      <div v-for="(r, idx) in room_list" :key="idx">
        <div class="rooms"
          @click="enterRoom(r.id)"
        >
          {{ r.title }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "RoomList",
  data: () => {
    return {
      id: -1,
      nickname: "",
      room_list: [1, 2, 3],
      title: ""
    };
  },
  created() {
    this.id = this.$route.params.id;
    this.nickname = this.$route.params.nickname;
    if (this.id == -1 || typeof this.id === "undefined") {
      this.$router.push({ name: "Home" });
    }
    alert("Hi ! " + this.$route.params.nickname);
    axios({
      method: "get",
      url: "/api/chat/rooms",
      baseURL: "http://localhost:8080/"
    }).then(
      res => {
        console.log(res);
        this.room_list = [];
        for (let i = 0; i < res.data.length; i++) {
          let room = {
            id: res.data[i].id,
            title: res.data[i].title
          };
          this.room_list.push(room);
        }
      },
      err => {
        console.log(err);
        this.$router.push({ name: "Home" });
      }
    );
  },
  methods: {
    enterRoom(id) {
      this.$router.push({
        name: "Room",
        params: { roomid: id, nickname: this.nickname, id: this.id }
      });
    },
    createRoom() {
      axios({
        method: "post",
        url: "/api/chat/room",
        baseURL: "http://localhost:8080/",
        headers: { "content-type": "application/json" },
        data: { id: this.id, title: this.title, masterId: this.id }
      }).then(
        res => {
          this.$router.push({
            name: "Room",
            params: { roomid: res.data, nickname: this.nickname, id: this.id }
          });
        },
        err => {
          console.log(err);
          this.$router.push({ name: "Home" });
        }
      );
    }
  }
};
</script>
<style scoped>
 h2 {
  margin-top: 20px;
  text-align: center;
 }
 h3{
  margin-left: 15px;
  margin-bottom:10px;
 }
 .roomList{
  margin-left:15px;
 }
 .search_room{
  margin-left:15px;
  margin-bottom:30px;
  height: 20px;
 }
 .rooms{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border:2px yellow solid; 
  height:70px; 
  width:95%; 
  border-radius: 10px;
  font-size:20px;
  background: rgb(255, 255, 172);
  margin-bottom: 5px;
 }
</style>