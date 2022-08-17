<template>
<div v-if="streamManager">
  <div>
    <div class="userVideoContainer" id="UserVideo">
      <ov-video :stream-manager="streamManager"/>
    </div>
  </div>
  <div><p class="fs-3" style="font-family : 'GangwonEdu_OTFBoldA'; #b5b1ae;">{{ this.nickname }}</p></div>
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
    },
    nickname: {
      type: String
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
  max-width: 80%;
  margin: auto;
  border-radius: 10px;
  border-color: aquamarine;
}

#UserVideoSession2{
}
</style>
