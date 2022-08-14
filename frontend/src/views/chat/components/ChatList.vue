<template>
  <div class="chat_list">
    <div class="title">
      <img
        src="@/img/angle-circle-left.svg"
        alt=""
        @click="moveBack"
        style="margin-left:-80px;"
      />
      {{ this.userId }}님의 매칭
      <img
        src="@/img/trash.svg"
        @click="showdelete()"
        style="margin-left: 480px; margin-top: -50px;"
      />
    </div>
    <hr />
    <div id="roomList" v-if="room_list.length == 0">
      방 없다
    </div>
    <div class="roomList" id="roomList" v-else-if="room_list.length">
      <div v-for="(r, idx) in room_list" :key="idx">
        <div v-if="r.alive == true">
          <div id="rooms" class="rooms" @click="enterRoom(r.id, r.photo2)" v-if="userId === r.senderId1">
            <div class="other"><div class="photo_box"><img class="photo" :src=r.photo2 /></div>{{ r.senderId2 }}</div><div class="msg">{{ r.recentMessage }}</div><div class="time">{{r.recentTime.split('T')[1].split('.')[0]}} </div>
          </div>
          <div id="rooms" class="rooms" @click="enterRoom(r.id, r.photo1)" v-else>
            <div class="other"><div class="photo_box"><img class="photo" :src=r.photo1 /></div>{{ r.senderId1 }}</div><div class="msg">{{ r.recentMessage }}</div><div class="time">{{r.recentTime.split('T')[1].split('.')[0]}} </div>
          </div>
          <div
            style="float:right; margin-top:-75px; margin-right: 8px; background-color: red; border-radius: 30px; height: 60px;"
          >
            <img
              v-if="isShowing"
              src="@/img/cross.svg"
              @click="deleteRoom(r.id)"
              style="margin-right: 7px; padding-left: 7px; padding-top: 20px;"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import axios from 'axios'
import api from '@/api/api'
let preDiffHeight = 0
let bottomFlag = true

export default {
  name: 'ChatList',
  data: () => {
    return {
      id: -1,
      name: '',
      room_list: [1, 2, 3],
      isShowing: false,
      userId: -1,
      photo: null
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn', 'currentUser'])
  },
  updated () {
    const objDiv = document.getElementById('roomList')
    if (bottomFlag) {
      objDiv.scrollTop = objDiv.scrollHeight
    }
  },
  created () {
    this.fetchCurrentUser()
    this.id = this.currentUser.id
    this.name = this.currentUser.name
    this.userId = this.currentUser.userid
    if (this.id === -1 || typeof this.id === 'undefined') {
      this.$router.push({ name: 'home' })
    }
    axios({
      method: 'get',
      url: api.chat.getRooms() + `${this.id}`
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
            recentMessageId: res.data[i].recentMessageId,
            senderId1: res.data[i].senderId1,
            senderId2: res.data[i].senderId2,
            alive: res.data[i].alive,
            recentTime: res.data[i].recentMessageTime,
            photo1: res.data[i].photoUrl1,
            photo2: res.data[i].photoUrl2
          }
          this.room_list.push(room)
        }
      },
      err => {
        console.log(err)
        window.location.href = '/'
      }
    )
  },
  methods: {
    ...mapActions(['fetchCurrentUser']),
    enterRoom (id, photo) {
      this.$router.push({
        name: 'chat',
        query: { roomid: id, photo: photo }
      })
    },
    moveBack () {
      window.location.href = '/'
    },
    deleteRoom (id) {
      if (confirm('정말 삭제하시겠습니까??') === true) {
        axios.put(api.chat.deleteRoom() + `${id}`, { 'content-type': 'application/json' }
        ).then(
          res => {
            this.$router.push({ name: 'chatlist' }).catch(() => {})
          }
        ).catch({})
      } else {
        return false
      }
    },
    showdelete () {
      if (this.isShowing === false) this.isShowing = true
      else this.isShowing = false
    },
    chat_on_scroll () {
      document.getElementById('roomList').scrollTop(0)
      const objDiv = document.getElementById('roomList')

      if (objDiv.scrollTop + objDiv.clientHeight === objDiv.scrollHeight) {
        bottomFlag = true
      }
      if (preDiffHeight > objDiv.scrollTop + objDiv.clientHeight) {
        bottomFlag = false
      }
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
.chat_list{
  width: 505px;
  height: 670px;
  background-color: #E7CECA;
  margin: 5rem auto 0rem;
  border-radius: 1.5rem;
  box-shadow: 0px 1px 20px #9c9cc855;
}
.roomList {
  overflow: scroll;
  scroll-behavior: smooth;
  display:flex;
  flex-direction: column;
  height: 90%;
  margin-left: 15px;
}
.roomList::-webkit-scrollbar {
  display: none;
}
.title {
  padding-top: 10px;
  padding-bottom: 5px;
  margin-left: -5px;
  width:250px;
  height:25px;
  font-size: large;
  color: #FFFFEA;
  font-family: 'GangwonEdu_OTFBoldA';
}
h3 {
  margin-left: 15px;
  margin-bottom: 10px;
}
.rooms {
  border: 1px #EDE0CF solid;
  height: 80px;
  max-width: 90%;
  border-radius: 10px;
  font-size: 20px;
  background: #EDE0CF;
  margin-bottom: 5px;
  padding: 10px;
}
.other {
  display: flex;
  flex-direction: row;
  justify-content: left;
  align-items: center;
  font-family: 'GangwonEdu_OTFBoldA';
}
.msg {
  display: block;
  flex-direction: row;
  justify-content: right;
  margin-top: -50px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-left: 280px;
  width: 150px;
  height: 40px;
  font-family: 'GangwonEdu_OTFBoldA';
}
.time {
  text-align: right;
  font-size: 15px;
  font-family: 'GangwonEdu_OTFBoldA';
}
.photo_box{
  width: 60px;
  height: 60px;
  border-radius: 70%;
  overflow: hidden;
  background-color: #ffffff;
  margin-right: 5px;
}
.photo {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
