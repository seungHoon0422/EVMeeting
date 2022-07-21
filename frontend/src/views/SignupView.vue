<template>
<div class="box d-flex justify-content-center">
  <div class="signup-form">
    <div class="mt-5 p-3 mb-5 rounded form-style">
      <h1>입주민 등록</h1>

      <account-error-list v-if="authError"></account-error-list>

      <form @submit.prevent="signup(credentials)" class="mt-5">
        <div class="d-flex justify-content-between">
          <div class="ms-3">
            <div class="d-flex justify-content-end">
              <label for="id" class="me-3 pt-3">*아이디</label>
              <input id="id" v-model="credentials.id" class="input-color rounded length height p-3" type="text" placeholder="아이디" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="password1" class="me-3 pt-3">*비밀번호</label>
              <input id="password1" v-model="credentials.password1" class="input-color rounded length height p-3" type="password" placeholder="비밀번호" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="password2" class="me-3 pt-3">*비밀번호 확인</label>
              <input id="password2" v-model="credentials.password2" class="input-color rounded length height p-3" type="password"  placeholder="비밀번호 확인" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="height" class="me-3 pt-3">*키</label>
              <input id="height" v-model="credentials.height" class="input-color rounded length height p-3" type="number" min=140 placeholder="키" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="weight" class="me-3 pt-3">*몸무게</label>
              <input id="weight" v-model="credentials.weight" class="input-color rounded length height p-3" type="number" min=30 placeholder="몸무게" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="gender" class="me-3 pt-3">*성별</label>
              <input id="gender" v-model="credentials.gender" class="input-color rounded length height p-3" type="text" placeholder="라디오버튼으로" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="birth" class="me-3 pt-3">*생년월일</label>
              <input id="birth" v-model="credentials.birth" class="input-color rounded length height p-3" type="date" placeholder="생년월일" required>
            </div>
          </div>
          <div class="me-5">
            <div class="d-flex justify-content-end">
              <label for="username" class="me-3 pt-3">닉네임</label>
              <input id="username" v-model="credentials.username" class="input-color rounded length height p-3" type="text" placeholder="닉네임">
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="mbti" class="me-3 pt-3">MBTI</label>
              <input id="mbti" v-model="credentials.mbti" class="input-color rounded length height p-3" type="text" placeholder="MBTI">
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="hobby" class="me-3 pt-3">관심사</label>
              <input id="hobby" v-model="credentials.hobby" class="input-color rounded length height p-3" type="text"  placeholder="관심사">
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="school" class="me-3 pt-3">학교</label>
              <input id="school" v-model="credentials.school" class="input-color rounded length height p-3" type="text" placeholder="학교">
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="email" class="me-3 pt-3">이메일</label>
              <input id="email" v-model="credentials.email" class="input-color rounded length height p-3" type="email" placeholder="이메일">
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="drink" class="me-3 pt-3">주량</label>
              <input id="drink" v-model="credentials.drink" class="input-color rounded length height p-3" type="text" placeholder="주량">
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="sigarette" class="me-3 pt-3">흡연여부</label>
              <input id="sigarette" v-model="credentials.sigarette" class="input-color rounded length height p-3" type="text" placeholder="흡연여부">
            </div>
          </div>
        </div>
        <div class="mt-3">
          <p class="text-start self-margin">자기소개</p>
          <textarea v-model="credentials.self" name="self-intro" id="" cols="80" rows="5" class="input-color"></textarea>
        </div>
        <button type="submit" class="btn rounded length mt-4">사진 등록하러 가기</button>
      </form>
    </div>
  </div>
</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import AccountErrorList from '@/components/AccountErrorList'

export default {
  name: 'SignupView',
  components: { AccountErrorList },
  data () {
    return {
      credentials: {
        id: '',
        username: '',
        password1: '',
        password2: '',
        height: '',
        weight: '',
        gender: '',
        birth: '',
        hobby: '',
        mbti: '',
        school: '',
        email: '',
        drink: '',
        sigarette: '',
        self: ''
      }
    }
  },
  computed: {
    ...mapGetters(['authError'])
  },
  methods: {
    ...mapActions(['signup'])
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
  color: #F88F6D;
  background-color: #FFFFEA;
  border-color: #F88F6D;
  border: solid 5px;
  border-radius: 20px;
  width: 800px;
}

.btn {
  color: white;
  background-color: #F88F6D;
}

.input-color {
  border: solid 3px;
  border-color: #F88F6D;
  background-color: #FFFFEA;
}

.self-margin {
  margin-left: 40px;
}
</style>
