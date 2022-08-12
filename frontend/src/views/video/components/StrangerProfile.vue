<template>
  <div>
    <div>
      <div v-if="this.subject==='mbti'">
        <i class='bx bx-smile' style="font-size: 50px; color:#B9729E;"></i>
        <h1 style="color:#B9729E; font-weight: bold;">MBTI</h1>
      </div>
      <div v-else-if="this.subject==='drink'">
        <i class='bx bx-happy-alt' style="font-size: 50px; color:#B9729E;"></i>
        <h1 style="color:#B9729E; font-weight: bold;">주량</h1>
      </div>
      <div v-else-if="this.subject==='description'">
        <i class='bx bx-happy-heart-eyes' style="font-size: 50px; color:#B9729E;"></i>
        <h1 style="color:#B9729E; font-weight: bold;">About Me</h1>
      </div>
    </div>
    <div>
      <div v-if="this.strangerProfile==='zero'">
        <h3 class="my-3" style="font-family : 'GangwonEdu_OTFBoldA';">안 좋아해요</h3>
      </div>
      <div v-else-if="this.strangerProfile==='under1'">
        <h2 class="my-3">
          <i class="fa-solid fa-bottle-droplet" id="bottle"></i>
        </h2>
      </div>
      <div v-else-if="this.strangerProfile==='under2'">
        <h2 class="my-3">
          <i class="fa-solid fa-bottle-droplet" id="bottle"></i>
          <i class="fa-solid fa-bottle-droplet" id="bottle"></i>
        </h2>
      </div>
      <div v-else-if="this.strangerProfile==='over3'">
        <h2 class="my-3">
          <i class="fa-solid fa-bottle-droplet" id="bottle"></i>
          <i class="fa-solid fa-bottle-droplet" id="bottle"></i>
          <i class="fa-solid fa-bottle-droplet" id="bottle"></i>
        </h2>
      </div>
      <div v-else>
        <h3 class="my-3" style="font-family : 'GangwonEdu_OTFBoldA';">{{this.strangerProfile}}</h3>
      </div>
    </div>
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

<style>
#bottle{
  color : #282627;
}
</style>
