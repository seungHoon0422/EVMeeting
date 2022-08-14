// const HOST = 'https://i7a708.p.ssafy.io:8080/api/v1/'
const HOST = 'https://localhost:8080/api/v1/'

const ACCOUNTS = 'accounts/'
const CHAT = 'chat/'
const VIDEO = 'meeting/'
const STATISTICS = 'statistics/'

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
  chat: {
    createRoom: () => HOST + CHAT + 'room/',
    getMessage: () => HOST + CHAT + 'room/allMessages/',
    getRooms: () => HOST + CHAT + 'rooms/',
    deleteRoom: () => HOST + CHAT + 'room/delete/',
    connectionSock: () => HOST + CHAT + 'ws/',
    removeMsg: () => HOST + CHAT + 'room/delete/'
  },
  video: {
    getSessionJoin: () => HOST + VIDEO + 'join',
    getStrangerProfile: () => HOST + VIDEO + 'userinfo',
    userLeaveSession: () => HOST + VIDEO + 'endservice'
  },
  statistics: {
    getGenderData: () => HOST + STATISTICS + 'total/gender/',
    getMbtiData: () => HOST + STATISTICS + 'total/mbti/',
    getTimeData: () => HOST + STATISTICS + 'total/matchingTime/',
    getMatchingRate: userid => HOST + STATISTICS + 'individual/matchingRate/' + userid,
    getMatchingTime: userid => HOST + STATISTICS + 'individual/matchingTime/' + userid,
    getMBTIRate: userid => HOST + STATISTICS + 'individual/mbtiRate/' + userid,
    getDrinkRate: userid => HOST + STATISTICS + 'individual/drinkRate/' + userid,
    getCigaretteRate: userid => HOST + STATISTICS + 'individual/cigaretteRate/' + userid
  }
  // Update end
}
