<template>
<div v-if="streamManager">
<div class="userVideoContainer mx-3" id="UserVideo">
  <ov-video :stream-manager="streamManager"/>
</div>
<!-- <div><p>{{ clientData }}</p></div> -->
</div>
</template>

<script>
import OvVideo from './OvVideo'

export default {
  name: 'UserVideo',

  components: {
    OvVideo
  },

  props: {
    streamManager: Object
  },

  computed: {
    clientData () {
      const { clientData } = this.getConnectionData()
      return clientData
    }
  },

  methods: {
    getConnectionData () {
      const { connection } = this.streamManager.stream
      return JSON.parse(connection.data)
    }
  }
}
</script>

<style>
.userVideoContainer {
  position: relative;
  display: block;
  width : 50%;
  height : 50%;
}

</style>
