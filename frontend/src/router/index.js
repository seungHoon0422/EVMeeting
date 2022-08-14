import store from '@/store'
import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/accounts/LoginView.vue'
import LogoutView from '../views/accounts/LogoutView.vue'
import SignupView from '../views/accounts/SignupView.vue'
import NotFound404 from '../views/accounts/NotFound404.vue'
import UploadPhotoView from '../views/accounts/UploadPhotoView.vue'
import ProfileView from '../views/accounts/ProfileView.vue'
import EditPasswordView from '../views/accounts/EditPasswordView.vue'
import EditProfileView from '../views/accounts/EditProfileView.vue'
import DeleteProfileView from '../views/accounts/DeleteProfileView.vue'
import EditPhotoView from '../views/accounts/EditPhotoView.vue'
import FindPasswordView from '../views/accounts/FindPasswordView.vue'
import PersonalStatView from '../views/accounts/PersonalStatView.vue'
import WebrtcView from '../views/video/WebrtcView.vue'

import chat from '../views/chat/ChatView.vue'
import chatList from '../views/chat/ChatListView.vue'
import chatrooms from '../views/chat/ChattingRoomView.vue'

import TotalStatisticsView from '../views/statistics/TotalStatisticsView.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/logout',
    name: 'logout',
    component: LogoutView
  },
  {
    path: '/signup',
    name: 'signup',
    component: SignupView
  },
  {
    path: '/upload',
    name: 'upload',
    component: UploadPhotoView
  },
  {
    path: '/profile',
    name: 'profile',
    component: ProfileView
  },
  {
    path: '/editpwd',
    name: 'editpwd',
    component: EditPasswordView
  },
  {
    path: '/editprofile',
    name: 'editprofile',
    component: EditProfileView
  },
  {
    path: '/deleteprofile',
    name: 'deleteprofile',
    component: DeleteProfileView
  },
  {
    path: '/editphoto',
    name: 'editphoto',
    component: EditPhotoView
  },
  {
    path: '/findpassword',
    name: 'findpassword',
    component: FindPasswordView
  },
  {
    path: '/personalstat',
    name: 'personalstat',
    component: PersonalStatView
  },
  {
    path: '/404',
    name: '404',
    component: NotFound404
  },
  {
    path: '/chat',
    name: 'chat',
    component: chat
  },
  {
    path: '/chatlist',
    name: 'chatlist',
    component: chatList
  },
  {
    path: '/chatrooms',
    name: 'chatrooms',
    component: chatrooms
  },
  {
    path: '/cam',
    name: 'cam',
    component: WebrtcView
  },
  {
    path: '/totalstat',
    name: 'totalstat',
    component: TotalStatisticsView
  },
  {
    path: '*',
    redirect: '/404'
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  // 엘레베이터 타러 가기 전에 로그인 해야함.
  const { isLoggedIn } = store.getters

  const authPages = ['cam']

  const isAuthRequired = authPages.includes(to.name)

  if (isAuthRequired && !isLoggedIn) {
    // next({ name: 'login' })
    window.location.href = '/login'
  } else {
    next()
  }

  // const { currentUser } = store.getters

  // const photoPages = ['upload']

  // const isPhotoRequired = photoPages.includes(from.name)

  // if (isPhotoRequired && currentUser.photo === null) {
  //   alert('사진을 등록해주세요!')
  //   next({ name: 'upload' })
  // } else {
  //   next()
  // }
})

export default router
