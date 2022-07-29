<template>
  <div>
    <div class="box">
      <!-- <h1 class="font-style">profileCard</h1> -->
      <div class="img-box1">
        <img src="@/img/iu.jpg" alt="..">
      </div>
      <table class="table">
        <thead>
          <tr>
            <th scope="col" class="fs-5">{{currentUser.username}}(32)</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row">키</th>
            <td>{{currentUser.height}}</td>
            <th scope="row">몸무게</th>
            <td>{{currentUser.weight}}</td>
          </tr>
          <tr>
            <th scope="row">관심사</th>
            <td>{{currentUser.hobby}}</td>
            <th scope="row">MBTI</th>
            <td>{{currentUser.mbti}}</td>
          </tr>
          <tr>
            <th scope="row">음주</th>
            <td>{{currentUser.drink}}</td>
            <th scope="row">흡연</th>
            <td>
              <span v-if="currentUser.cigarette">{{currentUser.cigarette}}</span>
              <span v-else> - </span>
            </td>
          </tr>
          <tr>
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
  width: 350px;
  height: 300px;
  margin-right: 10px;
}

.font-style {
  font-size: 30px;
  color: #F88F6D;
}

.img-box1 {
  width: 350px;
  height: 350px;
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
  background-color: white;
}

</style>
