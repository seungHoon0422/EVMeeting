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
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* background-color: white; */
  height: 150vh;
  background-image: linear-gradient( rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.2) ), url(@/img/scale.jpg);
  background-repeat : no-repeat;
  background-size : cover;
  background-attachment: fixed;
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
