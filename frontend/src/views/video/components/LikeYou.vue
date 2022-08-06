<template>
  <div>
    <button class="" id="buttonIcon"
    @click="[likeYouButton(),buttonSwitch()]"
    v-bind:disabled="buttonOff==true">
    <i class='bx bxs-chevron-up-circle' style="font-size: 50px; color : blue;"></i>
    </button>
  </div>
</template>

<script>
import { reactive } from 'vue'

export default {
  props: {
    session: {
      type: Object
    },
    // 상대방과 나의 count 확인
    levelOneCount: {
      type: Number
    }
  },
  data () {
    return {
      buttonOff: false
    }
  },
  methods: {
    buttonSwitch () {
      if (this.levelOneCount % 2 === 0) {
        this.buttonOff = true
      }
    }
  },
  name: 'like-you',
  setup (props, { emit }) {
    const state = reactive({
      likeYou: true
    })
    const likeYouButton = () => {
      if (state.likeyou) {
        state.likeyou = false
      } else {
        state.likeyou = true
      }
      // Sending Signal
      props.session.signal({
        data: '',
        to: [],
        type: 'levelOne'
      })
      emit('likeYouButton', {
        likeyou: state.likeyou
      })
    }
    return {
      likeYouButton
    }
  }
}
</script>

<style>
#buttonIcon{
  background-color:transparent;
  border: 0;
  outline: 0;
}
</style>
