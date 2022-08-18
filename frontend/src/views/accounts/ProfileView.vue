<template>
  <div>
    <div class="container box">
      <div class="d-flex justify-content-center my-3">
          <profile-card></profile-card>
      </div>
      <div class="button-style d-flex justify-content-between">
        <!-- <router-link :to="{ name: 'editphoto' }"><button>사진 변경</button></router-link> -->
        <router-link :to="{ name: 'editprofile' }"><button>프로필 변경</button></router-link>
        <router-link :to="{ name: 'editpwd' }"><button>비밀번호 변경</button></router-link>
        <router-link :to="{ name: 'deleteprofile' }"><button>회원 탈퇴</button></router-link>
      </div>
      <!-- <div class="d-flex justify-content-center my-3">
        <profile-card></profile-card>
      </div>
      <div class="button-style d-flex justify-content-between">
        <router-link :to="{ name: 'editphoto' }"><button>사진 변경</button></router-link>
        <router-link :to="{ name: 'editprofile' }"><button>프로필 변경</button></router-link>
        <router-link :to="{ name: 'editpwd' }"><button>비밀번호 변경</button></router-link>
        <router-link :to="{ name: 'deleteprofile' }"><button>회원 탈퇴</button></router-link>
      </div> -->
    </div>
  </div>
</template>

<script>
import ProfileCard from '@/views/accounts/components/ProfileCard'
// import StatisticCard from '@/views/accounts/components/StatisticCard'

export default {
  name: 'ProfileView',
  components: {
    ProfileCard
    // StatisticCard
  }
}
</script>

<style scoped>
.box {
  width: 70%;
  height: 100%;
  border: 3px solid #2c3e50;
  border-radius: 10px;
  padding-top: 5%;
  padding-bottom: 5%;
  position: relative;
  background-color: rgba(178, 174, 174, 0.4);
}

.font-style {
  color: #FAFAFA;
  font-size: 40px;
}

.button-style {
  width: 600px;
  position: absolute;
  right: 100px;
  margin-top: 500px;
}

button {
  background-color: transparent;
  border-radius: 10px;
  border: 2px solid #FAFAFA;
  color: #FAFAFA;
  padding: 5px 20px;
}

button:hover {
  color: gray;
  background-color: #FAFAFA;
}
</style>
