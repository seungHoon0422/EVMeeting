<template>
  <div class="box">
    <h1 class="font-style mt-3">사진 등록</h1>
    <form  @submit.prevent="uploadPhoto">
      <!-- <div class="container container-position"> -->
      <!-- <div class="row my-3"> -->
        <!-- <div class="col"> -->
          <div class="img-box">
            <input id="imgUpload1" @change="upload1" type="file" accept="image/*" style="display:none;">
            <label for="imgUpload1" v-if="!photoUrl">
              <i class="fa-solid fa-circle-plus icon-color"></i>
            </label>
            <img :src="photoUrl" alt=".." v-if="photoUrl" class="img">
          </div>
        <!-- </div> -->
        <!-- <div class="col">
          <div class="img-box">
          <input id="imgUpload2" @change="upload2" type="file" accept="image/*" style="display:none;">
          <label for="imgUpload2" v-if="!images.image2">
            <i class="fa-solid fa-circle-plus icon-color"></i>
          </label>
          <img :src="images.image2" alt=".." v-if="images.image2" class="img">
          </div>
        </div>
        <div class="col">
          <div class="img-box">
          <input id="imgUpload3" @change="upload3" type="file" accept="image/*" style="display:none;">
          <label for="imgUpload3" v-if="!images.image3">
            <i class="fa-solid fa-circle-plus icon-color"></i>
          </label>
          <img :src="images.image3" alt=".." v-if="images.image3" class="img">
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="img-box">
            <input id="imgUpload4" @change="upload4" type="file" accept="image/*" style="display:none;">
            <label for="imgUpload4" v-if="!images.image4">
              <i class="fa-solid fa-circle-plus icon-color"></i>
            </label>
            <img :src="images.image4" alt=".." v-if="images.image4" class="img">
          </div>
        </div>
        <div class="col">
          <div class="img-box">
            <input id="imgUpload5" @change="upload5" type="file" accept="image/*" style="display:none;">
            <label for="imgUpload5" v-if="!images.image5">
              <i class="fa-solid fa-circle-plus icon-color"></i>
            </label>
            <img :src="images.image5" alt=".." v-if="images.image5" class="img">
          </div>
        </div>
        <div class="col">
          <div class="img-box">
            <input id="imgUpload6" @change="upload6" type="file" accept="image/*" style="display:none;">
            <label for="imgUpload6" v-if="!images.image6">
              <i class="fa-solid fa-circle-plus icon-color"></i>
            </label>
            <img :src="images.image6" alt=".." v-if="images.image6" class="img">
          </div>
        </div>
      </div> -->
      <!-- </div> -->
      <button class="upload-button" type="submit">등록 완료</button>
      <!-- <router-link :to="{ name: 'home' }" class="btn rounded length mt-4">등록 완료</router-link> -->
    </form>
  </div>

</template>

<script>
import { mapActions } from 'vuex'
import api from '@/api/api'
import axios from 'axios'

export default {
  name: 'UploadPhotoView',
  data () {
    return {
      images: {
        image1: '',
        image2: '',
        image3: '',
        image4: '',
        image5: '',
        image6: ''
      },
      photoUrl: ''
    }
  },
  methods: {
    ...mapActions(['uploadPhotos']),
    upload1 (e) {
      const file = e.target.files
      const url = URL.createObjectURL(file[0])
      // console.log(url)
      this.photoUrl = url
    },
    uploadPhoto () {
      const frm = new FormData()
      const photoFile = document.getElementById('imgUpload1')
      console.log(photoFile.files[0])
      frm.append('imgUpload1', photoFile.files[0])
      axios({
        url: api.accounts.uploadPhoto(),
        method: 'post',
        data: frm,
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then(res => {
          console.log(res)
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
  border: 5px solid #F88F6D;
  border-radius: 10px;
  margin: 20px auto;
}

.img {
  width:100%;
  height:100%;
  border-radius: 10px;
}

.icon-color {
  color: #F88F6D;
  font-size: 30px;
  margin-top: 50px;
  cursor: pointer;
}

.font-style {
  color: #F88F6D;
  font-size: 40px;
}

.container-position {
  position: relative;
  left: 15px;
}

.box {
  border: 5px solid #F88F6D;
  width: 800px;
  height: 600px;
  margin: 0 auto;
  position: relative;
  border-radius: 10px;
}

.upload-button {
  border: 5px solid #F88F6D;
  background-color: #F88F6D;
  color: white;
  border-radius: 5px;
  margin-top: 25px;
  padding: 0 25px;
}
</style>
