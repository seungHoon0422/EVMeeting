<template>
  <div>
    <h1>Want to see your profile</h1>
    <button @click="profileOnOff" class="btn btn-danger" v-bind:disabled="showprofile==true">WantToSee</button>
  </div>
</template>

<script>
import { reactive } from 'vue'
export default {
  props: {
    profileopencount: {
      type: Number
    }
  },
  data () {
    return {
      buttonOnOff: 0
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
