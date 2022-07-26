<<<<<<< HEAD
import { createRouter, createWebHashHistory } from "vue-router";
import login from "../views/Login.vue";
import Room from "../views/Room.vue";
import RoomList from "../views/RoomList.vue";
=======
import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import LogoutView from '../views/LogoutView.vue'
import SignupView from '../views/SignupView.vue'
import NotFound404 from '../views/NotFound404.vue'
import UploadPhotoView from '../views/UploadPhotoView.vue'
import ProfileView from '../views/ProfileView.vue'
import EditPasswordView from '../views/EditPasswordView.vue'
import EditProfileView from '../views/EditProfileView.vue'
import DeleteProfileView from '../views/DeleteProfileView.vue'
import EditPhotoView from '../views/EditPhotoView.vue'

Vue.use(VueRouter)
>>>>>>> 18c6715a8ed890a0d0348b46b3cc1f61117d6914

const routes = [
  {
    path: "/",
    name: "login",
    component: login
  },
  {
    path: "/room",
    name: "Room",
    component : Room
  },
  {
<<<<<<< HEAD
    path: "/rooms",
    name: "Roomlist",
    component : RoomList
=======
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
    path: '/profile', // variable routing 해야함 :username
    name: 'profile',
    component: ProfileView
  },
  {
    path: '/editpwd', // variable routing 해야함 :username
    name: 'editpwd',
    component: EditPasswordView
  },
  {
    path: '/editprofile', // variable routing 해야함 :username
    name: 'editprofile',
    component: EditProfileView
  },
  {
    path: '/deleteprofile', // variable routing 해야함 :username
    name: 'deleteprofile',
    component: DeleteProfileView
  },
  {
    path: '/editphoto', // variable routing 해야함 :username
    name: 'editphoto',
    component: EditPhotoView
  },
  {
    path: '/404',
    name: '404',
    component: NotFound404
>>>>>>> 18c6715a8ed890a0d0348b46b3cc1f61117d6914
  }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes
});

export default router;
