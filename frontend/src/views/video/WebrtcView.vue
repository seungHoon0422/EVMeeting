<template>
<div id="main-container" class="container">
  <div id="join" v-if="!session">
    <div id="join-dialog" class="jumbotron vertical-center">
      <h1>Join a video session</h1>
      <div class="form-group">
        <h1>hi {{currentUserName}}</h1>
        <p>
          <label>Participant</label>
          <input v-model="currentUserName" class="form-control" type="text" required>
        </p>
        <p>
          <label>Session</label>
          <input v-model="mySessionId" class="form-control" type="text" required>
        </p>
        <p class="text-center">
          <!-- <button class="btn btn-lg btn-success" @click="joinSession()">Join!</button> -->
          <button class="btn btn-lg btn-success" @click="getSession()">Join!</button>
        </p>
      </div>
    </div>
  </div>

  <!-- 화상 채팅  -->
  <!-- <div id="session" v-if="session">
    <div>
      <button @click="startTimer" class="btn btn-primary">click</button><br>
      <div>
        <auto-timer></auto-timer>
      </div>
      <br>
      <div v-if="currentUser">
        <h1>profile open count : {{profileopencount}}</h1>
        <h1>countTogether : {{countTogether}}</h1>
        <adding-profile
          @profileOnOff="profileOnOff"
          :profileopencount= "profileopencount"
          :session="session"
          :countTogether = "countTogether"
          >

        </adding-profile>
      </div>
      <h1>user's profile : {{this.userprofile}}</h1>
      <h1>addcount : {{this.addcount}}</h1>
    </div>
    <div id="session-header">
    <div>
    </div>
    <div>
      <h1>남은 시간 : {{tenseconds}}</h1>
      <h1>현재 세션에 접속중인 유저 : {{currentUserCount}}</h1>
    </div>
      <h1 id="session-title">{{ mySessionId }}</h1>
      <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">
    </div>
    <div class="container d-flex justify-content-">
      <br>
      <div id="video-container" class="container">
        <div class="d-flex">
          <div class="mx-3">
            <user-video :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
          </div>
          <div >
            <user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
          </div>
        </div>
      </div>
    </div>
    <div>
      <video-bottom @audioOnOff="audioOnOff"></video-bottom>
    </div>
  </div> -->
  <div id="session" v-if="session">
    <!-- 세션 1 => 상대방 프로필 확인 -->
    <div id="session_1" v-if="sessionLevel===1">
      <h1>Hi I'm session_1</h1>
      <h1>세션 ID : {{this.mySessionId}}</h1>
      <!-- <button @click="sessionLevelPlus">levelUp</button> -->
      <h1> 프로필 들어가야함 : {{currentUser.username}}</h1>

      <!-- 상대방의 프로필이 보여야 함 -->
      <!-- <button @click="showProfilePicture">Show</button> -->
      <div id="profile-container" class="container">
        <!-- <div>
          <user-profile :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"></user-profile>
        </div> -->
        <div>
          <user-profile v-for="sub in subscribers"
          :key="sub.stream.connection.connectionId"
          :stranger="sub.stream.connection.data"
          :currentUser ="currentUser"
          @click.native="updateMainVideoStreamManager(sub)">
          </user-profile>
        </div>
      </div>
      <!-- 상대방이 마음에 든다는 신호 -->
      <like-you
      :levelOneCount="levelOneCount"
      :session="session"
      >
      </like-you>
      <!-- 세션 종료 -->
      <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="닫힘">
    </div>

    <!-- 세션 2 => 상대방 얼굴 확인 10초 카운트 및 질문 -->
    <div id="session_2" v-if="sessionLevel===2">
      <h1>Hi I'm session_2</h1>
      <h1>세션 ID : {{this.mySessionId}}</h1>
      <button @click="sessionLevelPlus">levelUp</button>
      <button @click="startTimer" class="btn btn-primary">click</button><br>
      <div v-if="currentUser">
        <h1>profile open count : {{profileopencount}}</h1>
        <h1>countTogether : {{countTogether}}</h1>
        <adding-profile
          @profileOnOff="profileOnOff"
          :profileopencount= "profileopencount"
          :session="session"
          :countTogether = "countTogether"
          >

        </adding-profile>
      </div>
       <div>
        <!-- 상대방의 정보 확인 -->
        <div v-if="StrangerProfile===true">
          <stranger-profile v-for="sub in subscribers"
          :key="sub.stream.connection.connectionId"
          :stranger="sub.stream.connection.data"
          @click.native="updateMainVideoStreamManager(sub)">
          </stranger-profile>
        </div>
        <h1>남은 시간 : {{tenseconds}}</h1>
      </div>
      <!-- 비디오 출력 부분  -->
      <div id="video-container" class="container">
        <div class="d-flex">
          <div class="mx-3">
            <user-video :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
          </div>
          <div >
            <user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
          </div>
        </div>
      </div>
      <!-- 세션 종료 -->
      <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">
      <div>
        <video-bottom @audioOnOff="audioOnOff"></video-bottom>
      </div>
    </div>

    <!-- 세션 3 => 상대방과 자유로운 교감 및 채팅 추가 -->
    <div id="session_3" v-if="sessionLevel===3">
      <h1>Hi I'm session_3</h1>
      <h1>세션 ID : {{this.mySessionId}}</h1>
      <button @click="sessionLevelPlus">levelUp</button>
      <h1> MBTI : {{currentUser.mbti}}</h1>
      <!-- 비디오 출력 부분  -->
      <div id="video-container" class="container">
        <div class="d-flex">
          <div class="mx-3">
            <user-video :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
          </div>
          <div >
            <user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
          </div>
        </div>
      </div>
       <!-- 세션 종료 -->
      <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios'
