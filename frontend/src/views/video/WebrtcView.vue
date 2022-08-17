<template>
<div id="main-container" class="container" style="width: 80%; height: 80%;">
  <div id="join" v-if="!session">
    <div id="join-dialog" class="jumbotron vertical-center">
      <div class="container">
        <!-- Greeting Message -->
        <div class="d-flex justify-content-center " style="flex-direction: row;">
          <h1 class="col-auto" style="font-weight: bold;">안녕하세요!</h1>
          <h1 class="col-auto mx-3 sessionColor"> {{currentUser.username}} </h1>
          <h1 class="col-auto" style="font-weight: bold;">님</h1>
        </div>
        <!-- Main Context Box -->
        <div class="d-flex justify-content-around my-5 align-items-center">
          <div class="row">
            <!-- User Profile Box -->
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
            <!-- How to Use Box-->
            <div class="col-auto d-flex justify-content-center">
              <div class="d-flex justify-content-center">
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
                  <!-- Elevator Animation -->
                  <div class="container d-flex justify-content-center align-items-center" id="elevatorAnimation">
                    <div class="container">
                      <div class="container d-flex justify-content-around align-items-center">
                        <div class="col-md-auto col-auto">
                          <elevator-infinity style="width: 200px; height: 125px"></elevator-infinity>
                        </div>
                        <!-- How to use 1st row -->
                        <div class="col-md-auto col-auto mx-5">
                          <span style="font-family : 'GangwonEdu_OTFBoldA'">
                            <h4 style="font-weight:bold;">엘리베이터에 <span class="sessionColor">탑승</span>하세요</h4>
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
                      <!-- How to use 2nd row -->
                      <div class="container d-flex justify-content-around align-items-center">
                        <div class="col-auto">
                          <span class="align-middle">
                            <h4 style="font-weight:bold;">10초 동안 <span class="sessionColor">고민</span>하세요</h4>
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
                      <div class="col-auto container align-items-center mt-3">
                        <span class="align-middle">
                          <h4 style="font-weight: bold;"> 엘리베이터를 <span class="sessionColor">호출</span> 해보세요</h4>
                        </span>
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

  <div id="session" v-if="session" class="container d-flex justify-content-center align-items-center">
    <!-- *****************************************-->
    <!-- ****************Session1*****************-->
    <!-- *****************************************-->
    <!-- 세션 1 => 상대방 프로필 확인 -->
    <div id="session_1" v-if="sessionLevel===1" class="container">
      <!-- Greet Message Box -->
      <div class="col-auto">
        <h1 class="col-auto" style="font-weight:bold; margin-bottom: 20px;"> 안녕하세요! <span class="col-auto sessionColor" >{{currentUser.username}} </span> 님</h1>
      </div>
      <!-- Main Context Section -->
      <div class="container" style="width: 80%;">
        <div>
          <!-- Waiting Time Section -->
          <div v-if="currentUserCount==0" class="d-flex justify-content-center align-items-center col-auto" style="flex-direction: column;">
            <button class="my-3" style="background-color: #FAFAFA" type="button" id="#buttonIcon" disabled>
              <span style="color : #B9729E;" class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>
              <span class="mx-1" style="font-weight:bold; color : black;">작동중</span>
            </button>
            <!-- Elevator Animation -->
            <oneby-one
            ref="elevatorOpen"
            style="width: 90%; height: 90%"
            ></oneby-one>
            <!-- Leave Session Button -->
            <button id="buttonIcon" @click="leaveSession">
              <i class='bx bxs-chevron-down-circle' style="font-size: 50px; color: red;" ></i>
            </button>
          </div>
          <!-- User Matching Success Section -->
          <div v-else class="d-flex justify-content-center align-items-center col-auto" style="flex-direction: column;">
            <!-- Check OK Sign Section -->
            <div v-if="this.levelOneCount === 0">
              <i class='bx bxs-heart' style="font-size:  50px;"></i>
            </div>
            <div v-else-if="this.levelOneCount === 1">
              <i class='bx bxs-heart' style="font-size: 50px; color:#B9729E;"></i>
            </div>
            <!-- Elevator Animation Section -->
            <oneby-one
            ref="elevatorOpen"
            style="width: 90%; height: 90%"
            >
            </oneby-one>
            <!-- Simple User Information -->
            <div v-if="this.currentUserCount === 1">
              <h3><span class="sessionColor">이름 : </span><span class="sessionColor" style="font-family: 'GangwonEdu_OTFBoldA';">{{this.strangerNickname}}</span></h3>
              <h3><span class="sessionColor">나이 : </span><span class="sessionColor" style="font-family: 'GangwonEdu_OTFBoldA';">{{this.strangerAge}}</span></h3>
            </div>
            <!-- Click OK Sign -->
            <div class="d-flex justify-content-center" id="elevatorButton">
              <!-- 상대방이 마음에 든다는 신호 -->
              <like-you
              :levelOneCount="levelOneCount"
              :session="session"
              >
              </like-you>
              <!-- Leave Session Button -->
              <button id="buttonIcon" @click="leaveSession">
                <i class='bx bxs-chevron-down-circle' style="font-size: 50px; color: red;" ></i>
              </button>
            </div>
            <!-- Show Profile -->
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
    <!-- *****************************************-->
    <!-- ************** Session 2 ****************-->
    <!-- *****************************************-->
    <div id="session_2" v-if="sessionLevel===2" class="container">
      <iframe src="@/img/silence.mp3" allow="autoplay" id="back-audio" style="display:none"></iframe>
      <audio id="back-audio" autoplay>
      <source src="@/img/pianomoment.mp3">
      </audio>
      <div class="row my-5" v-if="currentUser" style="height:20%">
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
       <div class="row d-flex justify-content-center align-items-center" style="height: 200px;">
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
      <div class="row">
        <div class="col-5">
          <user-video :stream-manager="publisher" :nickname="currentUser.username" :sessionLevel="sessionLevel" @click.native="updateMainVideoStreamManager(publisher)"/>
        </div>
        <div class="col-2">
          <div class="row d-flex justify-content-center align-items-center">
            <timer-animation style="width: 100px; height: 100px;"></timer-animation>
            <h4 style="font-weight: bold;">{{tenseconds}}</h4>
          </div>
        </div>
        <div class="col-5">
          <user-video v-for="sub in subscribers" :nickname="strangerNickname" :sessionLevel="sessionLevel" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
        </div>
        <!-- <div id="video-container" class="container d-flex justify-content-center align-items-center">
          <div class="container mx-2 col-auto" id="publisher_container">
          </div>
          <div class="container col-auto d-flex justify-content-center align-items-center mx-2" style="flex-direction: column;">
            <div>
            </div>
            <div>
              <h4 style="font-weight: bold;">{{tenseconds}}</h4>
            </div>
          </div>
          <div class="container col-auto mx-2" id="subscriber_container">
          </div>
        </div> -->
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
          <div class="col m-1">
            <audio-button style="color: red;" @audioOnOff="audioOnOff" :sessionLevel="sessionLevel"></audio-button>
          </div>
        </div>
      </div>
    </div>
    <!-- *****************************************-->
    <!-- ************** Session 3 ****************-->
    <!-- *****************************************-->
    <div id="session_3" v-if="sessionLevel===3" class="container3 p-2">
      <!-- Random Question Box -->
      <div class="row d-flex">
        <h3 class="col blink" style="font-family: 'GangwonEdu_OTFBoldA'; margin-top:6px; color:red;">최종 선택 까지 : {{tenseconds}}초</h3>
        <random-button
        :session="session"
        :tenseconds="tenseconds"/>
        <random-question
        ref="qustionRequest"
        @subject="subject"/>
      </div>
      <!-- Main Context Row -->
      <div class="row d-flex justify-content-md-center align-items-center">
        <!-- Left Column -->
        <div class="col-auto">
          <!-- Camera Row -->
          <div class="row d-flex justify-content-md-center align-items-center ">
            <!-- Other Camera Section -->
            <div class="col-auto p-auto">
              <user-video style="z-index:0;" v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
            </div>
            <!-- My Camera Section -->
            <div class="col-auto p-auto">
              <user-video class="my-video" style="z-index:0;" :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
            </div>
            <div class="col-auto"></div>
          </div>
          <!-- Final Select Row -->
          <h2 style="font-family: 'GangwonEdu_OTFBoldA'; margin-top:-15px; color:#ffffff; margin-top: -5px; margin-bottom:-10px;">최종 선택</h2>
          <!-- Select Buttons -->
          <div class="d-flex justify-content-center" >
            <!-- OK Button -->
            <adding-profile
              @profileOnOff="profileOnOff"
              :profileopencount="profileopencount"
              :session="session"
              :countTogether ="countTogether"
              style="z-index:2;"
              >
            <!-- Leave session Button -->
            </adding-profile>
            <button id="buttonIcon" @click="[deleteRoom(), removeMessage(), leaveSession()]">
              <i class='bx bxs-chevron-down-circle' style="font-size: 50px; color: red; z-index:2;" ></i>
            </button>
          </div>
          <!-- Menu Button Grid : Mic, Camera, Chat, Profile -->
          <div class="row">
            <!-- Grid Section -->
            <div class="col-auto"></div>
            <div class="col-6 buttonGrid">
              <!-- Grid Title -->
              <!-- <div class="row p-3"><h4 style="color : white;">Menu</h4></div> -->
              <div class="row p-3 d-flex justify-content-center align-items-center">
                <!-- Audio Button -->
                <div class="col m-1">
                  <audio-button class="button1" style="color: red;" @audioOnOff="audioOnOff" :sessionLevel="sessionLevel"></audio-button>
                </div>
                <!-- Video Button -->
                <div class="col m-1">
                  <video-button class="button1" style="color: red;" @videoOnOff="videoOnOff" :sessionLevel="sessionLevel"></video-button>
                </div>
                <!-- Chatting Button -->
                <!-- <div class="col m-1">
                  <button class="button1">
                    <img src="@/img/comment.svg" alt="chatting" id="buttonOpenChat" @click="openChat" style="width:30px; height:10px;"/>
                  </button>
                </div> -->
                <!-- Profile Button -->
                <!-- <div class="col m-1">
                  <button class="button1">
                    <img src="@/img/user.svg" alt="profile" id="buttonOpenProfile" @click="openProfile" style="width:30px; height:10px; z-index:2;"/>
                  </button>
                  </div> -->
                <div class="col m-1">
                    <button class="button1">
                      <b-button class="button1"  v-b-toggle.sidebar-right style="background-color:transparent; border-color: transparent;">Menu</b-button>
                    </button>
                </div>
              </div>
            </div>
            <div class="col-auto"></div>

          </div>
        </div>
        <!-- Side Bar : Chatting, Profile Section -->
        <div class="col-auto" v-if="is_show_c || is_show_p">
          <!-- Chatting Section -->
          <div v-show="is_show_c" class="pop_chat">
            <chat-view :roomid=this.roomid style="width:100%; height:100%;"></chat-view>
          </div>
          <!-- Profile Section -->
          <div v-show="is_show_p" class="pop_profile">
            <profile-view v-for="sub in subscribers"
                :key="sub.stream.connection.connectionId"
                :stranger="sub.stream.connection.data"
                :countTogether ="countTogether"
                @click.native="updateMainVideoStreamManager(sub)" style="width:100%; height:100%;"></profile-view>
          </div>
        </div>
      </div>
      <template>
        <div>
          <b-sidebar id="sidebar-right" title="Side Bar" right shadow width="500px">
            <div class="px-3 py-2">
              <profile-view v-for="sub in subscribers"
              :key="sub.stream.connection.connectionId"
              :stranger="sub.stream.connection.data"
              :countTogether ="countTogether"
              @click.native="updateMainVideoStreamManager(sub)" style="width:100%; height:100%;"></profile-view>
              <chat-view :roomid=this.roomid style="width:100%; height:100%;"></chat-view>
            </div>
          </b-sidebar>
        </div>
      </template>
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
// import VideoBottom from '@/views/video/components/VideoBottom'
import AddingProfile from '@/views/video/components/AddingProfile'
import StrangerProfile from '@/views/video/components/StrangerProfile'
import RandomButton from '@/views/video/components/RandomButton'
import RandomQuestion from '@/views/video/components/RandomQuestion'
import ChatView from '@/views/chat/ChatInMeeting'
import ElevatorInfinity from '@/views/video/animation/ElevatorInfinity'
import elevatorAnimation from '@/views/video/animation/ElevatorAnimation'
import OnebyOne from '@/views/video/animation/OnebyOne'
import TimerAnimation from '@/views/video/animation/TimerAnimation'
import ProfileView from '@/views/video/components/ProfileView'
import VideoButton from './components/VideoButton.vue'
import AudioButton from './components/AudioButton.vue'
import swal from 'sweetalert'

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
    // VideoBottom,
    StrangerProfile,
    ChatView,
    ElevatorInfinity,
    RandomButton,
    RandomQuestion,
    OnebyOne,
    TimerAnimation,
    ProfileView,
    elevatorAnimation,
    AudioButton,
    VideoButton
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
      roomid: 1,
      is_show_c: false,
      is_show_p: false,
      level3flag: false
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
      this.level3flag = false
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

      // window.addEventListener('beforeunload', this.leaveSession)

      if (this.session !== undefined) {
        console.log(this.subscriber)
      }
    },

    leaveSession () {
      this.level3flag = true
      // strangerId: this.strangerId
      axios.post(api.video.userLeaveSession(), { userFrom: this.currentUser.userid, userTo: this.strangerObject }).then(res => {
        console.log(res)
      }).catch(err => {
        console.log(err)
      })
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) {
        if (this.sessionLevel === 3 && this.level3flag === true && this.profileopencount !== 10) {
          swal({
            title: '매칭 실패',
            text: '상대방의 요청으로 연결이 종료되었습니다.',
            icon: 'error'
          })
        }
        this.session.disconnect()
      }
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
      document.getElementById('app').style.backgroundImage = ''
      document.getElementById('app').style.backgroundColor = 'white'

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
    sendStrangerAge (data) {
      this.strangerAge = data
    },
    subject (data) {
      this.randomSubject = data
    },
    createRoom () {
      console.log('current user', this.currentUser.id)
      console.log('stranger', this.strangerId)
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
      ).catch(function (error) {
        console.log('Axios creatRoom() error, catch block')
        if (error.response) {
          axios({
            method: 'get',
            url: 'localhost:8080/api/v1/chat/findroom',
            headers: { 'content-type': 'application/json' },
            data: { user1: this.id1, user2: this.id2 }
          }).then(res => {
            console.log(res.data)
            this.roomid = res.data
          }).catch(function (error) {
            if (error.response) {
              console.log('Axios findRoom() error, catch block')
            }
          })
        }
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
        res => {
          // alert('success')
          console.log('delete')
          swal({
            title: '매칭 실패',
            text: '엘리베이터에서 나오셨습니다.',
            icon: 'success'
          })
        }
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
      // 프로필이 오픈되어있는 상황에서 채팅을 오픈하려는 경우 -> 프로필 닫기
      if (!this.is_show_c && this.is_show_p) {
        this.is_show_p = false
      }
      this.is_show_c = !this.is_show_c
    },
    openProfile () {
      // 채팅이 오픈되어있는 상황에서 프로필을 오픈하려는 경우 -> 채팅 닫기
      if (this.is_show_c && !this.is_show_p) {
        this.is_show_c = false
      }
      this.is_show_p = !this.is_show_p
    },
    ToProfile () {
      this.$router.push('/editphoto')
    },
    // 매칭 성공 modal
    matchSuccess () {
      this.$bvModal.msgBoxOk('매칭이 성공 되었습니다!', {
        title: '축하드립니다!',
        size: 'sm',
        buttonSize: 'sm',
        okVariant: 'success',
        headerClass: 'p-2 border-bottom-0',
        footerClass: 'p-2 border-top-0',
        centered: true
      })
        .then(value => {
          if (value === true) {
            this.leaveSession()
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    videoOnOff ({ video }) {
      console.log('video')
      console.log(video)
      this.publisher.publishVideo(video)
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
      // window.addEventListener('beforeunload', (event) => {
      //   this.leaveSession()
      //   // swal({
      //   //   title: '매칭 실패',
      //   //   text: '상대방의 요청으로 연결이 종료되었습니다.',
      //   //   icon: 'error'
      //   // })
      //   // this.$router.push('/')
      //   window.location.reload(true)
      //   event.preventDefault()
      //   event.returnValue = 'TEST'
      // })
    } else {
      alert('잘못된 접근')
      this.$router.back()
    }
  },
  watch: {
    // 시간이 다 되면 자동적으로 세션 종료
    autoleaveflag () {
      this.leaveSession()
    },
    // 입장과 동시에 시간 Count
    sessionjoined (sessionjoined) {
      if (sessionjoined === 1) {
        if (this.currentUserCount === 1) {
          this.startTimer()
        }
      }
    },
    profileopencount () {
      if (this.profileopencount === 10) {
        this.matchSuccess()
      } else if (this.profileopencount !== 0 && this.profileopencount % 2 === 0) {
        // profileopencount 가 짝수 일 때마다 addCount가 증가하고,
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
        document.getElementById('app').style.backgroundImage = 'url(https://images.unsplash.com/photo-1621343607959-5d11ff0f1e39?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDF8fHxlbnwwfHx8fA%3D%3D&w=1000&q=80)'
        console.log('Its Level 3')
        axios.post('https://localhost:8080/api/v1/statistics/individual/addMatchingHistory', { userid1: this.currentUser.id, userid2: this.strangerId })
          .then(res => {
            console.log(res)
          }).catch(err => {
            console.log(err)
          })
        this.roomid = this.createRoom()
        this.tenseconds = 600
      }
    },
    tenseconds () {
      if (this.sessionLevel === 3 && this.tenseconds === 1) {
        this.id = this.createRoom()
      }
    },
    currentUserCount () {
      if (this.currentUserCount === 0) {
        this.strangerLeaveFlag = true
      } else {
        this.$refs.elevatorOpen.meetStranger()
      }
    },
    strangerLeaveFlag () {
      if (this.strangerLeaveFlag === true) {
        this.leaveSession()
      }
    }
  }
}
</script>

<style scoped>

.sessionColor {
  color: rgb(213, 136, 209);
  font-weight: bold;
}
.buttonGrid{
  background-color: transparent;
  border-radius: 20px;
  border-color: 2px, white;
  margin-right: auto;
  margin-left: auto;

}

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
.flex_table{
  display:flex;
  justify-content: space-between;
  align-items: center;
  width:100%;
}
.pop_chat{
}
.pop_profile{
}
.button_grid{
  display: grid;
  place-items: center;
  grid-template-columns: 2fr 2fr 50px 2fr 2fr;
  border: none;
  position:absolute;
  bottom:0;
  width:100%;
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

#join{
  border: 3px solid #FAFAFA;
  border-radius: 10px;
  padding: 2%;
  background-color: rgba(216, 213, 213, 0.4);

}
#session_1{
  border: 3px solid #FAFAFA;
  border-radius: 10px;
  padding: 2%;
  background-color: rgba(143, 142, 142, 0.4);

}
#session_2{
  border: 3px solid #FAFAFA;
  border-radius: 10px;
  padding: 2%;
  background-color: rgba(143, 142, 142, 0.4);

}
.container3 {
  height: auto;
  width: auto;
  background-repeat : no-repeat;
  background-size : cover;
  position:absolute;
  border-radius: 10px;
  top: 5%;
  bottom: 5%;
  left: 5%;
  right: 5%;
  padding: 3%;
  margin: 20px;
}
@keyframes blink-effect {
  50% {
    opacity: 0;
  }
}
.blink {
  animation: blink-effect 1s step-end infinite;
}
.button1 {
 /* padding: 17px 40px; */
 border-radius: 50px;
 border: 0;
 height: 40px;
 width: 140px;
 padding: auto;
 background-color: rgb(187, 115, 201);
 box-shadow: rgb(0 0 0 / 5%) 0 0 8px;
 letter-spacing: 1.5px;
 text-transform: uppercase;
 font-size: 15px;
 transition: all .5s ease;
}

.button1:hover {
 letter-spacing: 3px;
 background-color: hsl(261deg 80% 48%);
 color: hsl(0, 0%, 100%);
 box-shadow: rgb(93 24 220) 0px 7px 29px 0px;
}

.button1:active {
 letter-spacing: 3px;
 background-color: hsl(261deg 80% 48%);
 color: hsl(0, 0%, 100%);
 box-shadow: rgb(93 24 220) 0px 0px 0px 0px;
 transform: translateY(10px);
 transition: 100ms;
}
</style>
