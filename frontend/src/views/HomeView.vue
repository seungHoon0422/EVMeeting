<template class="home">
  <div class="box">
    <div class="nav-btn-position d-flex justify-content-end" style="color:black;">
      <!-- <router-link :to="{ name: 'login' }"><button class="login-btn">로그인/회원가입</button></router-link> -->
      <a href="/totalstat"><button class="nav-btn mr-3" style="font-family:'GangwonEdu_OTFBoldA';">
        <b-icon icon="bar-chart-fill" scale="1" variant="danger"></b-icon>
      About US</button></a>
      <a href="/login"><button class="nav-btn" v-if="!isLoggedIn">로그인/회원가입</button></a>
      <a href="/profile"><button class="nav-btn me-3"  v-if="isLoggedIn">내 정보</button></a>
      <a href="/logout"><button class="nav-btn" style="font-family:'GangwonEdu_OTFBoldA';" v-if="isLoggedIn">로그아웃</button></a>
    </div>
    <div class="home-text text-start">
      <div class="container d-flex justify-content-around">
        <div class="col-auto mx-5">
          <!-- <div class="text-style">
            <p>엘리베이터에서 안에서 우린</p>
            <p>사랑을 나누지</p>
          </div> -->
          <div class="col-auto">
            <button id="buttonIcon">
              <img src="@/img/logo1.png" id="logo">
            </button>
          </div>
          <div class="text-style col-auto">
            <div class="col-auto">
              <p style="font-family:'GangwonEdu_OTFBoldA';">엘리베이터에서 안에서 우린</p>
              <p style="font-family:'GangwonEdu_OTFBoldA';">사랑을 나누지</p>
            </div>
          </div>
          <div>
            <button class="home-btn col-auto" @click="ToCam">
                <p style="margin-top: 20px; font-weight: bold; font-family:'GangwonEdu_OTFBoldA'; color : white;">엘리베이터 타러 가기 </p>
            </button>
          </div>
        </div>
        <div class="col-auto">
          <video-player :mp4="require(`@/img/elevatorVideo.mp4`)" ></video-player>
        </div>
      </div>
      <footer-bar class="footer"></footer-bar>
    </div>

    <!-- <img class="elevator-img" src="@/img/elevator_1.gif" alt=".."> -->
    <!-- <p v-if="isLoggedIn">Hi, {{ currentUser.userid }}</p> -->
  </div>
</template>

<script>
// @ is an alias to /src
// import axios from 'axios'
import { mapGetters, mapActions } from 'vuex'
// import swal from 'sweetalert'
import FooterBar from '@/views/accounts/components/FooterBar.vue'
// import ChattingRoomView from './chat/ChattingRoomView.vue'
import VideoPlayer from '@/views/video/components/VideoPlayer.vue'

export default {
  name: 'HomeView',
  components: {
    VideoPlayer,
    FooterBar
  },
  computed: {
    ...mapGetters(['currentUser', 'isLoggedIn'])
  },
  methods: {
    ...mapActions(['fetchCurrentUser']),
    ToCam () {
      // this.$router.go('/cam')
      window.location.href = '/cam'
    }
  },
  created () {
    this.fetchCurrentUser()
  }
}
</script>

<style scoped>

/* <<<<<<< HEAD */
@font-face {
    font-family: 'GangwonEdu_OTFBoldA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

#font{
  font-family: 'GangwonEdu_OTFBoldA';
}
/* =======
.total-stat-card {
  background-color: cadetblue;
  border-radius: 20px;
  border: 2px solid white;
  padding: 10px 50px;
  font-size: 30px;
  margin-top: 400px;

}

>>>>>>> feature-statistics */
.box {
  height: 120vh;
  width: 100%;
  /* background-color: white; */
  background-color : #f5f5f5;
  /* background-image: linear-gradient( rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2) ), url(@/img/scale.jpg); */
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
  /* position: absolute; */
  top: 100px;
  left: 10%;
  font-size: 50px;
  color: black;
}

.home-btn {
  background-color: #B9729E;
  color: black;
  border-radius: 10px;
  border: 2px solid white;
  padding: 5px 50px;
  font-size: 20px;
  margin-top: 50px;
}

.nav-btn {
  background-color: transparent;
  color: black;
  border-radius: 10px;
  border: 1px solid #B9729E;
  padding: 10px 30px;
  font-size: 15px;
  margin: 10px;
}

.nav-btn-position {
  padding-right: 100px;
  padding-top: 50px;
}

button:hover {
  color: white;
  background-color: #B9729E;
}

.text-style {
  font-weight: 300px;
  color : black;
  margin-top: 50px;
  font-size: 50%;
}

.footer {
  margin-top: 350px;
  /* bottom: 0; */
  /* position: sticky; */
}

#buttonIcon{
  background-color:transparent;
  border: 0;
  outline: 0;
}

#logo{
  margin-top:200px;
  width: 70px;
  height: 70px;
}

</style>
