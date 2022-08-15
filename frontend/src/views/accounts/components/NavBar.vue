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
        <a class="text-black me-5 text-decoration-none" href="/">홈</a>
        <router-link style="font-family: 'GangwonEdu_OTFBoldA';" v-if="isLoggedIn" class="text-black me-5 text-decoration-none" :to="{ name: 'chatlist' }">매칭목록</router-link>
        <router-link style="font-family: 'GangwonEdu_OTFBoldA';" v-if="isLoggedIn" class="text-black me-5 text-decoration-none" :to="{ name: 'profile' }">{{currentUser.username}}의 프로필</router-link>
        <router-link style="font-family: 'GangwonEdu_OTFBoldA';" v-if="isLoggedIn" class="text-black me-5 text-decoration-none" :to="{ name: 'personalstat' }">{{currentUser.username}}의 통계</router-link>
        <router-link style="font-family: 'GangwonEdu_OTFBoldA';" v-if="!isLoggedIn" class="text-black text-decoration-none" :to="{ name: 'login' }">로그인/회원가입</router-link>
        <router-link style="font-family: 'GangwonEdu_OTFBoldA';" v-if="isLoggedIn" class="text-black text-decoration-none" :to="{ name: 'logout' }">로그아웃</router-link>
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
  computed: {
    ...mapGetters(['isLoggedIn', 'currentUser'])
  },
  methods: {
    ...mapActions(['fetchCurrentUser'])
  },
  created () {
    this.fetchCurrentUser()
  }
}
</script>

<style>
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
</style>
