<template>
<div class="nav-box">
  <nav class="d-flex justify-content-between pb-0">
    <router-link class="text-danger text-decoration-none" :to="{ name: 'home' }">
      <p class="mb-0"><span class="fs-4">엘</span>리베이터에서 우린</p>
      <p class="mb-0 text-start"><span class="fs-4">사</span>랑을 나누지</p>
    </router-link>
    <div class="pt-5">
      <router-link class="text-danger me-3 text-decoration-none" :to="{ name: 'home' }">홈</router-link>
      <router-link v-if="!isLoggedIn" class="text-danger text-decoration-none" :to="{ name: 'login' }">로그인/회원가입</router-link>
      <router-link v-if="isLoggedIn" class="text-danger text-decoration-none" :to="{ name: 'logout' }">로그아웃</router-link>
    </div>
  </nav>
  <hr class="hr1">
</div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'NavBar',
  computed: {
    ...mapGetters(['isLoggedIn'])
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
</style>
