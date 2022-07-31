<template>
  <div>
    <div class="box">
      <!-- <h1 class="font-style">profileCard</h1> -->
      <div class="img-box1">
        <img src="@/img/iu.jpg" alt="..">
      </div>
      <table class="table">
        <thead>
          <tr class="table-secondary">
            <th scope="row" class="fs-5">{{currentUser.username}} ({{currentUserAge}})</th>
            <td></td>
          </tr>
        </thead>
        <tbody>
          <tr class="table-primary">
            <th scope="row">키</th>
            <td>{{currentUser.height}} cm</td>
          </tr>
          <tr class="table-success">
            <th scope="row">몸무게</th>
            <td>{{currentUser.weight}} kg</td>
          </tr>
          <tr class="table-danger">
            <th scope="row">관심사</th>
            <td>{{currentUser.hobby}}</td>
          </tr>
          <tr class="table-warning">
            <th scope="row">MBTI</th>
            <td>{{currentUser.mbti}}</td>
          </tr>
          <tr class="table-info">
            <th scope="row">음주</th>
            <td v-if="currentUser.drink == 'nodrink'">아예 안 마심</td>
            <td v-else-if="currentUser.drink == 'solodrink'">혼술할 정도로 좋아함</td>
            <td v-else-if="currentUser.drink == 'frienddrink'">친구들 만날 때만</td>
            <td v-else-if="currentUser.drink == 'nownodrink'">현재 금주 중</td>
          </tr>
          <tr class="table-light">
            <th scope="row">흡연</th>
            <td v-if="currentUser.cigarette == 'smoke'">흡연</td>
            <td v-else-if="currentUser.cigarette == 'nosmoke'">비흡연</td>
          </tr>
          <tr class="table-dark">
            <th scope="row">자기소개</th>
            <td colspan="3">{{currentUser.description}}</td>
          </tr>
        </tbody>
      </table>
      <!-- <div class="description">
        <p><i class="fa-solid fa-image-portrait"></i> {{currentUser.username}} | {{currentUserAge}}세 </p>
        <div class="d-flex">
          <p class="me-5"><i class="fa-solid fa-person-arrow-up-from-line"></i> {{currentUser.height}}cm</p>
          <p><i class="fa-solid fa-weight-scale"></i> {{currentUser.weight}}kg</p>
        </div>
        <hr>
        <div class="d-flex">
          <p class="me-5"><i class="fa-solid fa-magnifying-glass-plus"></i> {{currentUser.hobby}}</p>
          <p><i class="fa-solid fa-atom"></i> {{currentUser.mbti}}</p>
        </div>
        <div class="d-flex">
          <p class="me-5"><i class="fa-solid fa-champagne-glasses"></i> {{currentUser.drink}}</p>
          <p><i class="fa-solid fa-smoking"></i>
            <span v-if="currentUser.cigarette">{{currentUser.cigarette}}</span>
            <span v-else> 미입력 </span>
          </p>
        </div>
        <hr>
        <p><i class="fa-solid fa-quote-left"></i> {{currentUser.description}}</p>
      </div> -->
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'ProfileCard',
  computed: {
    ...mapGetters(['currentUser', 'currentUserAge'])
  }
}
</script>

<style scoped>
.box {
  text-align: left;
  /* border: 3px solid #F88F6D; */
  width: 450px;
  height: 300px;
  margin-right: 10px;
}

.font-style {
  font-size: 30px;
  color: #F88F6D;
}

.img-box1 {
  width: 450px;
  height: 400px;
}

img {
  width:100%;
  height:100%;
}

.description {
  padding: 20px;
  font-size: 20px;
  background-color: white;
}

.table {
  background-color: #FFFFEA;
  /* border: 1px solid #F88F6D; */
}

</style>
