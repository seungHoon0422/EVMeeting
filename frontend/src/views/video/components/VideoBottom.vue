<template>
  <div>
    <div v-if="this.sessionLevel===3">
      <!-- <h1>AudioControl</h1> -->
      <button class="btn btn-large btn-danger" type="button" @click="audioOnOff">음소거</button>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
export default {
  name: 'video-bottom',
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
  },
  mounted () {
    this.audioOnOff()
  }
}
</script>
