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
  margin: 30px 30px;
}
</style>
