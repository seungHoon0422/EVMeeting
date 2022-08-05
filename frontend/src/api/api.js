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
    deleteprofile: () => HOST + ACCOUNTS + 'deleteprofile/',
    editprofile: () => HOST + ACCOUNTS + 'editprofile/',
    uploadPhoto: userid => HOST + ACCOUNTS + 'uploadphoto/' + userid,
    editPhoto: userid => HOST + ACCOUNTS + 'editphoto/' + userid,
    checkDuplicateId: () => HOST + ACCOUNTS + 'checkduplicateid/',
    checkDuplicateEmail: () => HOST + ACCOUNTS + 'checkduplicateemail/',
    findPassword: () => HOST + ACCOUNTS + 'findpassword/'
  },
  chat: {},
  video: {}
}
