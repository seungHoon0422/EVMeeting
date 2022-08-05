<template>
  <div>
    <h2>stranger : {{strangerName}}</h2>
    <h4>{{strangerProfile}}</h4>
    <h1>{{strangerId}}</h1>
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
      strangerProfile: '',
      strangerId: ''
    }
  },
  methods: {
    getProfile () {
      axios.post('http://localhost:8080/api/v1/meeting/userinfo', this.strangerName).then(res => {
        console.log('hereeeeeeeeeeeeeeeeeeeeeeeeeeeeee')
        console.log(res)
        this.strangerId = res.data.id
        this.$emit('sendStarngerId', this.strangerId)
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
