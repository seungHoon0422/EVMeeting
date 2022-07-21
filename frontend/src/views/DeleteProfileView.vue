<template>
  <div>
    <div class="box">
      <h1 class="font-style mt-3">회원탈퇴</h1>
      <form action="#">
        <div>
          <label for="password1">비밀번호</label>
          <input id="password1" type="text" placeholder="비밀번호">
        </div>
        <div>
          <label for="password2">비밀번호 확인</label>
          <input id="password2" type="text" placeholder="비밀번호 확인">
        </div>
        <button type="submit">탈퇴하기</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.box {
  width: 400px;
  height: 400px;
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
  padding: 0 20px;
}
</style>
