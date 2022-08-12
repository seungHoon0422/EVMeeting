// import { createStore } from 'vuex'
import router from '@/router'
import axios from 'axios'
import api from '@/api/api'
import swal from 'sweetalert'

export default {
  state: {
    token: localStorage.getItem('token') || '',
    currentUser: {},
    profile: {},
    authError: null,
    kakaoLogin: false,
    availableId: true,
    availableEmail: true,
    image1: '',
    cigaretteRate: [],
    mbtiLabel: [],
    mbtiRate: [],
    drinkRate: [],
    drinkLabel: []
  },
  getters: {
    isLoggedIn: state => !!state.token,
    currentUser: state => state.currentUser,
    currentUserAge: state => {
      const today = new Date()
      const a = (state.currentUser.birth || '').split('-')
      const birthDate = new Date(a[0], a[1], a[2])
      const age = today.getFullYear() - birthDate.getFullYear()
      return age
    },
    profile: state => state.profile,
    authError: state => state.authError,
    authHeader: state => ({ Authorization: `Bearer ${state.token}` }),
    kakaoLogin: state => state.kakaoLogin,
    availableId: state => state.availableId,
    availableEmail: state => state.availableEmail,
    image1: state => state.image1,
    userId: state => ({ Authorization: `Bearer ${state.currentUser.userid}` }),
    cigaretteRate: state => state.cigaretteRate,
    mbtiLabel: state => state.mbtiLabel,
    mbtiRate: state => state.mbtiRate,
    drinkRate: state => state.drinkRate,
    drinkLabel: state => state.drinkLabel
  },
  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    SET_CURRENT_USER: (state, user) => (state.currentUser = user),
    SET_PROFILE: (state, profile) => (state.profile = profile),
    SET_AUTH_ERROR: (state, error) => (state.authError = error),
    SET_KAKAO_LOGIN: (state, kakao) => (state.kakaoLogin = kakao),
    SET_IMAGE: (state, image) => (state.image1 = image),
    SET_AVAILABLEEMAIL: (state, bool) => (state.availableEmail = bool),
    SET_AVAILABLEID: (state, bool) => (state.availableId = bool),
    SET_CIGARETTERATE: (state, rates) => (state.cigaretteRate = rates),
    SET_MBTIRATE: (state, rates) => (state.mbtiRate = rates),
    SET_MBTILABEL: (state, labels) => (state.mbtiLabel = labels),
    SET_DRINKRATE: (state, rates) => (state.drinkRate = rates),
    SET_DRINKLABEL: (state, labels) => (state.drinkLabel = labels)
  },
  actions: {
    saveImage ({ commit }, image) {
      commit('SET_IMAGE', image)
    },

    saveToken ({ commit }, token) {
      commit('SET_TOKEN', token)
      localStorage.setItem('token', token)
    },

    removeToken ({ commit }) {
      commit('SET_TOKEN', '')
      localStorage.setItem('token', '')
    },

    login ({ commit, dispatch }, credentials) {
      axios({
        url: api.accounts.login(),
        method: 'post',
        data: credentials
      })
        .then(res => {
          // console.log(res)
          const token = res.data.accessToken
          dispatch('saveToken', token)
          dispatch('fetchCurrentUser')
          // router.replace({ name: 'home' })
          window.location.href = '/'
        })
        .catch(err => {
          console.error(err.response.data)
          if (err.response.data.message === 'No value present') {
            // swal('없는 아이디 입니다.')
            swal({
              title: '로그인 실패',
              text: '없는 아이디 입니다.',
              icon: 'error'
            })
          } else if (err.response.data.message === 'Invalid Password') {
            // alert('비밀번호를 다시 확인해주세요')
            swal({
              title: '로그인 실패',
              text: '비밀번호를 다시 확인해주세요.',
              icon: 'error'
            })
          } else {
            // alert('아이디와 비밀번호를 다시 확인해주세요')
            swal({
              title: '로그인 실패',
              text: '아이디와 비밀번호를 다시 확인해주세요.',
              icon: 'error'
            })
          }
          commit('SET_AUTH_ERROR', err.response.data)
        })
    },

    signup ({ commit, dispatch }, { credentials, valid }) {
      const errNames = {
        passwordHasError: '비밀번호',
        passwordHasError2: '비밀번호 확인',
        usernameHasError: '닉네임',
        useridHasError: '아이디'
      }
      if (Object.values(valid).includes(true)) {
        const invalid = []
        for (const err in valid) {
          if (valid[err] === true) {
            invalid.push(errNames[err])
          }
        }
        // alert(`${invalid} 다시 확인해주세요. 조건에 맞지 않습니다.`)
        swal({
          title: '회원가입 실패',
          text: `${invalid} 다시 확인해주세요. 조건에 맞지 않습니다.`,
          icon: 'error'
        })
        return
      }
      axios({
        url: api.accounts.signup(),
        method: 'post',
        data: credentials
      })
        .then(res => {
          // console.log('1', res)
          const token = res.data.accessToken
          // console.log('2', token)
          dispatch('saveToken', token)
          dispatch('fetchCurrentUser')
          router.push({ name: 'upload' })
        })
        .catch(err => {
          console.error(err.response.data)
          if (err.response.data.message === 'Passwords are not same') {
            // alert('비밀번호가 일치하지 않습니다.')
            swal({
              title: '회원가입 실패',
              text: '비밀번호가 일치하지 않습니다.',
              icon: 'error'
            })
          }
          commit('SET_AUTH_ERROR', err.response.data)
        })
    },

    uploadPhotos ({ dispatch }, images) {
      axios({
        url: api.accounts.upload(),
        method: 'post',
        data: images
      })
        .then(res => {
          dispatch('fetchCurrentUser')
          router.push({ name: 'home' })
        })
        .catch(err => {
          console.error(err.response.data)
          // commit('SET_AUTH_ERROR', err.response.data)
        })
    },

    logout ({ getters, dispatch, commit }) {
      if (getters.kakaoLogin) {
        // window.Kakao.init('ce7dd03b67ecccd321fee3d9e96865ae')

        // if (!window.Kakao.Auth.getAccessToken()) {
        //   console.log('Not logged in')
        //   return
        // }

        // window.Kakao.Auth.logout(function () {
        //   console.log(window.Kakao.Auth.getAccessToken())
        // })
        window.Kakao.API.request({
          url: '/v1/user/unlink',
          success: function (response) {
            console.log(response)
          },
          fail: function (error) {
            console.log(error)
          }
        })

        window.Kakao.Auth.setAccessToken(undefined)

        commit('SET_KAKAO_LOGIN', false)
        dispatch('removeToken')
        router.push({ name: 'login' })
      } else {
        // console.log(getters.authHeader)
        axios({
          url: api.accounts.logout(),
          method: 'post',
          headers: getters.authHeader
        })
          .then(() => {
            dispatch('removeToken')
            // alert('성공적으로 logout!')
            router.push({ name: 'login' })
          })
          .catch(err => {
            console.error(err.response)
          })
      }
    },

    fetchCurrentUser ({ state, commit, getters, dispatch }) {
      if (getters.isLoggedIn) {
        axios({
          url: api.accounts.currentUserInfo(),
          method: 'get',
          headers: getters.authHeader
        })
          .then(res => {
            // console.log('cur', res)
            // console.log(state.currentUser)
            commit('SET_CURRENT_USER', res.data)
            console.log(state.currentUser)
          })
          .catch(err => {
            if (err.response.status === 401) {
              dispatch('removeToken')
              router.push({ name: 'login' })
            }
          })
      }
    },

    fetchProfile ({ commit, getters }, { username }) {
      axios({
        url: api.accounts.profile(username),
        method: 'get',
        headers: getters.authHeader
      })
        .then(res => {
          commit('SET_PROFILE', res.data)
        })
    },

    kakaoLogin ({ commit, dispatch }) {
      if (!window.Kakao.isInitialized()) {
        window.Kakao.init('ce7dd03b67ecccd321fee3d9e96865ae')
      }

      // if (window.Kakao.Auth.getAccessToken()) {
      //   window.Kakao.API.request({
      //     url: '/v1/user/unlink',
      //     success: function (response) {
      //       console.log(response)
      //     },
      //     fail: function (error) {
      //       console.log(error)
      //     }
      //   })
      //   window.Kakao.Auth.setAccessToken(undefined)
      // }

      window.Kakao.Auth.login({
        success: function () {
          window.Kakao.API.request({
            url: '/v2/user/me',
            data: {
              property_keys: ['kakao_account.email', 'kakao_account.profile']
            },
            success: async function (response) {
              console.log(response)
              console.log(window.Kakao.Auth.getAccessToken())
              console.log(response.kakao_account.email)
              dispatch('saveToken', window.Kakao.Auth.getAccessToken())
              const data = {
                username: response.kakao_account.email
              }
              commit('SET_CURRENT_USER', data)
              commit('SET_KAKAO_LOGIN', true)
              router.push({ name: 'home' })
            },
            fail: function (error) {
              console.log(error)
            }
          })
        },
        fail: function (error) {
          console.log(error)
        }
      })
    },

    editPwd ({ dispatch }, credentials) {
      axios({
        url: api.accounts.editpwd(),
        method: 'post',
        data: credentials
      })
        .then(res => {
          // console.log(res)
          dispatch('fetchCurrentUser')
          router.push({ name: 'profile' })
        })
        .catch(err => console.log(err))
    },

    deleteProfile ({ dispatch }, credentials) {
      axios({
        url: api.accounts.deleteprofile(),
        method: 'post',
        data: credentials
      })
        .then(res => {
          // console.log(res)
          dispatch('removeToken')
          router.push({ name: 'login' })
        })
        .catch(err => console.log(err))
    },

    editProfile ({ dispatch }, credentials) {
      axios({
        url: api.accounts.editprofile(),
        method: 'post',
        data: credentials
      })
        .then(res => {
          // console.log(res)
          dispatch('fetchCurrentUser')
          router.push({ name: 'profile' })
        })
        .catch(err => console.log(err))
    },

    // Input: userId
    // Output: boolean(중복여부) => 중복이면 false, 사용가능 true
    checkDuplicateId ({ commit }, userId) {
      axios({
        url: api.accounts.checkDuplicateId(),
        method: 'post',
        data: userId
      })
        .then(res => {
          if (res.data.message === 'false') {
            commit('SET_AVAILABLEID', false)
          } else {
            commit('SET_AVAILABLEID', true)
          }
        })
        .catch(err => console.log(err))
    },

    // Input: email
    // Output: boolean(중복여부) => 중복이면 false, 사용가능 true
    checkDuplicateEmail ({ commit }, email) {
      // console.log(email)
      axios({
        url: api.accounts.checkDuplicateEmail(),
        method: 'post',
        data: email
      })
        .then(res => {
          // console.log(res)
          commit('SET_AVAILABLEEMAIL', res.data)
        })
        .catch(err => console.log(err))
    },

    findPassword ({ commit }, credentials) {
      // console.log(credentials)
      swal({
        title: '임시 비밀번호 발송',
        text: '잠시만 기다려주세요..',
        icon: 'success'
      })
      axios({
        url: api.accounts.findPassword(),
        method: 'post',
        data: credentials
      })
        .then(res => {
          console.log(res)
          if (res.data.message === 'success') {
            swal({
              title: '임시 비밀번호 발송',
              text: 'email로 임시 비밀번호가 발송되었습니다. 비밀번호 변경을 통해 새로운 비밀번호를 등록해주세요.',
              icon: 'success'
            })
          } else {
            swal({
              title: 'id email 불일치',
              text: 'id와 email이 일치하지 않습니다.',
              icon: 'error'
            })
          }
          // case1. id와 email이 일치하지 않습니다.
          // case2. email로 임시 비밀번호가 발송되었습니다. 비밀번호 변경을 통해 새로운 비밀번호를 등록해주세요.
        })
        .catch(err => {
          console.log(err)
          swal({
            title: '임시 비밀번호 발송 실패',
            text: 'id와 email을 다시 한번 확인해주세요',
            icon: 'error'
          })
        })
    },

    getciga ({ state, getters, commit }) {
      if (getters.isLoggedIn) {
        axios({
          url: api.accounts.currentUserInfo(),
          method: 'get',
          headers: getters.authHeader
        })
          .then(res => {
            axios({
              url: api.statistics.getCigaretteRate(res.data.id),
              method: 'get'
            })
              .then(res => {
                commit('SET_CIGARETTERATE', [res.data[0].percent, res.data[1].percent])
              })
          })
      }
    },

    getmbti ({ state, getters, commit }) {
      if (getters.isLoggedIn) {
        axios({
          url: api.accounts.currentUserInfo(),
          method: 'get',
          headers: getters.authHeader
        })
          .then(res => {
            axios({
              url: api.statistics.getMBTIRate(res.data.id),
              method: 'get'
            })
              .then(res => {
                const rates = []
                const labels = []
                for (let i = 0; i < res.data.length; i++) {
                  rates.push(res.data[i].percent)
                  labels.push(res.data[i].type)
                }
                commit('SET_MBTILABEL', labels)
                commit('SET_MBTIRATE', rates)
              })
          })
      }
    },

    getdrink ({ state, getters, commit }) {
      if (getters.isLoggedIn) {
        axios({
          url: api.accounts.currentUserInfo(),
          method: 'get',
          headers: getters.authHeader
        })
          .then(res => {
            axios({
              url: api.statistics.getDrinkRate(res.data.id),
              method: 'get'
            })
              .then(res => {
                const labelName = { zero: '소주 한 잔', under1: '소주 1병 미만', under3: '소주 1-3병', over3: '소주 3병 이상' }
                const rates = []
                const labels = []
                for (let i = 0; i < res.data.length; i++) {
                  rates.push(res.data[i].percent)
                  labels.push(labelName[res.data[i].type])
                }
                commit('SET_DRINKLABEL', labels)
                commit('SET_DRINKRATE', rates)
              })
          })
      }
    }
  }
}
