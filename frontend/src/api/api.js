const HOST = 'http://localhost:8000/api/v1/'

const ACCOUNTS = 'accounts/'

export default {
  accounts: {
    login: () => HOST + ACCOUNTS + 'login/',
    logout: () => HOST + ACCOUNTS + 'logout/',
    signup: () => HOST + ACCOUNTS + 'signup/',
    currentUserInfo: () => HOST + ACCOUNTS + 'user/',
    profile: username => HOST + ACCOUNTS + 'profile/' + username,
    upload: username => HOST + ACCOUNTS + 'upload/' + username
  }
}
