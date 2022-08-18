<template>
  <div>
    <div class="box">
      <h1 class="font-style mt-5">비밀번호 변경</h1>
      <form @submit.prevent="editPwd(credentials)">
          <div class="input-box d-flex justify-content-end">
            <label for="userid">아이디</label>
            <input v-model="credentials.userid" id="userid" type="text" placeholder="아이디">
          </div>
          <div class="input-box d-flex justify-content-end">
            <label for="password">기존 비밀번호</label>
            <input v-model="credentials.password" id="password" type="password" placeholder="기존비밀번호">
          </div>
          <div class="input-box d-flex justify-content-end">
            <label for="newPassword1">새 비밀번호</label>
            <input v-model="credentials.newpassword1" id="newPassword1" type="password" placeholder="비밀번호">
          </div>
          <div class="input-box d-flex justify-content-end">
            <label for="newPassword2">새 비밀번호 확인</label>
            <input v-model="credentials.newspassword2" id="newPassword2" type="password" placeholder="비밀번호 확인">
          </div>
        <button type="submit">변경하기</button>
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
        password: '',
        newPassword1: '',
        newPassword2: ''
      }
    }
  },
  methods: {
    ...mapActions(['editPwd'])
  }
}
</script>

<style scoped>
.box {
  width: 450px;
  height: 500px;
  margin: 0 auto;
  border: 3px solid #2c3e50;
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
  border-radius: 5px;
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
  margin: 30px 30px;
}

/* ::placeholder {
  color: #FAFAFA;
} */

button:hover {
  color: rgb(0, 0, 0);
  background-color: rgb(203, 150, 210);
}
</style>