import { mapGetters } from 'vuex'
import { OpenVidu } from 'openvidu-browser'
import UserVideo from '@/views/video/components/UserVideo'
import LikeYou from '@/views/video/components/LikeYou'
import UserProfile from '@/views/video/components/UserProfile'
import AddingProfile from '@/views/video/components/AddingProfile'
import VideoBottom from '@/views/video/components/VideoBottom'
import StrangerProfile from '@/views/video/components/StrangerProfile'

axios.defaults.headers.post['Content-Type'] = 'application/json'

const OPENVIDU_SERVER_URL = 'https://' + location.hostname + ':4443'
const OPENVIDU_SERVER_SECRET = 'MY_SECRET'
export default {
  components: {
    UserVideo,
    LikeYou,
    UserProfile,
    AddingProfile,
    VideoBottom,
    StrangerProfile
  },
  data () {
    return {
      OV: undefined,
      userCount: 0,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      tenseconds: 10,
      addcount: 0,
      addflag: false,
      stopadd: false,
      autoleaveflag: false,
      sessionjoined: 0,
      autocountflag: false,
      userprofile: undefined,
      profilecount: undefined,
      profileopencount: undefined,
      countTogether: 0,
      mySessionId: 'SessionA',
      audioEnabled: false,
      sessionLevel: 1,
      levelOneCount: 0,
      StrangerProfile: false

    }
  },
  methods: {
    // hyomin start
    getSession () {
      axios.post('http://localhost:8080/api/v1/meeting/join', { sessionid: this.currentUser.userid, gender: this.currentUser.gender }).then(res => {
        console.log(this.currentUser.gender)
        // sessionid 부분을 user정보로 바꾸면 된다
        console.log(res)
        this.mySessionId = res.data
        this.joinSession()
        console.log('res.data########################################################')
      }).catch(err => {
        console.log(err)
      })
    },
    // hyomin end
    joinSession () {
      // async 작업을 통해 순차적으로 코드가 동작하도록 해야된다
      this.autoleaveflag = false
      this.autocountflag = true
      this.tenseconds = 10
      this.profilecount = 0
      this.profileopencount = 0
      this.addcount = 0
      this.countTogether = 0
      this.sessionLevel = 1
      this.levelOneCount = 0
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu()

      // --- Init a session ---
      this.session = this.OV.initSession()
      // 시그널(타이머 자동 시작 보내기)
      if (this.session !== undefined) {
        this.sessionjoined = 1
      }
      if (this.subscribers.length >= 2) {
        console.log('more than 2')
      }

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on('streamCreated', ({ stream }) => {
        const subscriber = this.session.subscribe(stream)
        // console.log(subscriber)
        this.subscribers.push(subscriber)
        console.log(this.subscribers)
      })

      // On every Stream destroyed...
      this.session.on('streamDestroyed', ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0)
        if (index >= 0) {
          this.subscribers.splice(index, 1)
        }
      })

      // On every asynchronous exception...
      this.session.on('exception', ({ exception }) => {
        console.warn(exception)
      })
      // 타이머
      this.session.on('signal:timer', () => {
        this.countTime()
      })

      // 시간 추가
      this.session.on('signal:add', () => {
        this.addTime()
      })
      // 질문 시그널
      this.session.on('signal:profile', () => {
        this.profileList()
      })
      // countTogether 시그널
      this.session.on('signal:together', () => {
        this.addCountTogether()
      })
      // profileopencount 시그널
      this.session.on('signal:profileopencount', () => {
        this.profileopencount += 1
      })
      // levelOne 시그널
      this.session.on('signal:levelOne', () => {
        this.levelOneCount += 1
      })
      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then(token => {
        this.session.connect(token, { clientData: this.currentUserName })
          .then(() => {
            console.log(this.mySessionId)
            console.log('my Session Id : #################################################')
            // --- Get your own camera stream with the desired properties ---

            const publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: '640x480', // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
              mirror: false // Whether to mirror your local video or not
            })

            this.mainStreamManager = publisher
            this.publisher = publisher

            // --- Publish your stream ---
            this.session.publish(this.publisher)
          })
          .catch(error => {
            console.log('There was an error connecting to the session:', error.code, error.message)
          })
      })

      window.addEventListener('beforeunload', this.leaveSession)

      if (this.session !== undefined) {
        console.log(this.subscriber)
      }
    },

    leaveSession () {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect()

      this.sessionjoined = 0
      this.autocountflag = false
      this.session = undefined
      this.mainStreamManager = undefined
      this.publisher = undefined
      this.subscribers = []
      this.OV = undefined
      this.userinfo = undefined
      this.addflag = false
      this.profileopencount = undefined

      window.removeEventListener('beforeunload', this.leaveSession)
      // 사용자 UX 고려 해야할 부분
      // this.$router.back('practice')
    },

    updateMainVideoStreamManager (stream) {
      if (this.mainStreamManager === stream) return
      this.mainStreamManager = stream
    },

    getToken (mySessionId) {
      return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId))
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
    createSession (sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
            customSessionId: sessionId
          }), {
            auth: {
              username: 'OPENVIDUAPP',
              password: OPENVIDU_SERVER_SECRET
            }
          })
          .then(response => response.data)
          .then(data => resolve(data.id))
          .catch(error => {
            if (error.response.status === 409) {
              resolve(sessionId)
            } else {
              console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`)
              if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`)
              }
              reject(error.response)
            }
          })
      })
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
    createToken (sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
            auth: {
              username: 'OPENVIDUAPP',
              password: OPENVIDU_SERVER_SECRET
            }
          })
          .then(response => response.data)
          .then(data => resolve(data.token))
          .catch(error => reject(error.response))
      })
    },
    // 10초 count
    countTime () {
      if (this.tenseconds === 0) {
        this.autoleaveflag = true
      } else if (this.tenseconds > 0) {
        setTimeout(() => {
          this.tenseconds -= 1
          this.countTime()
        }, 1000)
      }
    },
    // 한 사람당 4번 시간 추가 가능
    stopaddFunc () {
      this.stopadd = true
    },
    // 10초 추가
    addTime () {
      if (this.addcount >= 3) {
        this.stopaddFunc()
        // this.addcount = 0
      }
      this.tenseconds += 10
      // this.addcount += 1
      this.addflag = true
      if (this.addflag === true) {
        this.profileSignal()
      }
    },
    // 타이머 시그널
    startTimer ({ timer }) {
      this.session.signal({
        data: '',
        to: [],
        type: 'timer'
      })
    },
    // 시간추가 시그널(user로 해야할듯)
    addingTime ({ add }) {
      this.session.signal({
        data: '',
        to: [],
        type: 'add'
      })
    },
    // 시작할 떈 audioOFF
    audioOnOff ({ audio }) {
      console.log('audio')
      console.log(audio)
      this.publisher.publishAudio(audio)
    },
    // 질문 출력 시그널
    profileSignal () {
      this.session.signal({
        data: '',
        to: [],
        type: 'profile'
      })
    },
    // countTogether 시그널
    countTogetherSignal () {
      this.session.signal({
        data: '',
        to: [],
        type: 'together'
      })
    },
    // addCountTogether
    addCountTogether () {
      this.countTogether += 1
    },
    // 질문 출력
    profileList () {
      const values = Object.values(this.currentUser)
      const prop = values[Math.floor(Math.random() * values.length)]
      this.userprofile = prop
      console.log('profilecount : ')
      console.log(this.profilecount)
    },
    // 프로필 보기 OnOFF
    profileOnOff () {
      console.log(this.profileOnOff)
      if (this.profileOnOff) {
        // this.profileopencount += 1
        if (this.profileopencount % 2 === 0) {
          this.profileSignal()
        }
      }
    },
    // addcount 1 증가
    plusAddCount () {
      this.addcount += 1
    },
    showProfilePicture () {
      console.log(this.subscribers)
    },
    // 세션 레벨 증가
    sessionLevelPlus () {
      this.sessionLevel += 1
    },
    // 상대방 프로필 확인
    strangerProfileCheck () {
      if (this.countTogether % 2 === 0) {
        this.StrangerProfile = true
        console.log('Im here')
        return this.StrangerProfile
      } else {
        this.StrangerProfile = false
        console.log('Im there')
        return this.StrangerProfile
      }
    }

  },
  computed: {
    ...mapGetters(['isLoggedIn', 'authHeader', 'currentUser']),
    currentUserCount: function () {
      return this.subscribers.length
    },
    currentUserName: function () {
      return this.currentUser.userid
    }
  },
  created () {
    if (this.isLoggedIn) {
      // console.log('hi')
      console.log(this.isLoggedIn)
    } else {
      alert('잘못된 접근')
      this.$router.back()
    }
  },
  watch: {
    // 시간이 다 되면 자동적으로 세션 종료
    autoleaveflag (newautoleaveflag) {
      this.leaveSession()
    },
    // 입장과 동시에 시간 Count
    sessionjoined (sessionjoined) {
      // this.startTimer()
      // if (this.currentUserCount === 1) {
      //   // this.startTimer()
      // }
      if (sessionjoined === 1) {
        if (this.currentUserCount === 1) {
          this.startTimer()
        }
      }
    },
    // profileopencount 가 짝수 일 때마다 addCount가 증가하고,
    profileopencount () {
      if (this.profileopencount !== 0 && this.profileopencount % 2 === 0) {
        this.plusAddCount()
        // 같이 값을 바꿀 수 있는 countTogether
        this.countTogetherSignal()
      }
    },
    // addcount가 1씩 증가할 때 마다 시간이 10초 증가 해야한다.
    addcount () {
      this.addTime()
    },
    levelOneCount () {
      if (this.levelOneCount === 2) {
        this.sessionLevel += 1
      }
    },
    countTogether () {
      if (this.countTogether === 8) {
        this.sessionLevel += 1
      }
      if (this.countTogether % 2 === 0) {
        this.StrangerProfile = true
      } else {
        this.StrangerProfile = false
      }
    }
  }
}
</script>

<style>

</style>
