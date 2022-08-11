<template>
  <div>
    <button class="btn btn-primary" @click="randomButton" style="background-color:#B9729E; border: none;">대화 주제</button>
  </div>
</template>

<script>
export default {
  props: {
    session: {
      type: Object
    },
    tenseconds: {
      type: Number
    }
  },
  name: 'randomButton',
  setup (props, { emit }) {
    const randomButton = () => {
      // Sending Signal
      props.session.signal({
        data: '',
        to: [],
        type: 'randomButton'
      })
    }
    return {
      randomButton
    }
  },
  watch: {
    tenseconds () {
      if (this.tenseconds % 60 === 0) {
        this.randomButton()
      }
    }
  }
}
</script>
