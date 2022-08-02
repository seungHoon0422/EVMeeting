<template>
  <div>
    <h2>stranger : {{strangerName}}</h2>
  </div>
</template>

<script>
export default {
  props: {
    currentUser: {
      type: Object
    },
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
