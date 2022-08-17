<template>
<div class="box d-flex justify-content-center">
  <div class="signup-form">
    <div class="mt-5 p-3 mb-5 rounded form-style">
      <h1>프로필 변경</h1>

      <!-- <account-error-list v-if="authError"></account-error-list> -->

      <form @submit.prevent="editProfile(credentials)" class="mt-5">
        <div class="d-flex justify-content-between">
          <div class="ms-3">
            <div class="d-flex justify-content-end">
              <label for="userid" class="me-3 pt-3">아이디</label>
              <input id="userid" v-model="credentials.userid" class="input-color rounded length height p-3 disabled" type="text" required disabled>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="height" class="me-3 pt-3">키</label>
              <input id="height" v-model="credentials.height" class="input-color rounded length height p-3" type="number" min=0 :placeholder="currentUser.height" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="weight" class="me-3 pt-3">몸무게</label>
              <input id="weight" v-model="credentials.weight" class="input-color rounded length height p-3" type="number" min=0 :placeholder="currentUser.weight" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="gender" class="me-3 pt-3">성별</label>
              <select name="gender" id="gender" class="length height">
                <!-- <option value="">성별을 선택해주세요</option> -->
                <option value="male" :selected="currentUser.gender=='male'?true:false">남성</option>
                <option value="female" :selected="currentUser.gender=='female'?true:false">여성</option>
              </select>
              <!-- <input id="gender" v-model="credentials.gender" class="input-color rounded length height p-3" type="text" placeholder="라디오버튼으로" required> -->
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="birth" class="me-3 pt-3">생년월일</label>
              <input id="birth" v-model="credentials.birth" class="input-color rounded length height p-3" type="date" :data-placeholder="currentUser.birth" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="cigarette" class="me-3 pt-3">흡연여부</label>
              <select name="cigarette" id="cigarette" class="length height">
                <!-- <option value="">-- 흡연여부 --</option> -->
                <option value="smoke" :selected="currentUser.cigarette=='smoke'?true:false">흡연</option>
                <option value="nosmoke" :selected="currentUser.cigarette=='nosmoke'?true:false">비흡연</option>
              </select>
              <!-- <input id="sigarette" v-model="credentials.sigarette" class="input-color rounded length height p-3" type="text" placeholder="흡연여부"> -->
            </div>
          </div>
          <div class="me-5">
            <div class="d-flex justify-content-end">
              <label for="username" class="me-3 pt-3">닉네임</label>
              <input id="username" v-model="credentials.username" class="input-color rounded length height p-3 disabled" type="text" :placeholder="currentUser.username" disabled>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <!-- <input id="mbti" v-model="credentials.mbti" class="input-color rounded length height p-3" type="text" :placeholder="currentUser.mbti"> -->
              <label for="mbti" class="me-3 pt-3">MBTI</label>
              <select v-model="credentials.mbti" name="mbti" id="mbti" class="length height" required>
                <!-- <option value=""> 선택하세요 </option> -->
                <!-- <option value="XXXX">잘 모름</option> -->
                <option value="ISTJ" :selected="currentUser.mbti=='ISTJ'?true:false">ISTJ</option>
                <option value="ISTP" :selected="currentUser.mbti=='ISTP'?true:false">ISTP</option>
                <option value="ISFJ" :selected="currentUser.mbti=='ISFJ'?true:false">ISFJ</option>
                <option value="ISFP" :selected="currentUser.mbti=='ISFP'?true:false">ISFP</option>
                <option value="INFJ" :selected="currentUser.mbti=='INFJ'?true:false">INFJ</option>
                <option value="INFP" :selected="currentUser.mbti=='INFP'?true:false">INFP</option>
                <option value="INTJ" :selected="currentUser.mbti=='INTJ'?true:false">INTJ</option>
                <option value="INTP" :selected="currentUser.mbti=='INTP'?true:false">INTP</option>
                <option value="ESTP" :selected="currentUser.mbti=='ESTP'?true:false">ESTP</option>
                <option value="ESTJ" :selected="currentUser.mbti=='ESTJ'?true:false">ESTJ</option>
                <option value="ESFP" :selected="currentUser.mbti=='ESFP'?true:false">ESFP</option>
                <option value="ESFJ" :selected="currentUser.mbti=='ESFJ'?true:false">ESFJ</option>
                <option value="ENFP" :selected="currentUser.mbti=='ENFP'?true:false">ENFP</option>
                <option value="ENFJ" :selected="currentUser.mbti=='ENFJ'?true:false">ENFJ</option>
                <option value="ENTP" :selected="currentUser.mbti=='ENTP'?true:false">ENTP</option>
                <option value="ENTJ" :selected="currentUser.mbti=='ENTJ'?true:false">ENTJ</option>
              </select>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="hobby" class="me-3 pt-3">관심사</label>
              <input id="hobby" v-model="credentials.hobby" class="input-color rounded length height p-3" type="text"  :placeholder="currentUser.hobby">
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="school" class="me-3 pt-3">학교</label>
              <input id="school" v-model="credentials.school" class="input-color rounded length height p-3" type="text" :placeholder="currentUser.school">
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="email" class="me-3 pt-3">이메일</label>
              <input id="email" v-model="credentials.email" class="input-color rounded length height p-3" type="email" :placeholder="currentUser.email">
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="drink" class="me-3 pt-3">주량</label>
              <select name="drink" id="drink" class="length height">
                <!-- <option value="">-- 주량 --</option> -->
                <option value="zero" :selected="currentUser.drink=='zero'?true:false">소주 한 잔</option>
                <option value="under1" :selected="currentUser.drink=='under1'?true:false">소주 1병 미만</option>
                <option value="under3" :selected="currentUser.drink=='under3'?true:false">소주 1-3병</option>
                <option value="over3" :selected="currentUser.drink=='over3'?true:false">소주 3병 이상</option>
              </select>
              <!-- <input id="drink" v-model="credentials.drink" class="input-color rounded length height p-3" type="text" placeholder="주량"> -->
            </div>
          </div>
        </div>
        <div class="mt-3">
          <p class="text-start self-margin">자기소개</p>
          <textarea v-model="credentials.description" name="self-intro" id="" cols="80" rows="5" class="input-color p-3" :placeholder="currentUser.description"></textarea>
        </div>
        <button type="submit" class="btn rounded length mt-4">변경 사항 저장</button>
      </form>
    </div>
  </div>
