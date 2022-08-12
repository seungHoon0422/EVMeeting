<template>
<div id="main-container" class="container" style="width: 80%; height: 80%;">
  <div id="join" v-if="!session">
    <div id="join-dialog" class="jumbotron vertical-center">
      <div class="container">
        <!-- <div class="container my-5" style="color: #FAFAFA;"> -->
        <!-- <div id="session_1" v-if="sessionLevel===1" class="container" style="color: #77a094"> -->
        <div class="container my-5">
          <div>
            <div class="d-flex justify-content-center " style="flex-direction: row;">
              <h1 class="col-auto" style="font-weight: bold;">안녕하세요!</h1>
              <h1 class="col-auto mx-3" style="color:#B9729E; font-weight: bold;"> {{currentUser.username}} </h1>
              <h1 class="col-auto" style="font-weight: bold;">님</h1>
            </div>
            <div class="container d-flex justify-content-around my-5 align-items-center">
              <div class="row">
                <!-- 유저 프로필 부분 -->
                <div class="col-auto form-group my-5 mx-3" >
                  <div>
                    <img :src="`${currentUser.photo}`" id="myProfile" style="border : 5px solid #B9729E;">
                  </div>
                  <div style="margin-top: 20px;">
                    <button @click="ToProfile"
                    style="background-color : #B9729E; border: 0px;  border-radius: 10px;
                    font-family : 'GangwonEdu_OTFBoldA';
                    margin-top: 1px; color: white;"
                    >
                      프로필 변경하기
                    </button>
                  </div>
                </div>
                <!-- How To Use -->
                <div class="col-auto d-flex justify-content-center">
                  <div class="d-flex justify-content-center">
                    <!-- <div class="align-items-center">
                        <button id="buttonIcon" @click="playAnimation">
                          <i class="fa-solid fa-elevator fa-3x" style="margin-top: 100px"></i>
                        </button>
                    </div> -->
                    <!-- 엘리베이터 애니메이션 -->
                    <div>
                      <div class="container d-flex justify-content-center align-items-center" id="elevatorAnimation">
                        <div class="container d-flex justify-content-center align-items-center" v-if="animationFlag===true" style="width: 550px; height: 650px; margin-top:85px;">
                          <div class="container justify-content-center align-items-center">
                            <button class="my-3" style="background-color: #FAFAFA" type="button" id="#buttonIcon" disabled>
                              <span style="color : #B9729E;" class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>
                              <span class="mx-1" style="font-weight:bold; color : black;">호출</span>
                            </button>
                            <div style="margin-bottom: 300px;">
                              <elevator-animation></elevator-animation>
                            </div>
                          </div>
                        </div>
                        <div v-else>
                          <div class="container">
                            <div class="container d-flex justify-content-around align-items-center">
                              <div class="col-md-auto col-auto">
                                <elevator-infinity style="width: 200px; height: 125px"></elevator-infinity>
                              </div>
                              <div class="col-md-auto col-auto mx-5">
                                <span class="align-middle" style="font-family : 'GangwonEdu_OTFBoldA'">
                                  <h4 style="font-weight:bold;">엘리베이터에 <span style="color:#B9729E;">탑승</span>하세요</h4>
                                  <div class="d-flex mt-3">
                                    <h6 style="font-family : 'GangwonEdu_OTFBoldA';">상대가 마음에 들면</h6>
                                    <i class='bx bxs-chevron-up-circle mx-2' style="font-size: 20px; color : blue;"></i>
                                  </div>
                                  <div class="d-flex my-2">
                                  <h6 style="font-family : 'GangwonEdu_OTFBoldA';">마음에 들지 않으면</h6>
                                  <i class='bx bxs-chevron-down-circle mx-2' style="font-size: 20px; color: red;" ></i>
                                  </div>
                                </span>
                              </div>
                            </div>
                            <hr>
                            <div class="container d-flex justify-content-around align-items-center">
                              <div class="col-auto">
                                <span class="align-middle">
                                  <h4 style="font-weight:bold;">10초 동안 <span style="color:#B9729E;">고민</span>하세요</h4>
                                    <div class="d-flex mt-3">
                                      <h6 style="font-family : 'GangwonEdu_OTFBoldA';">상대의 얼굴 과 프로필을 확인하고</h6>
                                    </div>
                                    <div class="d-flex">
                                      <h6 style="font-family : 'GangwonEdu_OTFBoldA';">고민 해보세요</h6>
                                    </div>
                                </span>
                              </div>
                              <img src="@/img/thinkingImage.png" style="width: 200px; height: 200px">
                            </div>
                            <hr>
                            <div class="col-auto container align-items-center">
                              <div class="col-auto mt-3">
                                <span class="align-middle">
                                  <h4 style="font-weight: bold;"> 엘리베이터를 <span style="color:#B9729E;">호출</span> 해보세요</h4>
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
    </div>
  </div>

  <div id="session" v-if="session" class="container d-flex justify-content-center align-items-center">
    <!-- 세션 1 => 상대방 프로필 확인 -->
    <!-- <div id="session_1" v-if="sessionLevel===1" class="container" style="color: #FAFAFA;"> -->
    <!-- <div id="session_1" v-if="sessionLevel===1" class="container" style="color: #77a094"> -->
    <div id="session_1" v-if="sessionLevel===1" class="container">
      <h1>세션 ID : {{this.mySessionId}}</h1>
      <div class="col-auto">
        <h1 class="col-auto" style="font-weight:bold; margin-bottom: 20px;"> 안녕하세요! <span class="col-auto" style="color:#B9729E;">{{currentUser.username}} </span> 님</h1>
      </div>
      <div class="container" style="width: 80%;">
        <div>
          <div v-if="currentUserCount==0" class="d-flex justify-content-center align-items-center col-auto" style="flex-direction: column;">
            <button class="my-3" style="background-color: #FAFAFA" type="button" id="#buttonIcon" disabled>
              <span style="color : #B9729E;" class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>
              <span class="mx-1" style="font-weight:bold; color : black;">작동중</span>
            </button>
            <oneby-one
            ref="elevatorOpen"
            style="width: 90%; height: 90%"
            ></oneby-one>
            <!-- 세션 종료 -->
            <!-- <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="닫힘"> -->
            <button id="buttonIcon" @click="leaveSession">
              <i class='bx bxs-chevron-down-circle' style="font-size: 50px; color: red;" ></i>
            </button>
          </div>
          <div v-else class="d-flex justify-content-center align-items-center col-auto" style="flex-direction: column;">
            <!-- 상대방의 호감 표시 확인 -->
            <div v-if="this.levelOneCount === 0">
              <i class='bx bxs-heart' style="font-size:  50px;"></i>
            </div>
            <div v-else-if="this.levelOneCount === 1">
              <i class='bx bxs-heart' style="font-size: 50px; color:#B9729E;"></i>
            </div>
            <oneby-one
            ref="elevatorOpen"
            style="width: 90%; height: 90%"
            >
            </oneby-one>
            <div v-if="this.currentUserCount === 1">
              <h3><span style="color: #B9729E; font-weight: bold;">이름 : </span><span style="font-family: 'GangwonEdu_OTFBoldA';">{{this.strangerNickname}}</span></h3>
              <h3><span style="color: #B9729E; font-weight: bold;">나이 : </span><span style="font-family: 'GangwonEdu_OTFBoldA';">{{this.strangerAge}}</span></h3>
            </div>
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
            <div class="col-auto">
              <transition
              enter-active-class="animate__animated animate__fadeIn"
              leave-active-class="animate__animated animate__fadeOut"
              >
                <div class="container" style="width: 80%; height : 80%;">
                  <div id="profile-container" class="col-auto d-flex justify-content-center align-items-center position-relative" v-if="currentUserCount===1">
                    <user-profile v-for="sub in subscribers"
                    :key="sub.stream.connection.connectionId"
                    :stranger="sub.stream.connection.data"
                    :currentUser ="currentUser"
                    @click.native="updateMainVideoStreamManager(sub)"
                    @sendStarngerId="sendStarngerId"
                    @sendStrangerObject="sendStrangerObject"
                    @sendStrangerNickname="sendStrangerNickname"
                    @sendStrangerUserid="sendStrangerUserid"
                    @sendStrangerAge="sendStrangerAge"
                    >
                    </user-profile>
                  </div>
                </div>
              </transition>
            </div>
          </div>
        </div>
      </div>

    </div>

    <!-- 세션 2 => 상대방 얼굴 확인 10초 카운트 및 질문 -->
    <!-- <div id="session_2" v-if="sessionLevel===2" class="container" style="width: 80%; color: #FAFAFA;"> -->
    <div id="session_2" v-if="sessionLevel===2" class="container" style="width: 80%;">
      <div class="contaniner my-5" v-if="currentUser">
        <h1 style="font-weight: bold;"> Hello I'm <span style="color : #B9729E;">{{this.strangerNickname}}</span></h1>
          <div class="container" style="width: 80%;">
            <b-progress height="2rem" show-progress :max="8" class="mb-3" style="background-color: #b5b1ae;" >
              <b-progress-bar variant="$black: #fff !default;" :value="profileopencount" animated show-progress
              style="background-color : #BE7292 !important">
                <div class="d-flex justify-content-center">
                  <span v-if="profileopencount===7">
                    <h3 class="mt-2 fs-3">엘리베이터에서 나갈까요?</h3>
                  </span>
                  <span v-else-if="profileopencount%2===1">
                    <h3 class="mt-2 fs-3">궁금해?</h3>
                  </span>
                  <span v-else-if="profileopencount%2===0">
                    <h3 class="mt-2 fs-3">It's Me!</h3>
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
          <div class="container mx-2 col-auto" id="publisher_container">
            <user-video :stream-manager="publisher" :nickname="currentUser.username" :sessionLevel="sessionLevel" @click.native="updateMainVideoStreamManager(publisher)"/>
          </div>
          <div class="container col-auto d-flex justify-content-center align-items-center mx-2" style="flex-direction: column;">
            <div>
              <timer-animation style="width: 100px; height: 100px;"></timer-animation>
            </div>
            <div>
              <h4 style="font-weight: bold;">{{tenseconds}}</h4>
            </div>
          </div>
          <div class="container col-auto mx-2" id="subscriber_container">
            <user-video v-for="sub in subscribers" :nickname="strangerNickname" :sessionLevel="sessionLevel" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
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
    <div id="session_3" v-if="sessionLevel===3" class="container">
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
      <div class="flex_box">
        <img src="@/img/profile.png"/>
        <!-- <user-profile style="position:absolute; width:295px; top:353px; "></user-profile> -->
        <!-- 비디오 출력 부분  -->
        <div id="video-container" class="container">
          <div class="d-flex">
            <div style="position:relative;">
              <user-video style="width:188%; height: 90%; margin-top:50px" v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
            </div>
            <div class="mx-3" style="position:absolute; top:900px; left:650px; z-index: 1;">
              <user-video style="width:300px;" :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
            </div>
          </div>
        </div>
        <img src="@/img/profile.png" style="position:relative;"/>
        <chat-view :roomid="roomid" style="position:absolute; right:135px; width:295px; height:395px; top:203px; "></chat-view>
      </div>
      <div class="table" style="position:relative">
        <img src="@/img/table.png" style="width: 100%; height: 100%; object-fit: cover; z-index: -1;" />
      </div>
      <img src="@/img/mirror.png" style="position:absolute; width:315px; height:155px; top:703px; left:600px"/>
      <div style="position:absolute; top:900px; left:400px">
        <video-bottom
        @audioOnOff="audioOnOff"
        :sessionLevel="sessionLevel"
        ></video-bottom>
      </div>
       <!-- 세션 종료 -->
      <input style="position:absolute; top:1000px; right:500px;" class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="removeMessage()" value="Button"/>
      <input style="position:absolute; top:1000px; right:300px;"  class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="[deleteRoom(), removeMessage(), leaveSession()]" value="Leave session"/>
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
    TimerAnimation
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
      strangerAge: '',
      roomid: 1
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
      this.strangerAge = ''
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
      this.tenseconds = 10000000
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
    sendStrangerAge (data) {
      this.strangerAge = data
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
    ToProfile () {
      this.$router.push('/editphoto')
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
    }
  }
}
</script>

<style>
#myProfile{
  margin-top: 100px;
  width: 250px;
  height: 200px;
}

#buttonIcon{
  background-color:transparent;
  border: 0;
  outline: 0;
}
.flex_box{
  display:flex;
  justify-content: space-between;
  align-items: center;
}

</style>
