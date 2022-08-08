<template>
<div class="nav-box">
  <nav class="d-flex justify-content-between pb-0 font-normal">
    <a class="text-danger me-5 text-decoration-none" href="/">
      <p class="mb-0"><span class="font-head">엘</span>리베이터에서 우린</p>
      <p class="mb-0 text-start"><span class="font-head">사</span>랑을 나누지</p>
    </a>
    <div class="nav-padding">
      <!-- <router-link class="text-danger me-5 text-decoration-none" :to="{ name: 'home' }">홈</router-link> -->
      <a class="text-danger me-5 text-decoration-none" href="/">홈</a>
      <router-link v-if="isLoggedIn" class="text-danger me-5 text-decoration-none" :to="{ name: 'profile' }">{{currentUser.username}}의 프로필</router-link>
      <router-link v-if="!isLoggedIn" class="text-danger text-decoration-none" :to="{ name: 'login' }">로그인/회원가입</router-link>
      <router-link v-if="isLoggedIn" class="text-danger text-decoration-none" :to="{ name: 'logout' }">로그아웃</router-link>
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
}

.hr1{
  height: 5px !important;
  background-color: #F90A0A !important;
}

.left {
  left: auto;
}

.font-head {
  font-size: 30px;
}

.font-normal {
  font-size: 20px;
}

.nav-padding {
  padding-top: 60px;
}
</style>
