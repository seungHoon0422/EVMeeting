<template>
  <div>
    <div v-if="this.subject==='mbti'">
      <i class='bx bx-smile' style="font-size: 50px;"></i>
      <h1>저의 MBTI 는</h1>
    </div>
    <div v-else-if="this.subject==='drink'">
      <i class='bx bx-happy-alt' style="font-size: 50px;"></i>
      <h1>술은 이정도 마셔요!</h1>
    </div>
    <div v-else-if="this.subject==='description'">
      <i class='bx bx-happy-heart-eyes' style="font-size: 50px;"></i>
      <h1>저는 이런 사람 이에요</h1>
    </div>
    <h1>{{this.strangerProfile}}</h1>
  </div>
</template>
<script>
import axios from 'axios'
import api from '@/api/api'

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
        'mbti',
        'mbti',
        'drink',
        'drink',
        'description'
      ],
      subject: ''
    }
  },
  computed: {
    ProfileCount () {
      return this.countTogether + 1
    }
  },
  methods: {
    getProfile () {
      axios.post(api.video.getStrangerProfile(), this.strangerName).then(res => {
        console.log(res.data)
        const number = this.ProfileCount - 3
        const pick = this.profileList[number]
        this.subject = pick
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
