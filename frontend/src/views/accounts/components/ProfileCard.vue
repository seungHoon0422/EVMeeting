<template>
  <div>
    <div class="box">
      <!-- <h1 class="font-style">profileCard</h1> -->
      <div class="img-box1">
        <img v-if="currentUser.photo" :src="currentUser.photo" alt="..">
        <button v-else @click="goUpload" class="btn-style">사진 등록하기</button>
      </div>
      <table class="table">
        <thead>
          <tr class="">
            <th scope="row" class="fs-5" colspan="2">{{currentUser.username}} ({{currentUserAge}})</th>
            <!-- <td></td> -->
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row">키</th>
            <td>{{currentUser.height}} cm</td>
          </tr>
          <tr>
            <th scope="row">몸무게</th>
            <td>{{currentUser.weight}} kg</td>
          </tr>
          <tr>
            <th scope="row">관심사</th>
            <td>{{currentUser.hobby}}</td>
          </tr>
          <tr>
            <th scope="row">MBTI</th>
            <td>{{currentUser.mbti}}</td>
          </tr>
          <tr>
            <th scope="row">음주</th>
            <td v-if="currentUser.drink == 'zero'">소주 한 잔</td>
            <td v-else-if="currentUser.drink == 'under1'">소주 1병 미만</td>
            <td v-else-if="currentUser.drink == 'under3'">소주 1-3병</td>
            <td v-else-if="currentUser.drink == 'over3'">소주 3병 이상</td>
          </tr>
          <tr>
            <th scope="row">흡연</th>
            <td v-if="currentUser.cigarette == 'smoke'">흡연</td>
            <td v-else-if="currentUser.cigarette == 'nosmoke'">비흡연</td>
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
import router from '@/router'

export default {
  name: 'ProfileCard',
  methods: {
    goUpload () {
      router.push({ name: 'upload' })
    }
  },
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
  color: #FAFAFA;
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
  background-color: #FAFAFA;
}

.table {
  background-color: transparent;
  color: #FAFAFA;
  /* border: 1px solid #F88F6D; */
}

table, tr, td, th {
 border:none;
 text-align: center;
}

.btn-style {
  color: #FAFAFA;
  padding: 5px 20px;
  background-color: transparent;
  border: 1px solid #FAFAFA;
  border-radius: 5px;
  margin: 200px 150px;
}

button:hover {
  color: gray;
  background-color: white;
}
</style>
