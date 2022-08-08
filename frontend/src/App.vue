<template id="back">
  <div id="app">
    <nav-bar v-if="!isChecked"></nav-bar>
    <router-view/>
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
    } else {
      this.isChecked = false
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
  background-color: #FFFFEA;
  height: 1200px;
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
