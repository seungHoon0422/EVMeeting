<template>
  <div class="video-player">
    <video class="video" ref="video" loop>
      <source src="@/img/elevatorVideo.mp4" type="video/mp4">
    </video>
  </div>
</template>

<script>
export default {
  name: 'VideoPlayer',
  props: {
    mp4: String
  },
  methods: {
    videoPlay () {
      this.$refs.video.muted = true
      this.$refs.video.play()
    }
  },
  mounted () {
    this.videoPlay()
  }
}
</script>

<style>
.video{
  width: 950px;
  height: 700px;
}
</style>
