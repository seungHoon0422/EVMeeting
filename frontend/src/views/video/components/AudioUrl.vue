<template>
  <div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      file: '@/img/pianomoment.mp3',
      fileList: [
        '@/img/silence.mp3',
        '@/img/pianomoment.mp3',
        '@/img/pianomoment.mp3'
      ]
    }
  },
  methods: {
    changeFile (randomValue) {
      // const randomValue = this.questionList[Math.floor(Math.random() * this.questionList.length)]
      // console.log(randomValue)
      this.file = this.fileList[randomValue]
      this.$emit('file', this.file)
    }
  }

}
</script>
