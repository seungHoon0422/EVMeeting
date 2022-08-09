<template>
  <div>
    <h1>{{this.subject}}</h1>
  </div>
</template>

<script>
export default {
  data () {
    return {
      subject: '',
      questionList: [
        '남녀 사이에 친구가 존재 할 수 있을까?',
        '전 애인과 친구가 될 수 있을까?',
        '내가 비흡연일때, 친구와 애인 둘이서 담배 피러 나가도 될까?',
        '본인이 술 안 마신 술자리에서 더치페이 해야 할까?',
        '애인의 바람을 알게된 날이 내 생일! 말을 해야 하나 말아야 하나?',
        '나를 좋아하는 사람 vs 내가 좋아하는 사람',
        '장거리 연애를 할 수 있는 최대 거리?',
        '데이트 통장 있으면 좋다 vs 너무 계산적이다',
        '애인의 부모님과의 만남 해야할까?',
        '애인의 연애 경험! 5년에 1명 vs 1년에 5명',
        '교통사고시!  내 차 박살 vs 애인 코 박살, 어떤게 더 가슴 아픈가?',
        '친구가 술에 취해 뻗었을 때, 이상형이 단둘이 술을 마시자고 하면 친구 버린다 vs 챙긴다',
        '만우절 장난 고백! 진심이다 vs 장난이다',
        '외모가 이상형인데 유머코드 안 맞는 애인 vs 외모가 이상형이 아닌데 유머코드 잘 맞는 애인'
      ]
    }
  },
  methods: {
    showQuestion (randomValue) {
      // const randomValue = this.questionList[Math.floor(Math.random() * this.questionList.length)]
      // console.log(randomValue)
      this.subject = this.questionList[randomValue]
      this.$emit('subject', this.subject)
    }
  }

}
</script>
