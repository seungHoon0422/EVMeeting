<template>
  <div>
    <!-- <h1>photobox</h1> -->
    <div class="img-box">
      <div>{{idx}}</div>
      <input id="imgUpload" @change="upload" type="file" accept="image/*" style="display:none;">
      <label for="imgUpload" v-if="!image">
        <i class="fa-solid fa-circle-plus icon-color"></i>
      </label>
      <!-- <img :src="image" alt=".." v-if="image" class="img"> -->
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      image: '' // 이거를 이용하고 싶은데
    }
  },
  methods: {
    upload (e) {
      const file = e.target.files
      const url = URL.createObjectURL(file[0])
      // console.log(url)
      this.image = url
      this.imageEmit()
    },
    imageEmit () {
      this.$emit('child-image-url', this.image, this.idx)
    }
  },
  props: {
    idx: Number
  }
}
</script>

<style>
.img-box {
  width: 200px;
  height: 200px;
  border: 5px solid #F88F6D;
  border-radius: 10px;
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
</style>
