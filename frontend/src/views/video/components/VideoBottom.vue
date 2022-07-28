<template>
  <div>
    <h1>AudioControl</h1>
    <button @click="audioOnOff">audio</button>
  </div>
</template>

<script>
import { reactive } from 'vue'
export default {
  name: 'video-bottom',
  setup (props, { emit }) {
    const state = reactive({
      audio: true
    })

    const audioOnOff = () => {
      if (state.audio) {
        state.audio = false
      } else {
        state.audio = true
      }
      console.log(state.audio)
      emit('audioOnOff', {
        audio: state.audio
      })
    }

    return {
      audioOnOff
    }
  }
}
</script>
