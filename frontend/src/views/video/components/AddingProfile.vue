<template>
  <div>
    <h1>Want to see your profile</h1>
    <button @click="profileOnOff" class="btn btn-danger" v-bind:disabled="buttonOff==true">WantToSee</button>
  </div>
</template>

<script>
import { reactive } from 'vue'
export default {
  props: {
    profileopencount: {
      type: Number
    },
    session: {
      type: String
    }
  },
  data () {
    return {
      buttonOff: false
    }
  },
  name: 'adding-profile',
  setup (props, { emit, data }) {
    const state = reactive({
      showprofile: true
    })

    const profileOnOff = (data) => {
      if (state.showprofile) {
        state.showprofile = false
      } else {
        state.showprofile = true
      }
      console.log(state.showprofile)
      // Sending Signal
      props.session.signal({
        data: '',
        to: [],
        type: 'profileopencount'
      })
      emit('profileOnOff', {
        showprofile: state.showprofile
      })
    }
    return {
      profileOnOff
    }
  }
}
</script>
