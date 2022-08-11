<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="position-relative col-auto" style="bottom: 450px; left: 8px;">
          <img :src="`${strangerProfile}`" style="width: 350px; height: 250px;">
        </div>
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
      strangerUserId: '',
      strangerAge: 0
    }
  },
  methods: {
    getStrangerAge (strangerBirthDate) {
      const today = new Date()
      const a = (strangerBirthDate || '').split('-')
      const birthDate = new Date(a[0], a[1], a[2])
      this.strangerAge = today.getFullYear() - birthDate.getFullYear()
    },
    getProfile () {
      setTimeout(() => {
        axios.post(api.video.getStrangerProfile(), this.strangerName).then(res => {
          console.log(res)
          this.strangerId = res.data.id
          this.strangerUserId = res.data.userid
          this.strangerNickname = res.data.username
          const strangerBirthDate = res.data.birth
          this.getStrangerAge(strangerBirthDate)
          this.$emit('sendStarngerId', this.strangerId)
          this.$emit('sendStrangerObject', this.strangerUserId)
          this.$emit('sendStrangerNickname', this.strangerNickname)
          this.$emit('sendStrangerUserid', this.strangerName)
          this.$emit('sendStrangerAge', this.strangerAge)
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
