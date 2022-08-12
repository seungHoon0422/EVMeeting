<template>
<div id="main-container" class="container">
  <div id="join" v-if="!session">
    <div id="join-dialog" class="jumbotron vertical-center">
      <div class="container">
        <div class="container my-5">
          <h1>안녕하세요 ! {{currentUser.username}} 님</h1>
          <div class="d-flex justify-content-center my-5 align-items-center">
            <!-- 유저 프로필 부분 -->
            <div class="form-group my-5 mx-5">
              <div>
                <img :src="`${currentUser.photo}`" id="myProfile" style="border : 5px solid #B9729E;">
              </div>
            </div>
            <!-- How To Use -->
            <div class="d-flex justify-content-center">
              <div class="d-flex justify-content-center">
                <!-- <div class="align-items-center">
                    <button id="buttonIcon" @click="playAnimation">
                      <i class="fa-solid fa-elevator fa-3x" style="margin-top: 100px"></i>
                    </button>
                </div> -->
                <!-- 엘리베이터 애니메이션 -->
                <div>
                  <div class="container d-flex justify-content-center align-items-center" id="elevatorAnimation">
                    <div class="container d-flex justify-content-center align-items-center" v-if="animationFlag===true" style="width: 550px; height: 650px">
                      <div class="container justify-content-center align-items-center">
                        <elevator-animation></elevator-animation>
                      </div>
                    </div>
                    <div v-else>
                      <div class="container">
                        <div class="container d-flex justify-content-around align-items-center">
                          <div>
                            <elevator-infinity style="width: 200px; height: 125px"></elevator-infinity>
                          </div>
                          <div class="mx-5">
                            <span class="align-middle">
                              <h4 style="font-weight:bold;">엘리베이터에 탑승하세요</h4>
                              <div class="d-flex mt-3">
                                <h6>상대가 마음에 들면</h6>
                                <i class='bx bxs-chevron-up-circle mx-2' style="font-size: 20px; color : blue;"></i>
                              </div>
                              <div class="d-flex my-2">
                              <h6>마음에 들지 않으면</h6>
                              <i class='bx bxs-chevron-down-circle mx-2' style="font-size: 20px; color: red;" ></i>
                              </div>
                            </span>
                          </div>
                        </div>
                        <hr>
                        <div class="container d-flex justify-content-around align-items-center">
                          <div>
                            <span class="align-middle">
                              <h4 style="font-weight:bold;">10초 동안 고민하세요 !</h4>
                                <div class="d-flex mt-3">
                                  <h6>상대의 얼굴 과 프로필을 확인하고</h6>
                                </div>
                                <div class="d-flex">
                                  <h6>고민 해보세요</h6>
                                </div>
                            </span>
                          </div>
                          <img src="@/img/thinkingImage.png" style="width: 200px; height: 200px">
                        </div>
                        <hr>
                        <div class="container align-items-center">
                          <div class="mt-3">
                            <span class="align-middle">
                              <h4 style="font-weight: bold;"> 엘리베이터를 호출 해보세요! </h4>
                            </span>
                          </div>
                          <div>
                            <button id="buttonIcon" @click="playAnimation">
                              <i class="fa-solid fa-elevator fa-3x my-2"></i>
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div id="session" v-if="session">
    <!-- 세션 1 => 상대방 프로필 확인 -->
    <div id="session_1" v-if="sessionLevel===1">
      <h1>세션 ID : {{this.mySessionId}}</h1>
      <h1 style="font-weight:bold;"> 안녕하세요 ! {{currentUser.username}} 님</h1>
      <div class="container">
        <div>
          <div v-if="currentUserCount==0">
            <oneby-one
            ref="elevatorOpen"
            ></oneby-one>
            <h3>대기중..</h3>
          </div>
          <div v-else>
            <!-- 상대방의 호감 표시 확인 -->
            <div v-if="this.levelOneCount === 0">
              <i class='bx bxs-heart' style="font-size:  50px;"></i>
            </div>
            <div v-else-if="this.levelOneCount === 1">
              <i class='bx bxs-heart' style="font-size: 50px; color: red;"></i>
            </div>
            <oneby-one ref="elevatorOpen">
            </oneby-one>
          </div>
        </div>
        <h1>{{this.strangerNickname}}</h1>
        <div class="d-flex justify-content-center" id="elevatorButton">
          <!-- 상대방이 마음에 든다는 신호 -->
          <like-you
          :levelOneCount="levelOneCount"
          :session="session"
          >
          </like-you>
          <!-- 세션 종료 -->
          <!-- <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="닫힘"> -->
          <button id="buttonIcon" @click="leaveSession">
            <i class='bx bxs-chevron-down-circle' style="font-size: 50px; color: red;" ></i>
          </button>
        </div>
        <!-- 상대방의 프로필이 보여야 함 -->
        <!-- <button @click="showProfilePicture">Show</button> -->
        <transition
        enter-active-class="animate__animated animate__fadeIn"
        leave-active-class="animate__animated animate__fadeOut"
        >
          <div id="profile-container" class="container" v-if="currentUserCount===1">
            <user-profile v-for="sub in subscribers"
            :key="sub.stream.connection.connectionId"
            :stranger="sub.stream.connection.data"
            :currentUser ="currentUser"
            @click.native="updateMainVideoStreamManager(sub)"
            @sendStarngerId="sendStarngerId"
            @sendStrangerObject="sendStrangerObject"
            @sendStrangerNickname="sendStrangerNickname"
            @sendStrangerUserid="sendStrangerUserid"
            >
            </user-profile>
            </div>
        </transition>
      </div>

    </div>

    <!-- 세션 2 => 상대방 얼굴 확인 10초 카운트 및 질문 -->
    <div id="session_2" v-if="sessionLevel===2">
      <h1>Hi I'm session_2</h1>
      <h1>세션 ID : {{this.mySessionId}}</h1>
      <button @click="sessionLevelPlus">levelUp</button>
      <div v-if="currentUser">
          <!-- #ff8585 -->
          <div class="">
            <b-progress height="2rem" show-progress :max="8" class="mb-3">
              <b-progress-bar variant="$white: #fff !default;" :value="profileopencount" animated show-progress
              style="background-color : #BE7292 !important">
                <div class="d-flex justify-content-center">
                  <span v-if="profileopencount===7">
                    <h3 class="mt-2">엘리베이터에서 나갈까요?</h3>
                  </span>
                  <span v-else-if="profileopencount%2===1">
                    <h3 class="mt-2">보고싶어요!</h3>
                  </span>
                  <span v-else-if="profileopencount%2===0">
                    <h3 class="mt-2">10초 추가!</h3>
                  </span>
                </div>
              </b-progress-bar>
            </b-progress>
          </div>
      </div>
       <div>
        <!-- 상대방의 정보 확인 -->
        <transition
        enter-active-class="animate__animated animate__fadeIn"
        leave-active-class="animate__animated animate__fadeOut"
        >
          <div v-if="StrangerProfile===true">
            <stranger-profile v-for="sub in subscribers"
            :key="sub.stream.connection.connectionId"
            :stranger="sub.stream.connection.data"
            :countTogether ="countTogether"
            @click.native="updateMainVideoStreamManager(sub)">
            </stranger-profile>
          </div>
        </transition>
      </div>
      <!-- 비디오 출력 부분  -->
      <div>
        <div id="video-container" class="container d-flex justify-content-center align-items-center">
          <div class="container mx-3" id="publisher_container">
            <user-video :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
          </div>
          <div class="container align-items-center">
            <timer-animation style="width: 100px; height: 100px;"></timer-animation>
            <h4 style="font-weight: bold;">{{tenseconds}}</h4>
          </div>
          <div class="container mx-3" id="subscriber_container">
            <user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
          </div>
        </div>
      </div>
      <div>
        <!-- 선택 및 세션 종료 버튼 -->
        <div class="d-flex justify-content-center">
          <adding-profile
            @profileOnOff="profileOnOff"
            :profileopencount="profileopencount"
            :session="session"
            :countTogether ="countTogether"
            >

          </adding-profile>
          <!-- 세션 종료 -->
          <!-- <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="닫힘"> -->
          <button id="buttonIcon" @click="leaveSession">
            <i class='bx bxs-chevron-down-circle' style="font-size: 50px; color: red;" ></i>
          </button>
        </div>
        <div>
          <video-bottom
          @audioOnOff="audioOnOff"
          :sessionLevel="sessionLevel"
          >
          </video-bottom>
        </div>
      </div>
    </div>

    <!-- 세션 3 => 상대방과 자유로운 교감 및 채팅 추가 -->
    <div id="session_3" v-if="sessionLevel===3">
      <!-- <h1>Hi I'm session_3</h1>
      <h1>세션 ID : {{this.mySessionId}}</h1>
      <h1> MBTI : {{currentUser.mbti}}</h1> -->
      <h1>남은 시간 : {{tenseconds}}</h1>
      <!-- 랜덤 질문 출력 부분 -->
      <div>
        <random-button
        :session="session"
        :tenseconds="tenseconds">
        </random-button>
      </div>
      <div>
        <random-question
        ref="qustionRequest"
        @subject="subject"
        >
        </random-question>
      </div>
      <div v-show="is_show_c" class="pop_chat">
        <chat-view :roomid=this.roomid style="width:100%; height:100%;"></chat-view>
      </div>
      <div v-show="is_show_p" class="pop_profile">
        <profile-view v-for="sub in subscribers"
            :key="sub.stream.connection.connectionId"
            :stranger="sub.stream.connection.data"
            :countTogether ="countTogether"
            @click.native="updateMainVideoStreamManager(sub)" style="width:100%; height:100%;"></profile-view>
      </div>
      <!-- 상대 비디오 출력   -->
      <div>
          <user-video style="width:150%; height: 100%; margin-left:11.3%; margin-right:11%;" v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
      </div>
      <!-- 테이블 -->
      <div class="table" style="position:relative; height:100px;">
        <img src="@/img/table.png" style="width: 100%; height: 100%; object-fit: cover;" />
        <div style="display: grid; place-items: center; border: none; margin-top:3%;">
          <!-- 거울 -->
          <!-- <img src="@/img/mirror.png" style="border: none; width:12.7%;"/> -->
          <!-- 내 비디오 출력 -->
          <user-video class="my-video" style="border: none;  width: 330px; height:200px; margin-left:10.7%; margin-top:-15%;" :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
        </div>
        <div class="button_grid" style="border:none; margin-top:-10%;">
          <!-- 음소거 버튼 -->
          <video-bottom style="color: red;" @audioOnOff="audioOnOff" :sessionLevel="sessionLevel"></video-bottom>
          <!-- 채팅 버튼 -->
            <input class="btn btn-large btn-danger" type="button" id="buttonOpenChat" @click="openChat" value="채팅"/>
          <!-- 프로필 버튼 -->
            <input class="btn btn-large btn-danger" type="button" id="buttonOpenProfile" @click="openProfile" value="프로필"/>
          <!-- 세션 종료 버튼 -->
          <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="[deleteRoom(), removeMessage(), leaveSession()]" value="나가기"/>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios'
