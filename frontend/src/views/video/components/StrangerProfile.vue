<template>
  <div>
    <h1>Stranger : {{this.strangerName}}</h1>
    <h1>{{number}}</h1>
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
      strangerName: this.stranger.split('"')[3],
      number: Math.floor(Math.random() * 10 + 1)
    }
  }
}
</script>
