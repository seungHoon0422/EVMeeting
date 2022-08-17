<template>
  <div>
    <div class="box">
      <div class="row">
        <div class="col-4">
          <div class="img-box1">
            <div class="d-flex justify-content-center align-items-center">
              <img v-if="currentUser.photo" :src="currentUser.photo" alt="..">
              <button v-else @click="goUpload" class="btn-style">사진 등록하기</button>
            </div>
            <div class="d-flex justify-content-center align-items-center">
              <router-link :to="{ name: 'editphoto' }"><button v-if="currentUser.photo" class="btn-style">사진 변경</button></router-link>
            </div>
          </div>
        </div>
        <div class="col-8">
          <table class="table">
            <thead>
              <tr class="">
                <th scope="row" class="fs-5" colspan="2">{{currentUser.username}} ({{currentUserAge}})</th>
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
          <div class="button-style d-flex justify-content-center">
            <!-- <router-link :to="{ name: 'editphoto' }"><button>사진 변경</button></router-link> -->
            <router-link :to="{ name: 'editprofile' }"><button class="btn-style">프로필 변경</button></router-link>
            <router-link :to="{ name: 'editpwd' }"><button class="btn-style">비밀번호 변경</button></router-link>
            <router-link :to="{ name: 'deleteprofile' }"><button class="btn-style">회원 탈퇴</button></router-link>
          </div>
        </div>
      </div>
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
  width: 100%;
  height: 100%;
  margin-right: 10px;
}

.font-style {
  font-size: 30px;
  color: #FAFAFA;
}

.img-box1 {
  /* width: 450px;
  height: 400px; */
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
  color: #000000;
  /* border: 1px solid #F88F6D; */
}

table, tr, td, th {
 border:none;
 text-align: center;
}

.btn-style {
  color: #000000;
  padding: 5px 20px;
  background-color: transparent;
  border: 2px solid #9a4c9e;
  border-radius: 5px;
  margin:20px;
  /* margin-left : auto;
  margin-right : auto; */
}

button:hover {
  color: rgb(0, 0, 0);
  background-color: rgb(203, 150, 210);
}
</style>
