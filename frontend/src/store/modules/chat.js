import socket from './socket'
const debug = process.env.NODE_ENV !== 'production'

export default {
  state: {
    user: null
  },
  mutations: {
    LOGIN (state, userData) {
      state.user = userData
      localStorage.setItem('user', JSON.stringify(userData))
    },
    LOGOUT () {
      localStorage.removeItem('user')
      location.reload()
    }
  },
  actions: {
    login ({ commit }, userData) {
      commit('LOGIN', userData)
    },
    logout ({ commit }) {
      commit('LOGOUT')
    }
  },
  modules: {
    socket
  },
  strict: debug
}
