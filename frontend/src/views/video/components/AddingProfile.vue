<template>
  <div>
    <h1>Want to see your profile</h1>
    <button @click="[profileOnOff(),buttonSwitch()]" class="btn btn-danger"  v-bind:disabled="buttonOff==true">WantToSee</button>
    <!-- <button @click="buttonSwitch" class="btn btn-primary" v-bind:disabled="buttonOff==true">Checking</button> -->
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
      type: Object
    },
    countTogether: {
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
      if (this.profileopencount % 2 === 0) {
        this.buttonOff = true
      }
    }
  },
  watch: {
    countTogether () {
      if (this.countTogether !== 0) {
        this.buttonOff = false
      }
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
