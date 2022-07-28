<template>
  <div>
    <div class="box">
      <h1 class="font-style my-3">프로필</h1>
      <div class="d-flex justify-content-between">
        <statistic-card></statistic-card>
        <profile-card></profile-card>
      </div>
      <div class="button-style d-flex justify-content-between mt-3">
        <router-link :to="{ name: 'editphoto' }"><button>사진 변경</button></router-link>
        <router-link :to="{ name: 'editprofile' }"><button>프로필 변경</button></router-link>
        <router-link :to="{ name: 'editpwd' }"><button>비밀번호 변경</button></router-link>
        <router-link :to="{ name: 'deleteprofile' }"><button>회원 탈퇴</button></router-link>
      </div>
    </div>
  </div>
</template>

<script>
import ProfileCard from '@/views/accounts/components/ProfileCard'
import StatisticCard from '@/views/accounts/components/StatisticCard'

export default {
  name: 'ProfileView',
  components: {
    ProfileCard,
    StatisticCard
  }
}
</script>

<style scoped>
.box {
  width: 800px;
  height: 850px;
  border: 5px solid #F88F6D;
  border-radius: 10px;
  margin: 0 auto;
  position: relative;
}

.font-style {
  color: #F88F6D;
  font-size: 40px;
}

.button-style {
  width: 600px;
  position: absolute;
  right: 10px;
}

button {
  background-color: #F88F6D;
  border-radius: 10px;
  border: none;
  color: white;
  padding: 5px 20px;
}
</style>
