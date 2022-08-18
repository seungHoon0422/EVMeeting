<template>
  <div class="d-flex justify-content-center">
    <div class="box mt-5 p-3 mb-5">
      <h1 class="mt-3 font-head" >로그인</h1>
      <!-- <account-error-list v-if="authError"></account-error-list> -->
      <form @submit.prevent="login(credentials)" class="mt-5">
        <div class="my-3">
          <div class="mb-3">
            <input v-model="credentials.userid" class="length height p-3" id="id" type="text" placeholder="아이디">
          </div>
          <div>
            <input v-model="credentials.password" class="length height p-3" type="password" placeholder="비밀번호">
          </div>
        </div>
        <div>
          <button type="submit" class="btn rounded length mt-3">로그인</button>
        </div>
      </form>
        <!-- <div class="my-3">
          <button type="submit" class="btn btn-warning rounded length" @click="kakaoLogin">카카오로 로그인</button>
        </div>
        <div>
          <button type="submit" class="btn btn-success rounded length" @click="naverLogin">네이버로 로그인(서버필요)</button>
        </div>
        <div class="mt-3">
          <button type="submit" class="btn btn-secondary rounded length" @click="client.requestAccessToken()">구글로 로그인(서버필요)</button>
        </div> -->
        <!-- <button @click="client.requestAccessToken()">Authorize me</button>
        <button @click="fetchinfo">fetchinfo</button> -->
      <div class="mt-3 d-flex justify-content-center">
        <router-link v-if="!isLoggedIn" class="nav-link text-color" :to="{ name: 'findpassword' }"><span>비밀번호 찾기</span></router-link>
        <router-link v-if="!isLoggedIn" class="nav-link text-color" :to="{ name: 'signup' }"><span>회원가입</span></router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { mapActions, mapGetters } from 'vuex'
// import AccountErrorList from '../components/AccountErrorList'

export default {
  name: 'LoginView',
  // components: {
  //   AccountErrorList
  // },
  data () {
    return {
      credentials: {
        userid: '',
        password: ''
      },
      client: ''
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn', 'authError'])
  },
  created () {
    this.googleLogin()
  },
  methods: {
    ...mapActions(['login', 'kakaoLogin']),
    naverLogin () {
      const authUri = 'https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=w8_s1fcXM3yxRgEKy4yI&state=e8fc20ce-4428-4ddf-a45c-04a7c32130ba&redirect_uri=http://localhost:8080/home'
      window.location.href = authUri
      // axios({
      //   // url: 'https://nid.naver.com/oauth2.0/authorize',
      //   url: '/oauth2.0/authorize',
      //   method: 'get',
      //   headers: {
      //     response_type: 'code',
      //     client_id: 'w8_s1fcXM3yxRgEKy4yI',
      //     redirect_uri: 'http://localhost:8080/home',
      //     state: localStorage['com.naver.nid.oauth.state_token']
      //   }
      // })
      //   .then(res => console.log(res))
    },
    googleLogin () {
      const client = window.google.accounts.oauth2.initTokenClient({
        client_id: '232631786052-52f3pejjnrqc0789hfrgon3gelucjjfd.apps.googleusercontent.com',
        scope: 'https://www.googleapis.com/auth/userinfo.profile',
        callback: (tokenResponse) => {
          console.log(tokenResponse)
        }
      })
      // console.log(client)
      this.client = client
    },
    fetchinfo () {
      axios({
        url: 'https://www.googleapis.com/auth/userinfo.profile',
        method: 'get'
      })
        .then(res => console.log(res))
    }
  }
}
</script>

<style scoped>
.length {
  width: 300px;
}

.height {
  height: 50px;
}

.box {
  width: 500px;
  height: 450px;
  border: 3px solid #2c3e50;
  background-color: rgba(178, 174, 174, 0.4);
  border-radius: 10px;
}

.text-color {
 font-weight: 600;
}

.font-head {
  font-size: 40px;
}

input {
  background-color: transparent;
  border: 3px solid #2c3e50;
  border-top: none;
  border-left: none;
  border-right: none;
  border-radius: 5px;
  color: #2c3e50;
}

input:focus {
  outline:none;
}

input::placeholder {
  color: #2c3e50;
}

button:hover {
  background-color: rgb(203, 150, 210);
}

span {
  color: #2c3e50;
}

span:hover {
  color: rgb(203, 150, 210);
}

.btn {
  border: 2px solid #2c3e50 !important;
}
</style>
