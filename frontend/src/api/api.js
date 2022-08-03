// const HOST = 'http://i7a708.p.ssafy.io/api/v1/'
const HOST = 'http://localhost:8080/api/v1/'

const ACCOUNTS = 'accounts/'
// const CHAT = 'chat/'
// const VIDEO = 'video/'

export default {
  accounts: {
    login: () => HOST + ACCOUNTS + 'login/',
    logout: () => HOST + ACCOUNTS + 'logout/',
    signup: () => HOST + ACCOUNTS + 'signup/',
    currentUserInfo: () => HOST + ACCOUNTS + 'user/',
    profile: username => HOST + ACCOUNTS + 'profile/' + username,
    upload: username => HOST + ACCOUNTS + 'upload/' + username,
    editpwd: () => HOST + ACCOUNTS + 'editpwd/',
    deleteprofile: () => HOST + ACCOUNTS + 'deleteprofile/'
  },
  chat: {},
  video: {}
}
