<template>
  <div class="room_list">
    <div class="title">
      채팅 목록
      <img
        src="../img/trash.svg"
        @click="showdelete()"
        style="margin-left: 350px;  margin-right: 5px;"
      />
    </div>
    <h3>{{ nickname }}님</h3>
    <input
      class="search_room"
      type="text"
      placeholder="방 제목"
      v-model="title"
    />&nbsp;&nbsp;
    <img
      src="../img/add.svg"
      @click="createRoom()"
      style="margin-left: 0px; margin-top: 5px"
    />
    <hr />
    <div v-if="room_list.length == 0">
      방 없다
    </div>
    <div class="roomList" v-else-if="room_list.length > 0">
      <div v-for="(r, idx) in room_list" :key="idx">
        <div id="rooms" class="rooms" @click="enterRoom(r.id)">
          {{ r.title }}
        </div>
        <div
          style="float:right; margin-top:-50px; margin-right: 5px; background-color: red; border-radius: 30px;"
        >
          <img
            v-if="isShowing"
            src="../img/cross.svg"
            @click="deleteRoom()"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'ChatList',
  data: () => {
    return {
      id: -1,
      nickname: '',
      room_list: [1, 2, 3],
      title: '',
      isShowing: false
    }
  },
  created () {
    this.id = this.$route.params.id
    this.nickname = this.$route.params.nickname
    if (this.id === -1 || typeof this.id === 'undefined') {
      this.$router.push({ name: 'Home' })
    }
    alert('Hi ! ' + this.$route.params.nickname)
    axios({
      method: 'get',
      url: '/api/chat/room/chatroomInfo/{id}',
      baseURL: 'http://localhost:8080/'
    }).then(
      res => {
        console.log(res)
        this.room_list = []
        for (let i = 0; i < res.data.length; i++) {
          const room = {
            id: res.data[i].id,
            title: res.data[i].title
          }
          this.room_list.push(room)
        }
      },
      err => {
        console.log(err)
        this.$router.push({ name: 'Home' })
      }
    )
  },
  methods: {
    enterRoom (id) {
      this.$router.push({
        name: 'chat',
        params: { roomid: id, nickname: this.nickname, id: this.id }
      })
    },
    createRoom () {
      axios({
        method: 'post',
        url: '/api/v1/chat/room',
        baseURL: 'http://localhost:8080/',
        headers: { 'content-type': 'application/json' },
        data: { id: this.id }
      }).then(
        res => {
          this.$router.push({
            name: 'chat',
            params: { userid1: this.userid1, userid2: this.userid2, id: this.id }
          })
        },
        err => {
          console.log(err)
          this.$router.push({ name: 'Home' })
        }
      )
    },
    showdelete () {
      if (this.isShowing === false) this.isShowing = true
      else this.isShowing = false
    }
  }
}
</script>
<style scoped>
.title {
  margin-top: 10px;
  text-align: center;
  font-size: large;
  font: bold;
}
h3 {
  margin-left: 15px;
  margin-bottom: 10px;
}
.roomList {
  margin-left: 15px;
}
.search_room {
  margin-left: 15px;
  margin-bottom: 30px;
  height: 20px;
}
.rooms {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 2px #f90a0a solid;
  height: 70px;
  max-width: 90%;
  border-radius: 10px;
  font-size: 20px;
  background: #ffffea;
  margin-bottom: 5px;
  text-align: center;
}
</style>
