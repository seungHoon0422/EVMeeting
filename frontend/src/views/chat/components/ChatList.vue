<template>
  <div class="chat_list">
    <div class="title">
      ChatRoom
      <img
        src="@/img/trash.svg"
        @click="showdelete()"
        style="margin-left: 330px;  margin-right: 5px; margin-top: 20px;"
      />
    </div>
    <!-- <input
      class="search_room"
      type="text"
      placeholder="방 제목"
      v-model="title"
    />&nbsp;&nbsp; -->
    <img
      src="@/img/add.svg"
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
          {{ r.id }}
        </div>
        <div
          style="float:right; margin-top:-50px; margin-right: 8px; background-color: red; border-radius: 30px;"
        >
          <img
            v-if="isShowing"
            src="@/img/cross.svg"
            @click="deleteRoom()"
            style="margin-right: 7px; padding-left: 7px;"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { mapGetters, mapActions } from 'vuex'
export default {
  name: 'ChatList',
  data: () => {
    return {
      id: -1,
      nickname: '',
      room_list: [1, 2, 3],
      isShowing: false
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn', 'currentUser'])
  },
  created () {
    this.fetchCurrentUser()
    this.name = this.currentUser.username
    // if (this.id === -1 || typeof this.id === 'undefined') {
    //   this.$router.push({ name: 'home' })
    // }
    alert('Hi ! ' + this.currentUser.userid)
    axios({
      method: 'get',
      url: '/api/v1/chat/rooms/1',
      baseURL: 'http://localhost:8080/'
    }).then(
      res => {
        console.log(res)
        this.room_list = []
        for (let i = 0; i < res.data.length; i++) {
          const room = {
            id: res.data[i].id
          }
          this.room_list.push(room)
        }
      }
      // err => {
      //   console.log(err)
      //   this.$router.push({ name: 'home' })
      // }
    )
  },
  methods: {
    ...mapActions(['fetchCurrentUser']),
    enterRoom (id) {
      this.$router.push({
        name: 'chat',
        params: { id: id, name: this.currentUser.username }
      })
    },
    createRoom () {
      axios({
        method: 'post',
        url: '/api/v1/chat/room',
        baseURL: 'http://localhost:8080/',
        headers: { 'content-type': 'application/json' },
        data: { userid1: 1, userid2: 2 }
      }).then(
        res => {
          this.$router.push({
            name: 'chat',
            params: { userid1: this.userid1, userid2: this.userid2, id: this.id }
          })
        },
        err => {
          console.log(err)
          this.$router.push({ name: 'home' })
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
.chat_list{
  width: 505px;
  height: 712px;
  background-color: #bad8da;
  margin: 5rem auto 0rem;
  border-radius: 1.5rem;
  box-shadow: 0px 1px 20px #9c9cc855;
}
.title {
  margin-top: 20px;
  margin-left: 10px;
  font-size: large;
  font-family: "Golden Plains - Demo";
  color: #FFFFEA;
}
h3 {
  margin-left: 15px;
  margin-bottom: 10px;
}
.roomList {
  margin-left: 15px;
}
/* .search_room {
  margin-left: 15px;
  margin-bottom: 30px;
  height: 20px;
} */
.rooms {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 2px #fefefe solid;
  height: 70px;
  max-width: 90%;
  border-radius: 10px;
  font-size: 20px;
  background: #ffffea;
  margin-bottom: 5px;
  text-align: center;
}
</style>