</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
// import AccountErrorList from '@/components/AccountErrorList'

export default {
  name: 'EditProfileView',
  // components: { AccountErrorList },
  data () {
    return {
      credentials: {
        userid: '',
        username: '',
        height: '',
        weight: '',
        gender: '',
        birth: '',
        hobby: '',
        mbti: '',
        school: '',
        email: '',
        drink: '',
        cigarette: '',
        description: ''
      }
    }
  },
  computed: {
    ...mapGetters(['authError', 'currentUser'])
  },
  methods: {
    ...mapActions(['signup', 'editProfile']),
    test (e) {
      console.log(e.target.value)
    }
  },
  created () {
    if (this.currentUser) {
      this.credentials.userid = this.currentUser.userid
      this.credentials.username = this.currentUser.username
      this.credentials.height = this.currentUser.height
      this.credentials.weight = this.currentUser.weight
      this.credentials.gender = this.currentUser.gender
      this.credentials.birth = this.currentUser.birth
      this.credentials.hobby = this.currentUser.hobby
      this.credentials.mbti = this.currentUser.mbti
      this.credentials.school = this.currentUser.school
      this.credentials.email = this.currentUser.email
      this.credentials.drink = this.currentUser.drink
      this.credentials.cigarette = this.currentUser.cigarette
      this.credentials.description = this.currentUser.description
    }
  }
}
</script>

<style scoped>
.length {
  width: 200px;
}

.height {
  height: 50px;
}

.signup-form {
  color: #FAFAFA;
  background-color: rgba(0,0,0,0.4);
  border-color: #FAFAFA;
  border: solid 5px;
  border-radius: 20px;
  width: 800px;
}

.btn {
  color: #FAFAFA;
  border: 2px solid #FAFAFA;
  background-color: transparent;
}

.input-color {
  border: solid 3px #FAFAFA;
  background-color: transparent;
}

.self-margin {
  margin-left: 40px;
}

select {
  border: 3px solid #FAFAFA;
  background-color: transparent;
  font-size: 14px;
  padding: 0 10px;
}

input[type='date']::before {
  content: attr(data-placeholder);
  width: 100%;
}

input, textarea, select {
  color: #FAFAFA;
  outline-color: yellowgreen;
}

/* option {
  background-color: rgba(0,0,0,0.4);
} */

.disabled {
  background-color: black;
}

button:hover {
  color: gray;
  background-color: #FAFAFA;
}

option {
  background-color: rgba(0,0,0,0.4);
}
</style>
