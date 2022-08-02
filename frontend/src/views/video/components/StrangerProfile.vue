<template>
  <div>
    <h1>Stranger : {{this.strangerName}}</h1>
  </div>
</template>
<script>
export default {
  props: {
    stranger: {
      type: String
    }
  },
  data () {
    return {
      strangerName: this.stranger.split('"')[3]
    }
  }
}
</script>
