<template id="back">
  <div id="app">
    <nav-bar v-if="!isChecked"></nav-bar>
    <transition
      mode="out-in"
      enter-active-class="animate__animated animate__fadeIn"
      leave-active-class="animate__animated animate__fadeOut"
    >
      <router-view/>
    </transition>
  </div>
</template>

<script>
import NavBar from '@/views/accounts/components/NavBar'

export default {
  components: {
    NavBar
  },
  data () {
    return {
      isChecked: false
    }
  },
  created () {
    if (document.location.pathname === '/') {
      this.isChecked = true
      // console.log('ische:', this.isChecked)
    } else {
      this.isChecked = false
    }
  }
}
</script>

<style>
@font-face {
    font-family: 'GangwonEdu_OTFBoldA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

#app {
  font-family: 'GangwonEdu_OTFBoldA', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* background-color: white; */
/* <<<<<<< HEAD */
  height: 1200px;
  /* background-color : #f5f5f5; */
  /* background-color : #77a094; */
  /* background-image: linear-gradient( rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2) ), url(@/img/scale.jpg); */
/* =======
  height: 150vh;
  background-image: linear-gradient( rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2) ), url(@/img/scale.jpg);
>>>>>>> feature-statistics */
  background-repeat : no-repeat;
  background-size : cover;
  background-attachment: fixed;
  background-image: url('https://wordpress-network.prod.aws.skyscnr.com/wp-content/uploads/2018/05/GettyImages-470434611.jpg');
}

nav {
  padding: 20px;
}

nav a {
  font-weight: bold;
  /* color: #2c3e50; */
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
