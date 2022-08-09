<template>
  <div>
    <!-- <h2>stranger : {{strangerNickname}}</h2> -->
    <div class="container">
      <div class="position-relative" style="bottom: 550px; left: 8px;">
        <img :src="`${strangerProfile}`" style="width: 400px; height: 300px;">
      </div>
    </div>
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
      strangerNickname: '',
      strangerUserId: ''
    }
  },
  methods: {
    getProfile () {
      setTimeout(() => {
        axios.post(api.video.getStrangerProfile(), this.strangerName).then(res => {
          console.log(res)
          this.strangerId = res.data.id
          this.strangerUserId = res.data.userid
          this.strangerNickname = res.data.username
          this.$emit('sendStarngerId', this.strangerId)
          this.$emit('sendStrangerObject', this.strangerUserId)
          this.$emit('sendStrangerNickname', this.strangerNickname)
          this.strangerProfile = res.data.photo
        }).catch(err => {
          console.log(err)
        })
      }, 1000)
    }
  },
  mounted () {
    this.getProfile()
  }
}
</script>

<style>
img {
  max-width: 100%;
  height: auto !important;
}
</style>
