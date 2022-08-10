<template>
  <video autoplay style="width: 100%; max-width: fit-content;"/>
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
