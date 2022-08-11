<template>
  <div class="box">
    <h1 class="font-style my-5">사진 변경</h1>
    <form  @submit.prevent="editPhoto(currentUser.userid)">
          <div class="img-box">
            <input id="imgUpload1" @change="upload1" type="file" accept="image/*" style="display:none;">
            <label for="imgUpload1" v-if="!photoUrl">
              <i class="fa-solid fa-circle-plus icon-color"></i>
            </label>
            <img :src="photoUrl" alt=".." v-if="photoUrl" class="img">
          </div>
      <button class="upload-button" type="submit">변경 완료</button>
    </form>
  </div>

</template>

<script>
import { mapGetters } from 'vuex'
import api from '@/api/api'
import axios from 'axios'
import router from '@/router'

export default {
  name: 'EditPhotoView',
  data () {
    return {
      photoUrl: ''
    }
  },
  computed: {
    ...mapGetters(['currentUser'])
  },
  methods: {
    upload1 (e) {
      const file = e.target.files
      const url = URL.createObjectURL(file[0])
      this.photoUrl = url
    },
    editPhoto (userid) {
      const frm = new FormData()
      const photoFile = document.getElementById('imgUpload1')
      frm.append('imgUpload1', photoFile.files[0])
      axios({
        url: api.accounts.editPhoto(userid),
        method: 'post',
        data: frm,
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then(res => {
          console.log(res)
          router.push({ name: 'home' })
        })
        .catch(err => console.log(err))
    }

  }
}

</script>

<style scoped>
.img-box {
  width: 400px;
  height: 400px;
  border: 5px solid #FAFAFA;
  border-radius: 10px;
  margin: 20px auto;
}

.img {
  width:100%;
  height:100%;
  border-radius: 10px;
}

.icon-color {
  color: #FAFAFA;
  font-size: 30px;
  margin-top: 50px;
  cursor: pointer;
}

.font-style {
  color: #FAFAFA;
  font-size: 40px;
}

.container-position {
  position: relative;
  left: 15px;
}

.box {
  border: 5px solid #FAFAFA;
  width: 800px;
  height: 700px;
  margin: 0 auto;
  position: relative;
  border-radius: 10px;
  background-color: rgba(0,0,0,0.4);
}

.upload-button {
  border: 3px solid #FAFAFA;
  background-color: transparent;
  color: white;
  border-radius: 5px;
  margin-top: 25px;
  padding: 5px 25px;
}

button:hover {
  color: gray;
  background-color: #FAFAFA;
}
</style>
