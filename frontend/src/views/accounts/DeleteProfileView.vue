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
  height: 350px;
  margin: 0 auto;
  border: 5px solid #F88F6D;
  border-radius: 10px;
}

.font-style {
  color: #F88F6D;
  font-size: 40px;
}

button {
  background-color: #F88F6D;
  padding: 5px 30px;
  border: none;
  border-radius: 10px;
  color: white;
}

label {
  font-size: 20px;
  color: #F88F6D;
  margin-right: 20px;
}

input {
  background-color: #FFFFEA;
  width: 200px;
  height: 40px;
  border: 2px solid #F88F6D;
  border-radius: 10px;
  padding: 0 20px;
}

.input-box {
  margin: 30px 20px;
}
</style>
