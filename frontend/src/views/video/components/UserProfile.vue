<template>
  <div>
    <h2>stranger : {{strangerName}}</h2>
    <h4>{{strangerProfile}}</h4>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  props: {
    currentUser: {
      type: Object
    },
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
        console.log(res)
        this.strangerProfile = res.data.height
      }).catch(err => {
        console.log(err)
      })
    }
  },
  mounted () {
    this.getProfile()
  }
}
</script>
