export default {
  state: {
    flag_: 'flag_'
  },
  getters: {
    getFlag (state) {
      return state.flag_
    }
  },
  mutations: {
    SET_FLAG (state, payload) {
      state.flag_ = payload
    }
  },
  actions: {}
}
