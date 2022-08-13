<template>
  <div>
    <div v-if="this.sessionLevel===3">
      <img :src="require('@/img/cross-circle.svg')" alt="음소거" id="audiobutton" type="audiobutton" @click="audioOnOff" style="width:30px; height:10px;"/>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
export default {
  name: 'audio-button',
  props: {
    sessionLevel: {
      type: Number
    }
  },
  setup (props, { emit }) {
    const state = reactive({
      audio: true
    })
    const audioOnOff = () => {
      if (state.audio) {
        state.audio = false
        document.getElementById('audiobutton').src = require('@/img/cross-circle.svg')
      } else {
        state.audio = true
        document.getElementById('audiobutton').src = require('@/img/volume.svg')
      }
      console.log(state.audio)
      emit('audioOnOff', {
        audio: state.audio
      })
    }
    return {
      audioOnOff
    }
  },
  mounted () {
    this.audioOnOff()
  }
}
</script>
