<template>
  <div class="box">
    <!-- <h1>Home</h1> -->
    <div class="home-text text-start">
      <p class="text-left">엘리베이터가</p>
      <p class="text-right">멈춰있는 시간 10초</p>
      <p class="text-left mt-5">상대방을 판단하기에</p>
      <p class="text-right">충분하지 않을까..?</p>
      <button class="mt-5 home-btn">엘리베이터 타러 가기</button>
    </div>
    <img class="elevator-img" src="@/img/elevator.jpg" alt="..">
    <p v-if="isLoggedIn">Hi, {{ currentUser.userid }}</p>
  </div>
</template>

<script>
// @ is an alias to /src
// import axios from 'axios'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'HomeView',
  computed: {
    ...mapGetters(['currentUser', 'isLoggedIn'])
  },
  methods: {
    ...mapActions(['fetchCurrentUser'])
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
  width: 1200px;
  margin: 0 auto;
  position: relative;
}

.elevator-img {
  position: absolute;
  top: 50px;
  right: 0%;
}

.home-text {
  position: absolute;
  top: 100px;
  left: 10%;
  font-size: 40px;
  color: #F88F6D;
}

.home-btn {
  background-color: #F88F6D;
  color: white;
  border-radius: 20px;
  border: none;
  padding: 10px 50px;
  font-size: 30px;
}

.text-left {
  margin-left: -80px;
}

.text-right {
  margin-left: 100px;
}
</style>
