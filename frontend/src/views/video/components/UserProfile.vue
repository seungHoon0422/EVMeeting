<template>
  <div>
    <h2>stranger : {{strangerNickname}}</h2>
    <img :src="`${strangerProfile}`">
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
      strangerId: '',
      strangerNickname: ''
    }
  },
  methods: {
    getProfile () {
      axios.post(api.video.getStrangerProfile(), this.strangerName).then(res => {
        console.log(res)
        this.strangerId = res.data.id
        this.strangerNickname = res.data.username
        this.$emit('sendStarngerId', this.strangerId)
        this.strangerProfile = res.data.photo
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
