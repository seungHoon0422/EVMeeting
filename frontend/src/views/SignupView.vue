<template>
  <div class="d-flex justify-content-center">
    <div class="box mt-5 shadow-lg p-3 mb-5 bg-body rounded">
      <h1 class="mt-3">회원가입</h1>

      <account-error-list v-if="authError"></account-error-list>

      <form @submit.prevent="signup(credentials)" class="mt-5">
        <div>
          <input v-model="credentials.username" class="border-top-0 border-end-0 border-start-0 rounded length height p-3" type="text" placeholder="아이디">
        </div>
        <div class="my-3">
          <input v-model="credentials.password1" class="border-top-0 border-end-0 border-start-0 rounded length height p-3" type="password" placeholder="비밀번호">
        </div>
        <div>
          <input v-model="credentials.password2" class="border-top-0 border-end-0 border-start-0 rounded length height p-3" type="password"  placeholder="비밀번호 확인">
        </div>
        <button class="btn btn-dark rounded length mt-4">가입하기</button>
      </form>
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
        username: '',
        password1: '',
        password2: ''
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

<style>
.length {
  width: 300px;
}
</style>
