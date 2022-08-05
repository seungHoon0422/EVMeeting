<template>
  <div>
    <h1>Stranger : {{this.strangerName}}</h1>
    <h1>{{this.strangerProfile}}</h1>
    <h1>{{this.countTogether}}</h1>
    <h1>{{this.ProfileCount}}</h1>
  </div>
</template>
<script>
import axios from 'axios'

export default {
  props: {
    stranger: {
      type: String
    },
    countTogether: {
      type: Number
    }
  },
  data () {
    return {
      strangerName: this.stranger.split('"')[3],
      strangerProfile: '',
      profileList: [
        'hobby',
        'mbti',
        'school',
        'drink',
        'cigarette'
      ]
    }
  },
  computed: {
    ProfileCount () {
      return this.countTogether + 1
    }
  },
  methods: {
    getProfile () {
      axios.post('http://localhost:8080/api/v1/meeting/userinfo', this.strangerName).then(res => {
        const number = this.ProfileCount - 3
        const pick = this.profileList[number]
        this.strangerProfile = res.data[pick]
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
