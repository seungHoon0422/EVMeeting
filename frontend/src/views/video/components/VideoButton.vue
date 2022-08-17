<template>
  <div>
    <div v-if="this.sessionLevel===3" style="width:100%; height: 100%;" @click="videoOnOff">
      <img :src="require('@/img/video-camera-alt.svg')" alt="비디오 끄기" id="videobutton" type="videobutton"  style="width:30px; height: 10px; margin-top: 5px;"/>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
export default {
  name: 'video-button',
  props: {
    sessionLevel: {
      type: Number
    }
  },
  setup (props, { emit }) {
    const state = reactive({
      video: false
    })
    const videoOnOff = () => {
      if (state.video) {
        state.video = false
        document.getElementById('videobutton').src = require('@/img/video-slash.svg')
      } else {
        state.video = true
        document.getElementById('videobutton').src = require('@/img/video-camera-alt.svg')
      }
      console.log(state.audio)
      emit('videoOnOff', {
        video: state.video
      })
    }
    return {
      videoOnOff
    }
  },
  mounted () {
    this.videoOnOff()
  }
}
</script>
