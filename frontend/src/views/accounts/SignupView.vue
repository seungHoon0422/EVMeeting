<template>
<div class="box d-flex justify-content-center">
  <div class="signup-form">
    <div class="mt-5 p-3 mb-5 rounded form-style">
      <h1>입주민 등록</h1>
      <p class="guide head">*모든 항목 기입해주시기 바랍니다</p>
      <!-- <account-error-list v-if="authError"></account-error-list> -->

      <form @submit.prevent="signup(credentials)" class="mt-5">
        <div class="d-flex justify-content-between">
          <div class="ms-3">
            <div class="d-flex justify-content-end">
              <label for="userid" class="me-3 pt-3">아이디</label>
              <input id="userid"  @blur="checkDuplicateId(credentials.userid)" v-model="credentials.userid" class="input-color rounded length height p-3" type="text" placeholder="영문,숫자 조합 4-12자" required>
            </div>
            <p class="badge bg-danger bg-margin" v-if="!availableId">이미 사용중인 아이디입니다.</p>
            <div class="mt-3 d-flex justify-content-end">
              <label for="password1" class="me-3 pt-3">비밀번호</label>
              <input id="password1" v-model="credentials.password1" :class="{ 'input-danger': passwordHasError }" class="input-color rounded length height p-3" type="password" placeholder="영문,숫자,특수문자 조합 8-16자" required>
            </div>
            <!-- <p class="guide">-영어/숫자/특수문자 중 2가지 이상 조합 8자 이상</p> -->
            <div class="mt-3 d-flex justify-content-end">
              <label for="password2" class="me-3 pt-3">비밀번호 확인</label>
              <input id="password2" v-model="credentials.password2" class="input-color rounded length height p-3" type="password"  placeholder="영문,숫자,특수문자 조합 8-16자" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="height" class="me-3 pt-3">키</label>
              <input id="height" v-model="credentials.height" class="input-color rounded length height p-3" type="number" placeholder="숫자만 입력해주세요(cm)" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="weight" class="me-3 pt-3">몸무게</label>
              <input id="weight" v-model="credentials.weight" class="input-color rounded length height p-3" type="number" placeholder="숫자만 입력해주세요(kg)" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="gender" class="me-3 pt-3">성별</label>
              <select v-model="credentials.gender" name="gender" id="gender" class="length height" required>
                <option value="">선택하세요</option>
                <option value="male">남성</option>
                <option value="female">여성</option>
              </select>
              <!-- <input id="gender" v-model="credentials.gender" class="input-color rounded length height p-3" type="text" placeholder="라디오버튼으로" required> -->
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="birth" class="me-3 pt-3">생년월일</label>
              <input id="birth" v-model="credentials.birth" class="input-color rounded length height p-3" type="date" placeholder="생년월일" required>
            </div>
          </div>
          <div class="me-5">
            <div class="d-flex justify-content-end">
              <label for="username" class="me-3 pt-3">닉네임</label>
              <input id="username" v-model="credentials.username" class="input-color rounded length height p-3" type="text" placeholder="한글,숫자,영문 조합 4-12자" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="mbti" class="me-3 pt-3">MBTI</label>
              <select v-model="credentials.mbti" name="mbti" id="mbti" class="length height" required>
                <option value=""> 선택하세요 </option>
                <option value="XXXX">잘 모름</option>
                <option value="ISTJ">ISTJ</option>
                <option value="ISTP">ISTP</option>
                <option value="ISFJ">ISFJ</option>
                <option value="ISFP">ISFP</option>
                <option value="INFJ">INFJ</option>
                <option value="INFP">INFP</option>
                <option value="INTJ">INTJ</option>
                <option value="INTP">INTP</option>
                <option value="ESTP">ESTP</option>
                <option value="ESTJ">ESTJ</option>
                <option value="ESFP">ESFP</option>
                <option value="ESFJ">ESFJ</option>
                <option value="ENFP">ENFP</option>
                <option value="ENFJ">ENFJ</option>
                <option value="ENTP">ENTP</option>
                <option value="ENTJ">ENTJ</option>
              </select>
              <!-- <input id="mbti" v-model="credentials.mbti" class="input-color rounded length height p-3" type="text" placeholder="MBTI" required> -->
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="hobby" class="me-3 pt-3">관심사</label>
              <input id="hobby" v-model="credentials.hobby" class="input-color rounded length height p-3" type="text"  placeholder="한가지만 입력해주세요" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="school" class="me-3 pt-3">학교</label>
              <input id="school" v-model="credentials.school" class="input-color rounded length height p-3" type="text" placeholder="ex) 엘사대학교" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="email" class="me-3 pt-3">이메일</label>
              <input id="email" v-model="credentials.email" class="input-color rounded length height p-3" type="email" placeholder="ex) elsa@elsa.com" @blur="checkDuplicateEmail(credentials.email)" required>
              <!-- <span class="badge badge-danger mt-1" v-if="!availableEmailForm">이메일 형식이 다릅니다.</span> -->
            </div>
            <p class="badge bg-danger ms-5" v-if="!availableEmail">이미 사용중인 이메일입니다.</p>
            <div class="mt-3 d-flex justify-content-end">
              <label for="drink" class="me-3 pt-3">주량</label>
              <select v-model="credentials.drink" name="drink" id="drink" class="length height" required>
                <option value=""> 선택하세요 </option>
                <option value="zero">소주 한 잔</option>
                <option value="under1">소주 1병 미만</option>
                <option value="under3">소주 1-3병</option>
                <option value="over3">소주 3병 이상</option>
              </select>
              <!-- <input id="drink" v-model="credentials.drink" class="input-color rounded length height p-3" type="text" placeholder="주량"> -->
            </div>
            <div class="mt-3 d-flex justify-content-end">
              <label for="cigarette" class="me-3 pt-3">흡연여부</label>
              <select v-model="credentials.cigarette" name="cigarette" id="cigarette" class="length height" required>
                <option value=""> 선택하세요 </option>
                <option value="smoke">흡연</option>
                <option value="nosmoke">비흡연</option>
              </select>
              <!-- <input id="sigarette" v-model="credentials.sigarette" class="input-color rounded length height p-3" type="text" placeholder="흡연여부"> -->
            </div>
          </div>
        </div>
        <div class="mt-3">
          <p class="text-start self-margin">자기소개</p>
          <textarea v-model="credentials.description" name="self-intro" id="" cols="80" rows="5" class="input-color p-3" placeholder="ex) 잘 부탁드립니다" required></textarea>
        </div>
        <button type="submit" class="btn rounded length mt-4">사진 등록하러 가기</button>
        <!-- <router-link :to="{ name: 'upload' }" class="btn rounded length mt-4">사진 등록하러 가기</router-link> -->
      </form>
    </div>
  </div>
