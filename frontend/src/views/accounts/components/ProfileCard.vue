<template>
  <div>
    <div class="box">
      <!-- <h1 class="font-style">profileCard</h1> -->
      <div class="img-box">
        <img src="@/img/iu.jpg" alt="..">
      </div>
      <div class="description">
        <p><i class="fa-solid fa-image-portrait"></i> {{currentUser.username}} | {{currentUserAge}}세 </p>
        <div class="d-flex">
          <p class="me-5"><i class="fa-solid fa-person-arrow-up-from-line"></i> {{currentUser.height}}cm</p>
          <p><i class="fa-solid fa-weight-scale"></i> {{currentUser.weight}}kg</p>
        </div>
        <hr>
        <div class="d-flex">
          <p class="me-5"><i class="fa-solid fa-magnifying-glass-plus"></i> {{currentUser.hobby}}</p>
          <p><i class="fa-solid fa-atom"></i> {{currentUser.mbti}}</p>
        </div>
        <div class="d-flex">
          <p class="me-5"><i class="fa-solid fa-champagne-glasses"></i> {{currentUser.drink}}</p>
          <p><i class="fa-solid fa-smoking"></i>
            <span v-if="currentUser.cigarette">{{currentUser.cigarette}}</span>
            <span v-else> 미입력 </span>
          </p>
        </div>
        <hr>
        <p><i class="fa-solid fa-quote-left"></i> {{currentUser.description}}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'ProfileCard',
  computed: {
    ...mapGetters(['currentUser', 'currentUserAge'])
  }
}
</script>

<style scoped>
.box {
  text-align: left;
  border: 3px solid #F88F6D;
  width: 350px;
  height: 100%;
  margin-right: 10px;
}

.font-style {
  font-size: 30px;
  color: #F88F6D;
}

.img-box {
  background-color: white;
  width: 345px;
  height: 200px;
}

img {
  width:100%;
  height:100%;
}

.description {
  padding: 20px;
  font-size: 20px;
}
</style>
