<template>
  <div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      url: 'https://images.unsplash.com/photo-1621343607959-5d11ff0f1e39?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDF8fHxlbnwwfHx8fA%3D%3D&w=1000&q=80',
      urlList: [
        'https://images.unsplash.com/photo-1554118811-1e0d58224f24?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2FmZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60',
        'https://images.unsplash.com/photo-1463797221720-6b07e6426c24?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTN8fGNhZmV8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60'
      ]
    }
  },
  methods: {
    changeUrl (randomValue) {
      // const randomValue = this.questionList[Math.floor(Math.random() * this.questionList.length)]
      // console.log(randomValue)
      this.url = this.urlList[randomValue]
      this.$emit('url', this.url)
    }
  }

}
</script>