import api from '@/api/api'
import { mapGetters } from 'vuex'
import { OpenVidu } from 'openvidu-browser'
import UserVideo from '@/views/video/components/UserVideo'
import LikeYou from '@/views/video/components/LikeYou'
import UserProfile from '@/views/video/components/UserProfile'
import VideoBottom from '@/views/video/components/VideoBottom'
import AddingProfile from '@/views/video/components/AddingProfile'
import StrangerProfile from '@/views/video/components/StrangerProfile'
import RandomButton from '@/views/video/components/RandomButton'
import RandomQuestion from '@/views/video/components/RandomQuestion'
import ChatView from '@/views/chat/ChatInMeeting'
import ElevatorAnimation from '@/views/video/animation/ElevatorAnimation'
import ElevatorInfinity from '@/views/video/animation/ElevatorInfinity'
import OnebyOne from '@/views/video/animation/OnebyOne'
import TimerAnimation from '@/views/video/animation/TimerAnimation'
import ProfileView from '@/views/video/components/ProfileView'

axios.defaults.headers.post['Content-Type'] = 'application/json'

// const OPENVIDU_SERVER_URL = 'https://' + location.hostname + ':4443'
const OPENVIDU_SERVER_URL = 'https://' + 'i7a708.p.ssafy.io' + ':4443'
const OPENVIDU_SERVER_SECRET = 'MY_SECRET'
export default {
  components: {
    UserVideo,
    LikeYou,
    UserProfile,
    AddingProfile,
    VideoBottom,
    StrangerProfile,
    ChatView,
    ElevatorAnimation,
    ElevatorInfinity,
    RandomButton,
    RandomQuestion,
    OnebyOne,
    TimerAnimation,
    ProfileView
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
      StrangerProfile: false,
      strangerId: undefined,
      strangerObject: undefined,
      animationFlag: false,
      strangerLeaveFlag: false,
      id: 1,
      canLeaveSite: true,
      randomSubject: '',
      randomValue: 0,
      strangerNickname: '',
      strangerUserid: '',
      strangerAge: Date.now(),
      roomid: 1,
      is_show_c: false,
      is_show_p: false
    }
  },
  methods: {
    // hyomin start
    getSession () {
      axios.post(api.video.getSessionJoin(), { userid: this.currentUser.userid, gender: this.currentUser.gender }).then(res => {
        console.log(this.currentUser.gender)
        // sessionid 부분을 user정보로 바꾸면 된다
        console.log(res)
        this.mySessionId = res.data
      }).then(res => {
        this.joinSession()
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
      this.StrangerProfile = false
      this.profileCount = 0
      this.strangerId = undefined
      this.animationFlag = false
      this.randomSubject = ''
      this.randomValue = 13
      this.strangerNickname = ''
      this.strangerUserid = ''
      this.strangerAge = Date.now()
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
      // randomButton 시그널
      this.session.on('signal:randomButton', () => {
        // this.randomValue = Math.floor(Math.random() * 14)
        if (this.randomValue === 0) {
          this.randomValue = 14
        }
        this.randomValue -= 1
        this.$refs.qustionRequest.showQuestion(this.randomValue)
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
      // strangerId: this.strangerId
      axios.post(api.video.userLeaveSession(), { userFrom: this.currentUser.userid, userTo: this.strangerObject }).then(res => {
        console.log(res)
      }).catch(err => {
        console.log(err)
      })
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

      this.$router.go('/cam')

      // window.removeEventListener('beforeunload', this.leaveSession)
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
          this.tenseconds -= 0.5
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
      this.tenseconds = 10
      // this.addcount += 1
      this.addflag = true
      if (this.addflag === true) {
        this.profileSignal()
      }
    },
    // 타이머 시그널
    startTimer () {
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
        return this.StrangerProfile
      } else {
        this.StrangerProfile = false
        return this.StrangerProfile
      }
    },
    sendStarngerId (data) {
      this.strangerId = data
    },
    sendStrangerObject (data) {
      this.strangerObject = data
    },
    sendStrangerNickname (data) {
      this.strangerNickname = data
    },
    sendStrangerUserid (data) {
      this.strangerUserid = data
    },
    subject (data) {
      this.randomSubject = data
    },
    createRoom () {
      console.log(this.currentUser.id)
      console.log(this.strangerId)
      if (this.currentUser.id > this.strangerId) {
        this.id1 = this.strangerId
        this.id2 = this.currentUser.id
      } else {
        this.id1 = this.currentUser.id
        this.id2 = this.strangerId
      }
      axios({
        method: 'post',
        url: api.chat.createRoom(),
        headers: { 'content-type': 'application/json' },
        data: { userid1: this.id1, userid2: this.id2, id: this.id, alive: true }
      }).then(
        res => {
          console.log(res.data)
          this.roomid = res.data
        }
      ).catch({})
    },
    getStrangerInfo () {
      axios.post(api.video.getStrangerProfile(), this.strangerUserid).then(res => {
        console.log(res.data)
        console.log(res.data.birth)
        this.strangerAge = res.data.birth
      }).catch(err => {
        console.log(err)
      })
    },
    // setTimeout
    playAnimation () {
      this.animationFlag = true
      setTimeout(() => {
        this.getSession()
      }, 2000)
    },
    removeMessage () {
      axios.delete(api.chat.removeMsg() + `${this.roomid}`).then(
        res => { alert('success') }
      ).catch({})
    },
    deleteRoom () {
      axios.put(api.chat.deleteRoom() + `${this.roomid}`
      ).then(
        res => {
        }
      ).catch({})
    },
    openChat () {
      this.is_show_c = !this.is_show_c
    },
    openProfile () {
      this.is_show_p = !this.is_show_p
    }
    // Really? leave?
    // reallyLeave () {
    //   this.boxTwo = ''
    //   this.$bvModal.msgBoxConfirm('정말 나가시겠습니까?', {
    //     title: '알림',
    //     size: 'sm',
    //     buttonSize: 'sm',
    //     okVariant: 'primary',
    //     okTitle: '네',
    //     cancelTitle: '아니요',
    //     cancleVariant: 'danger',
    //     footerClass: 'p-2',
    //     hideHeaderClose: false,
    //     centered: true
    //   })
    //     .then(value => {
    //       if (value === true) {
    //         this.leaveSession()
    //       }
    //     })
    //     .catch(err => {
    //       console.log(err)
    //     })
    // }
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
      window.addEventListener('beforeunload', (event) => {
        this.leaveSession()
        this.$router.push('/')
        // window.location.reload(true)
        // event.preventDefault()
        event.returnValue = 'TEST'
      })
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
    },
    sessionLevel () {
      if (this.sessionLevel === 2) {
        this.startTimer()
      }
      if (this.sessionLevel === 3) {
        console.log('Its Level 3')
        this.roomid = this.createRoom()
        this.tenseconds = 600
      }
    },
    tenseconds () {
      if (this.sessionLevel === 3 && this.tenseconds === 1) {
        // this.roomid = this.createRoom()
      }
    },
    currentUserCount () {
      if (this.currentUserCount === 0) {
        this.strangerLeaveFlag = true
      } else {
        this.$refs.elevatorOpen.meetStranger()
      }
      // if (this.currentUserCount === 1) {
      //   this.$refs.elevatorOpen.meetStranger()
      // }
    },
    strangerLeaveFlag () {
      if (this.strangerLeaveFlag === true) {
        this.leaveSession()
      }
    },
    strangerUserid () {
      this.getStrangerInfo()
      // console.log('ItsStart')
      // console.log(this.strangerAge)
      // const today = new Date()
      // const a = (this.strangerAge || '').split('-')
      // const birthDate = new Date(a[0], a[1], a[2])
      // this.strangerAge = today.getFullYear() - birthDate.getFullYear()
      // console.log('thisIsStrangerAge')
      // console.log('today : ', today)
      // console.log('a :', a)
      // console.log('birthDate :', birthDate)
      // console.log(this.strangerAge)
    }
  }
}
</script>

<style>
#myProfile{
  width: 250px;
  height: 200px;
}

#buttonIcon{
  background-color:transparent;
  border: 0;
  outline: 0;
}
.flex_table{
  display:flex;
  justify-content: space-between;
  align-items: center;
  width:100%;
}
.pop_chat{
  position: absolute;
  top: 200px;
  height: 430px;
  width: 400px;
  left: 20px;
}
.pop_profile{
  position: absolute;
  top: 280px;
  height: 60%;
  width: 350px;
  right: 67px;
}
.button_grid{
  display: grid;
  place-items: center;
  grid-template-columns: repeat(auto-fill,minmax(300px, 1fr));
  border: none;
}
@media screen and (max-width: 1300px) {
  .pop_chat {
    display: none;
  }
  .pop_profile {
    display: none;
  }
  .my-video {
    display: none;
  }
  .button_grid {
    display: none;
  }
}
.box{
  width: 30px;
  height: 40px;
  border-radius: 70%;
  overflow: hidden;
  background-color: white;
  margin-right: 5px;
  margin-left: -20px;
}
.photo {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
