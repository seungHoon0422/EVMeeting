<template>
  <video autoplay style="width: 100%; height:300px; max-width: fit-content; border-radius: 20px;"/>
</template>

<script>
export default {
  name: 'OvVideo',

  props: {
    streamManager: Object
  },

  mounted () {
    this.streamManager.addVideoElement(this.$el)
  }
}
</script>
