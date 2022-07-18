<template>
  <div>
    <h1>Home</h1>
    <p v-if="isLoggedIn">Hi, {{ currentUser.username }}</p>
  </div>
</template>

<script>
// @ is an alias to /src
// import axios from 'axios'
import { mapGetters } from 'vuex'

export default {
  name: 'HomeView',
  computed: {
    ...mapGetters(['currentUser', 'isLoggedIn'])
  }
}
// created () {
//   const searchParams = new URLSearchParams(location.search)
//   for (const param of searchParams) {
//     if (param[0] === 'code') {
//       var code = param[1]
//     } else {
//       var state = param[1]
//     }
//   }
//   axios({
//     url: 'https://nid.naver.com/oauth2.0/token',
//     method: 'get',
//     headers: {
//       grant_type: 'authorization_code',
//       client_id: 'w8_s1fcXM3yxRgEKy4yI',
//       client_secret: 'A7646UmvlH',
//       code: code,
//       state: state
//     }
//   })

// methods: {
//   ...mapActions(['fetchCurrentUser'])
// },
// created () {
//   this.fetchCurrentUser()
// }
// }
</script>
