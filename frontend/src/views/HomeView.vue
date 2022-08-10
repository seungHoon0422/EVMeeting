<template class="home">
  <div class="box">
    <div class="nav-btn-position d-flex justify-content-end">
      <!-- <router-link :to="{ name: 'login' }"><button class="login-btn">로그인/회원가입</button></router-link> -->
      <a href="/login"><button class="nav-btn" v-if="!isLoggedIn">로그인/회원가입</button></a>
      <a href="/profile"><button class="nav-btn me-3" v-if="isLoggedIn">내 정보</button></a>
      <a href="/logout"><button class="nav-btn" v-if="isLoggedIn">로그아웃</button></a>
    </div>
    <div class="home-text text-start">
      <div class="text-style">
        <p>엘리베이터에서 안에서 우린</p>
        <p>사랑을 나누지</p>
      </div>
      <!-- <p class="text-left">엘리베이터가</p>
      <p class="text-right">멈춰있는 시간 10초</p>
      <p class="text-left mt-5">상대방을 판단하기에</p>
      <p class="text-right">충분하지 않을까..?</p> -->
      <button class="home-btn" @click="ToCam">엘리베이터 타러 가기</button>
      <!-- <chatting-room-view style="margin-top: 10px"></chatting-room-view> -->
    </div>
    <!-- <img class="elevator-img" src="@/img/elevator_1.gif" alt=".."> -->
    <!-- <p v-if="isLoggedIn">Hi, {{ currentUser.userid }}</p> -->
    <!-- <footer-bar class="footer"></footer-bar> -->
  </div>
</template>

<script>
// @ is an alias to /src
// import axios from 'axios'
import { mapGetters, mapActions } from 'vuex'
// import swal from 'sweetalert'
// import FooterBar from '@/views/accounts/components/FooterBar.vue'
// import ChattingRoomView from './chat/ChattingRoomView.vue'

export default {
  // components: { FooterBar },
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
</script>

<style scoped>
.box {
  height: 1200px;
  width: 100%;
  /* background-color: white; */
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
  top: 100px;
  left: 10%;
  font-size: 50px;
  color: white;
}

.home-btn {
  background-color: transparent;
  color: white;
  border-radius: 20px;
  border: 2px solid white;
  padding: 10px 50px;
  font-size: 30px;
  margin-top: 100px;
}

.nav-btn {
  background-color: transparent;
  color: white;
  border-radius: 10px;
  border: 1px solid white;
  padding: 10px 30px;
  font-size: 15px;
}

.nav-btn-position {
  padding-right: 100px;
  padding-top: 50px;
}

button:hover {
  color: gray;
  background-color: white;
}

.text-style {
  font-weight: 300;
}

.footer {
  margin-top: 1000px;
  /* position: fixed; */
}
</style>
