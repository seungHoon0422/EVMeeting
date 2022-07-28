<template>
  <div>
    <h1>It's question List</h1>
    <h1>{{questionList}}</h1>
  </div>
</template>

<script>

export default ({
  data () {
    return {
      questionList: [
        { question: 'ESFP' },
        { question: '서울시 은평구' },
        { question: '술 조금' },
        { question: '재밌는 사람' }
      ]
    }
  },
  methods: {
    ShowQuestion () {
      console.log(this.questionList)
    }
  }
})
</script>
