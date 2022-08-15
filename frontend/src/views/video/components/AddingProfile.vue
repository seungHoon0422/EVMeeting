<template>
  <div>
    <button @click="[profileOnOff(),buttonSwitch()]" class="btn btn-danger" id="buttonIcon"  v-bind:disabled="buttonOff==true">
      <i class='bx bxs-chevron-up-circle mx-2' style="font-size: 50px; color : blue;"></i>
    </button>
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
    },
    profileopencount () {
      if (this.profileopencount === 10) {
        this.buttonOff = true
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

<style>
#buttonIcon{
  background-color:transparent;
  border: 0;
  outline: 0;
}

</style>
