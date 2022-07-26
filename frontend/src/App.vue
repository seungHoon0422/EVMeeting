<template id="back">
  <div id="app">
    <router-view class="container"/>
    <div class="app__brand">
      <p @click="moveToHome" class="app__logo">
        엘레베이터에서우린사랑을나누지
      </p>
      <div class="app__copyright"></div>
    </div>
  </div>
</template>

<script>
<<<<<<< HEAD
=======
import NavBar from './components/NavBar'

>>>>>>> 18c6715a8ed890a0d0348b46b3cc1f61117d6914
export default {
  methods: {
    moveToHome() {
      this.$router.push({ name: "login" });
      //this.$router.go(this.$router.currentRoute);
    },
  },
};
</script>

<style>
<<<<<<< HEAD
@import url("https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css");


* {
  font-family: Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto,
    "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR",
    "Malgun Gothic", sans-serif;
}

body {
  background-color: #f7ee73;
}

.container {
  width: 375px;
  height: 812px;
  background-color: #ffffff;
  margin: 5rem auto 0rem;
  border-radius: 1.5rem;
  box-shadow: 0px 1px 20px #9c9cc855;
=======
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-color: #FFFFEA;
  height: 1500px;
}

nav {
  padding: 20px;
}

nav a {
  font-weight: bold;
  /* color: #2c3e50; */
>>>>>>> 18c6715a8ed890a0d0348b46b3cc1f61117d6914
}

.app__brand {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.app__logo {
  font-family: "Golden Plains - Demo";
  font-size: 30px;
  text-decoration: none;
  margin-top: 10px;
  margin-bottom: -5px;
  color: #ffffff;
}

.app__copyright {
  font-size: 10px;
  color: #ffffff;
}
</style>
