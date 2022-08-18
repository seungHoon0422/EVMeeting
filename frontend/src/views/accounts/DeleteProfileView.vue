<template>
  <div>
    <div class="box">
      <h1 class="font-style mt-5">회원탈퇴</h1>
      <form @submit.prevent="deleteProfile(credentials)">
          <div class="input-box d-flex justify-content-end">
            <label for="userid">아이디</label>
            <input v-model="credentials.userid" id="userid" type="userid" placeholder="아이디">
          </div>
          <div class="input-box d-flex justify-content-end">
            <label for="password1">비밀번호</label>
            <input v-model="credentials.password1" id="password1" type="password" placeholder="비밀번호">
          </div>
          <div class="input-box d-flex justify-content-end">
            <label for="password2">비밀번호 확인</label>
            <input v-model="credentials.password2" id="password2" type="password" placeholder="비밀번호 확인">
          </div>
        <button type="submit" class="mt-3">탈퇴하기</button>
      </form>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  data () {
    return {
      credentials: {
        userid: '',
        password1: '',
        password2: ''
      }
    }
  },
  methods: {
    ...mapActions(['deleteProfile'])
  }
}
</script>

<style scoped>
.box {
  width: 400px;
  height: 450px;
  margin: 0 auto;
  border: 5px solid #2c3e50;
  border-radius: 10px;
  background-color: rgba(178, 174, 174, 0.4);
}

.font-style {
  font-size: 40px;
}

button {
  background-color: transparent;
  padding: 5px 30px;
  border: 2px solid #2c3e50;
  border-radius: 10px;
}

label {
  font-size: 20px;
  margin-right: 20px;
}

input {
  background-color: transparent;
  width: 200px;
  height: 40px;
  border: 2px solid #2c3e50;
  border-radius: 10px;
  padding: 0 20px;
}

.input-box {
  margin: 30px 20px;
}

/* ::placeholder {
  color: #2c3e50;
} */

button:hover {
  color: rgb(0, 0, 0);
  background-color: rgb(203, 150, 210);
}
</style>
