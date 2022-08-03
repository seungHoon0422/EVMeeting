<template>
  <div class="chat_list">
    <div class="title">
      <img
        src="@/img/angle-circle-left.svg"
        alt=""
        @click="moveBack"
        style="margin-left:-45px; margin-top: -5px;"
      />
      {{name}}님의 ChatRoom
      <img
        src="@/img/trash.svg"
        @click="showdelete()"
        style="margin-left: 480px; margin-top: -50px;"
      />
    </div>
    <!-- <input
      class="search_room"
      type="text"
      placeholder="방 제목"
      v-model="title"
    />&nbsp;&nbsp; -->
    <!-- <img
      src="@/img/add.svg"
      @click="createRoom()"
      style="margin-left: 0px; margin-top: 5px"
    /> -->
    <hr />
    <div v-if="room_list.length == 0">
      방 없다
    </div>
    <div class="roomList" v-else-if="room_list.length > 0">
      <div v-for="(r, idx) in room_list" :key="idx">
        <div id="rooms" class="rooms" @click="enterRoom(r.id)" v-if="id === r.userid1">
          <div class="other"> {{ r.userid2 }}</div><div v-if="id !== r.recentMessageId" class="msg" style="color:red">{{ r.recentMessage }}</div>
          <div v-else class="msg">{{ r.recentMessage }}</div>
        </div>
        <div id="rooms" class="rooms" @click="enterRoom(r.id)" v-else>
          <div class="other">{{ r.userid1 }}</div><div v-if="id !== r.recentMessageId" class="msg" style="color:red">{{ r.recentMessage }}</div>
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
import { mapGetters, mapActions, mapMutations } from 'vuex'
import axios from 'axios'

export default {
  name: 'ChatList',
  data: () => {
    return {
      id: -1,
      name: '',
      room_list: [1, 2, 3],
      isShowing: false
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn', 'currentUser', 'getFlag'])
  },
  watch: {
    ...mapMutations(['SET_FLAG'])
  },
  created () {
    this.fetchCurrentUser()
    this.id = this.$route.params.id
    this.name = this.$route.params.name
    if (this.id === -1 || typeof this.id === 'undefined') {
      this.$router.push({ name: 'home' })
    }
    axios({
      method: 'get',
      url: `/api/v1/chat/rooms/${this.id}`,
      baseURL: 'http://localhost:8080/'
    }).then(
      res => {
        console.log(res)
        this.room_list = []
        for (let i = 0; i < res.data.length; i++) {
          const room = {
            id: res.data[i].id,
            userid1: res.data[i].userid1,
            userid2: res.data[i].userid2,
            recentMessage: res.data[i].recentMessage,
            recentMessageId: res.data[i].recentMessageId
          }
          this.room_list.push(room)
        }
      },
      err => {
        console.log(err)
        this.$router.push({ name: 'home' })
      }
    )
  },
  methods: {
    ...mapActions(['fetchCurrentUser']),
    enterRoom (id) {
      this.$router.push({
        name: 'chat',
        params: { roomid: id, id: this.id, name: this.name }
      })
    },
    moveBack () {
      this.$router.push({
        name: 'home'
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
  padding-top: 5px;
  padding-bottom: 5px;
  margin-left: -5px;
  width:250px;
  height:25px;
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
  /* display: flex;
  flex-direction: row;
  justify-content: left;
  align-items: center; */
  border: 2px #fefefe solid;
  height: 70px;
  max-width: 90%;
  border-radius: 10px;
  font-size: 20px;
  background: #ffffea;
  margin-bottom: 5px;
  padding: 10px;
}
.other {
  display: flex;
  flex-direction: row;
  justify-content: left;
  align-items: center;
}
.msg {
  display: block;
  flex-direction: row;
  justify-content: right;
  margin-top: -10px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-left: 280px;
  width: 150px;
  height: 40px;
}
</style>
