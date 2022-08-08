<template class="home">
  <div class="box">
    <div class="login-btn-position d-flex justify-content-end">
      <!-- <router-link :to="{ name: 'login' }"><button class="login-btn">로그인/회원가입</button></router-link> -->
      <a href="/login"><button class="login-btn">로그인/회원가입</button></a>
    </div>
    <div class="home-text text-start">
      <p class="text-left">엘리베이터가</p>
      <p class="text-right">멈춰있는 시간 10초</p>
      <p class="text-left mt-5">상대방을 판단하기에</p>
      <p class="text-right">충분하지 않을까..?</p>
      <button class="mt-5 home-btn" @click="ToCam">엘리베이터 타러 가기</button>
      <!-- <chatting-room-view style="margin-top: 10px"></chatting-room-view> -->
    </div>
    <!-- <img class="elevator-img" src="@/img/elevator_1.gif" alt=".."> -->
    <!-- <p v-if="isLoggedIn">Hi, {{ currentUser.userid }}</p> -->
  </div>
</template>

<script>
// @ is an alias to /src
// import axios from 'axios'
import { mapGetters, mapActions } from 'vuex'
// import ChattingRoomView from './chat/ChattingRoomView.vue'

export default {
  // components: { ChattingRoomView },
  name: 'HomeView',
  computed: {
    ...mapGetters(['currentUser', 'isLoggedIn'])
  },
  methods: {
    ...mapActions(['fetchCurrentUser']),
    ToCam () {
      this.$router.push('/cam')
    }
  },
  created () {
    this.fetchCurrentUser()
  }
}
// created () {
//   const searchParams = new URLSearchParams(location.search)
//   for (const param of searchParams) {
//     if (param[0] === 'code') {
//       var code = param[1]
//     } else {
//       var state = param[1]
//     }
//   }
//   axios({
//     url: 'https://nid.naver.com/oauth2.0/token',
//     method: 'get',
//     headers: {
//       grant_type: 'authorization_code',
//       client_id: 'w8_s1fcXM3yxRgEKy4yI',
//       client_secret: 'A7646UmvlH',
//       code: code,
//       state: state
//     }
//   })

// methods: {
//   ...mapActions(['fetchCurrentUser'])
// },
// created () {
//   this.fetchCurrentUser()
// }
// }
</script>

<style scoped>
.box {
  height: 1200px;
  width: 100%;
  background-image: linear-gradient( rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2) ), url(@/img/scale.jpg);
  background-repeat : no-repeat;
  background-size : cover;
  background-attachment: fixed;
}

.elevator-img {
  position: absolute;
  top: 50px;
  right: 0%;
}

.home-text {
  position: absolute;
  top: 180px;
  left: 20%;
  font-size: 40px;
  color: white;
}

.home-btn {
  background-color: transparent;
  color: white;
  border-radius: 20px;
  border: 2px solid white;
  padding: 10px 50px;
  font-size: 30px;
}

.login-btn {
  background-color: transparent;
  color: white;
  border-radius: 10px;
  border: 1px solid white;
  padding: 10px 30px;
  font-size: 15px;
}

.text-left {
  margin-left: -80px;
}

.text-right {
  margin-left: 100px;
}

.login-btn-position {
  padding-right: 50px;
  padding-top: 50px;
}

button:hover {
  color: gray;
  background-color: white;
}
</style>
