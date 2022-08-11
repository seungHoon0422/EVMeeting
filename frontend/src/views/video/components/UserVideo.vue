<template>
<div v-if="streamManager">
  <div v-if="this.sessionLevel===2">
    <div class="userVideoContainer mx-3" id="UserVideoSession2">
      <ov-video :stream-manager="streamManager"/>
    </div>
  </div>
  <div v-else>
    <div class="userVideoContainer mx-3" id="UserVideo">
      <ov-video :stream-manager="streamManager"/>
    </div>
  </div>
  <div><p>{{ clientData }}</p></div>
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
    streamManager: Object,
    sessionLevel: {
      type: Number
    }
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

#UserVideoSession2{
  width: 400px;
  height: 300px;
}
</style>
