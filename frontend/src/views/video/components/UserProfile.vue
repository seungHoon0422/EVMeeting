<template>
  <div>
    <h2>stranger : {{strangerName}}</h2>
    <h4>{{strangerProfile}}</h4>
    <h1>{{strangerId}}</h1>
  </div>
</template>

<script>
import axios from 'axios'
import api from '@/api/api'

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
      axios.post(api.video.getStrangerProfile(), this.strangerName).then(res => {
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
