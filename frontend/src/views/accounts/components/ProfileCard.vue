<template>
  <div>
    <div class="box">
      <!-- <h1 class="font-style">profileCard</h1> -->
      <div class="img-box">
        <img src="@/img/iu.jpg" alt="..">
      </div>
      <p>{{currentUser.username}} | {{currentUser.birth}}세</p>
      <p>키: {{currentUser.height}}cm</p>
      <p>몸무게: {{currentUser.weight}}kg</p>
      <br>
      <p>관심사: {{currentUser.hobby}}</p>
      <p>MBTI: {{currentUser.mbti}}</p>
      <p>음주: {{currentUser.drink}}</p>
      <p>흡연: {{currentUser.cigarette}}</p>
      <br>
      <p>자기소개 : {{currentUser.description}}</p>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'ProfileCard',
  computed: {
    ...mapGetters(['currentUser'])
  }
}
</script>

<style scoped>
.box {
  border: 3px solid #F88F6D;
  width: 350px;
  height: 600px;
  margin-right: 10px;
}

.font-style {
  font-size: 30px;
  color: #F88F6D;
}

.img-box {
  background-color: white;
  width: 345px;
  height: 200px;
}

img {
  width:100%;
  height:100%;
}
</style>
