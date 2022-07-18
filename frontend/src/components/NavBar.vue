<template>
  <!-- <nav class="navbar navbar-expand-md bg-light mb-3">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Navbar</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarToggleExternalContent">
        <span class="navbar-toggler-icon btn-dark"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
          <router-link class="nav-link" :to="{ name: 'home' }">홈</router-link>
          <router-link v-if="!isLoggedIn" class="nav-link" :to="{ name: 'login' }">로그인/회원가입</router-link>
          <router-link v-if="isLoggedIn" class="nav-link" :to="{ name: 'logout' }">로그아웃</router-link>
        </div>
      </div>
    </div>
  </nav> -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">A708</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse d-flex justify-content-end" id="navbarTogglerDemo02">
      <router-link class="nav-link text-dark" :to="{ name: 'home' }">홈</router-link>
      <router-link class="nav-link text-dark" :to="{ name: 'home' }">미팅참가</router-link>
      <router-link v-if="!isLoggedIn" class="nav-link text-dark" :to="{ name: 'login' }">로그인/회원가입</router-link>
      <router-link v-if="isLoggedIn" class="nav-link text-dark" :to="{ name: 'logout' }">로그아웃</router-link>
    </div>
  </div>
</nav>

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

</style>
