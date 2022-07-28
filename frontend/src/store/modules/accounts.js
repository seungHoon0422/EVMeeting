// import { createStore } from 'vuex'
import router from '@/router'
import axios from 'axios'
import api from '@/api/api'

export default {
  state: {
    token: localStorage.getItem('token') || '',
    currentUser: {},
    profile: {},
    authError: null,
    kakaoLogin: false,
    image1: ''
  },
  getters: {
    isLoggedIn: state => !!state.token,
    currentUser: state => state.currentUser,
    profile: state => state.profile,
    authError: state => state.authError,
    authHeader: state => ({ Authorization: `Bearer ${state.token}` }),
    kakaoLogin: state => state.kakaoLogin,
    image1: state => state.image1
  },
  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    SET_CURRENT_USER: (state, user) => (state.currentUser = user),
    SET_PROFILE: (state, profile) => (state.profile = profile),
    SET_AUTH_ERROR: (state, error) => (state.authError = error),
    SET_KAKAO_LOGIN: (state, kakao) => (state.kakaoLogin = kakao),
    SET_IMAGE: (state, image) => (state.image1 = image)
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
          console.log(res)
          const token = res.data.accessToken
          dispatch('saveToken', token)
          dispatch('fetchCurrentUser')
          router.push({ name: 'home' })
        })
        .catch(err => {
          console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
        })
    },

    signup ({ commit, dispatch }, credentials) {
      axios({
        url: api.accounts.signup(),
        method: 'post',
        data: credentials
      })
        .then(res => {
          console.log(res)
          const token = res.data.key
          dispatch('saveToken', token)
          dispatch('fetchCurrentUser')
          router.push({ name: 'upload' })
        })
        .catch(err => {
          console.error(err.response.data)
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
        console.log(getters.authHeader)
        axios({
          url: api.accounts.logout(),
          method: 'post',
          headers: getters.authHeader
        })
          .then(() => {
            dispatch('removeToken')
            alert('성공적으로 logout!')
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
          console.log(res)
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
          console.log(res)
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
          console.log(res)
          dispatch('fetchCurrentUser')
        })
        .catch(err => console.log(err))
    }
  }
}
