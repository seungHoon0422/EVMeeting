<template>
<div class="nav-box">
  <nav class="d-flex justify-content-center pb-0 font-normal navbar align-items-center">
    <div class="container-fluid">
      <div style="margin-top:10px;">
        <a class="" href="/">
          <img src="@/img/logo1.png" id="logo">
        </a>
      </div>
      <div class="nav-padding">
<!-- <<<<<<< HEAD -->
        <a class="text-light me-5 text-decoration-none" href="/">홈</a>
        <router-link v-if="isLoggedIn" class="text-dark me-5 text-decoration-none" :to="{ name: 'chatlist' }"><span :class="{'selected': clicked === 'match'}" @click="select0">매칭목록</span></router-link>
        <router-link v-if="isLoggedIn" class="text-dark me-5 text-decoration-none" :to="{ name: 'profile' }"><span :class="{'selected': clicked === 'profile'}" @click="select1">{{currentUser.username}}의 프로필</span></router-link>
        <router-link v-if="isLoggedIn" class="text-dark me-5 text-decoration-none" :to="{ name: 'personalstat' }"><span :class="{'selected': clicked === 'stat'}" @click="select2">{{currentUser.username}}의 통계</span></router-link>
        <router-link v-if="!isLoggedIn" class="text-dark text-decoration-none" :to="{ name: 'login' }">로그인/회원가입</router-link>
        <router-link v-if="isLoggedIn" class="text-dark text-decoration-none" :to="{ name: 'logout' }">로그아웃</router-link>
<!-- =======
        <a class="text-black me-5 text-decoration-none" href="/">홈</a>
        <router-link style="font-family: 'GangwonEdu_OTFBoldA';" v-if="isLoggedIn" class="text-black me-5 text-decoration-none" :to="{ name: 'chatlist' }">매칭목록</router-link>
        <router-link style="font-family: 'GangwonEdu_OTFBoldA';" v-if="isLoggedIn" class="text-black me-5 text-decoration-none" :to="{ name: 'profile' }">{{currentUser.username}}의 프로필</router-link>
        <router-link style="font-family: 'GangwonEdu_OTFBoldA';" v-if="isLoggedIn" class="text-black me-5 text-decoration-none" :to="{ name: 'personalstat' }">{{currentUser.username}}의 통계</router-link>
        <router-link style="font-family: 'GangwonEdu_OTFBoldA';" v-if="!isLoggedIn" class="text-black text-decoration-none" :to="{ name: 'login' }">로그인/회원가입</router-link>
        <router-link style="font-family: 'GangwonEdu_OTFBoldA';" v-if="isLoggedIn" class="text-black text-decoration-none" :to="{ name: 'logout' }">로그아웃</router-link>
>>>>>>> front -->
      </div>
    </div>
  </nav>
  <hr class="hr1">
</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
  name: 'NavBar',
  data () {
    return {
      clicked: ''
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn', 'currentUser'])
  },
  methods: {
    ...mapActions(['fetchCurrentUser']),
    select0 () {
      this.clicked = 'match'
      console.log(this.clicked)
    },
    select1 () {
      this.clicked = 'profile'
      console.log(this.clicked)
    },
    select2 () {
      this.clicked = 'stat'
      console.log(this.clicked)
    }
  },
  created () {
    this.fetchCurrentUser()
  }
}
</script>

<style scoped>
*{
  font-family: 'GangwonEdu_OTFBoldA' !important;
}

.nav-box {
  width: 1200px;
  margin: 0 auto;
  position: relative;
  color : #282627 ;
}

.hr1{
  height: 4px !important;
  background-color:#282627  !important;
}

.font-head {
  font-size: 30px;
  color : #282627;
  font-weight: bold;
}

.font-normal {
  font-size: 20px;
  color : #282627;
  font-weight: bold;
}

.nav-padding {
  padding-top: 30px;
  right: 3%;
  position: absolute;
}

.dropdown-style {
  margin-right: 500px;
  padding-bottom: 0px;
  margin-bottom: 0px;
}

#logo {
  width : 30px;
  height: 30px;
}

.selected {
  color: #B9729E;
}
</style>
