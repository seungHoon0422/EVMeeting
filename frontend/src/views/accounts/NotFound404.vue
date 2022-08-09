<template>
  <div>
    <div class="text-style">
      <h1>404</h1>
      <h2>페이지를 찾을 수 없습니다.</h2>
    </div>
  </div>
</template>

<script>
import swal from 'sweetalert'

export default {
  name: 'NotFound404',
  created () {
    swal('페이지를 찾을 수 없습니다. 홈으로 이동할까요?', {
      buttons: {
        goHome: '예',
        cancel: '아니오'
      }
    })
      .then((value) => {
        // console.log(value)
        if (value === 'goHome') {
          window.location.href = '/'
        }
      })
  }
}
</script>

<style>
.text-style {
  color: #FAFAFA;
  margin-top: 60px;
}
</style>
