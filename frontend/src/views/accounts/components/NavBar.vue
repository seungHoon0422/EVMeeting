<template>
<div class="nav-box">
  <nav class="d-flex justify-content-center pb-0 font-normal navbar">
    <div class="container-fluid">
      <a class="text-light me-5 text-decoration-none" href="/">
        <p class="mb-0"><span class="font-head">엘</span>리베이터에서 우린</p>
        <p class="mb-0 text-start"><span class="font-head">사</span>랑을 나누지</p>
      </a>
      <div class="nav-padding">
        <a class="text-light me-5 text-decoration-none" href="/">홈</a>
        <router-link v-if="isLoggedIn" class="text-light me-5 text-decoration-none" :to="{ name: 'profile' }">{{currentUser.username}}의 프로필</router-link>
        <router-link v-if="!isLoggedIn" class="text-light text-decoration-none" :to="{ name: 'login' }">로그인/회원가입</router-link>
        <router-link v-if="isLoggedIn" class="text-light text-decoration-none" :to="{ name: 'logout' }">로그아웃</router-link>
        <router-link v-if="isLoggedIn" class="text-light text-decoration-none" :to="{ name: 'chatrooms' }">매칭목록</router-link>
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
}

.hr1{
  height: 5px !important;
  background-color: white !important;
}

.font-head {
  font-size: 30px;
}

.font-normal {
  font-size: 20px;
}

.nav-padding {
  padding-top: 60px;
  right: 3%;
  position: absolute;
}

.dropdown-style {
  margin-right: 500px;
  padding-bottom: 0px;
  margin-bottom: 0px;
}
</style>
