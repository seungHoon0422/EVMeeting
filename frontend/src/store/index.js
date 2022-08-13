import Vue from 'vue'
import Vuex from 'vuex'
import accounts from './modules/accounts'
// 데이터 유지를 위해 import
import createPersistedState from 'vuex-persistedstate'
Vue.use(Vuex)

export default new Vuex.Store({
  modules: { accounts },
  plugins: [
    createPersistedState()
  ]
})