</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
// import AccountErrorList from '@/components/AccountErrorList'

export default {
  name: 'SignupView',
  // components: { AccountErrorList },
  data () {
    return {
      credentials: {
        userid: '',
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
        cigarette: '',
        description: ''
      },
      valid: {
        password: false
      },
      passwordHasError: false
    }
  },
  computed: {
    ...mapGetters(['authError', 'availableEmail', 'availableId'])
  },
  methods: {
    ...mapActions(['signup', 'checkDuplicateId', 'checkDuplicateEmail']),
    checkPassword () {
      const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/

      if (!validatePassword.test(this.credentials.password1) || !this.credentials.password1) {
        this.valid.password = true
        this.passwordHasError = true
        return
      } this.valid.password = false
      this.passwordHasError = false
    }
  },
  watch: {
    'credentials.password1': function () {
      this.checkPassword()
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
  border: solid 3px #F88F6D;
  background-color: #FFFFEA;
}

.self-margin {
  margin-left: 40px;
}

select {
  border: 3px solid #F88F6D;
  background-color: #FFFFEA;
  font-size: 14px;
  padding: 0 10px;
}

input, textarea, select {
  outline-color: yellowgreen;
}

.guide {
  font-size: 10px;
  margin-left: 100px;
}

.head {
  margin-left:500px !important;
}

.bg-margin {
  margin-left: 60px;
  font-size:10px;
  margin-bottom: 0px;
}

::placeholder {
  font-size: 8px;
}

.input-danger {
    border-bottom: 1px solid red !important;
}
</style>
