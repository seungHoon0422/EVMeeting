<template>
  <div>
    <div class="box">
      <h1 class="font-style mt-5">비밀번호 찾기</h1>
      <form @submit.prevent="findPassword(credentials)">
          <div class="input-box d-flex justify-content-center">
            <label for="userid">아이디</label>
            <input v-model="credentials.userid" id="userid" type="text" placeholder="아이디" required>
          </div>
          <div class="input-box d-flex justify-content-center">
            <label for="email">이메일</label>
            <input v-model="credentials.email" id="email" type="email" placeholder="이메일" required>
          </div>
        <button type="submit">임시 비밀번호 보내기</button>
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
        email: ''
      }
    }
  },
  methods: {
    ...mapActions(['findPassword'])
  }
}
</script>

<style scoped>
.box {
  width: 450px;
  height: 350px;
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
