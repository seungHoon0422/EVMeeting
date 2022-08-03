<template>
  <div>
    <h2>stranger : {{strangerName}}</h2>
    <button @click="getProfile()"> getProfile </button>
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
      strangerName: this.stranger.split('"')[3]
    }
  },
  methods: {
    getProfile () {
      axios.post('http://localhost:8080/api/v1/meeting/userinfo', this.strangerName).then(res => {
        console.log(res)
      }).then(res => {
        console.log(res)
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>
