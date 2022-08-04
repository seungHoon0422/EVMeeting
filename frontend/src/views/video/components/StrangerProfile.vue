<template>
  <div>
    <h1>Stranger : {{this.strangerName}}</h1>
    <h1>{{StrangerProfile}}</h1>
  </div>
</template>
<script>
import axios from 'axios'

export default {
  props: {
    stranger: {
      type: String
    }
  },
  data () {
    return {
      strangerName: this.stranger.split('"')[3],
      strangerProfile: ''
    }
  },
  methods: {
    getProfile () {
      axios.post('http://localhost:8080/api/v1/meeting/userinfo', this.strangerName).then(res => {
        console.log(res.data.height)
        this.strangerProfile = res.data.height
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>
