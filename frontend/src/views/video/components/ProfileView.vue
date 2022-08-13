<template>
  <div>
    <div class="box">
      <table class="table">
        <thead>
          <tr class="">
            <th scope="row" class="fs-5" colspan="2" >{{this.strangerName}}</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row">키</th>
            <td>{{this.height}} cm</td>
          </tr>
          <tr>
            <th scope="row">몸무게</th>
            <td>{{this.weight}} kg</td>
          </tr>
          <tr>
            <th scope="row">관심사</th>
            <td>{{this.hobby}}</td>
          </tr>
          <tr>
            <th scope="row">MBTI</th>
            <td>{{this.mbti}}</td>
          </tr>
          <tr>
            <th scope="row">음주</th>
            <td v-if="this.drink == 'zero'">소주 한 잔</td>
            <td v-else-if="this.drink == 'under1'">소주 1병 미만</td>
            <td v-else-if="this.drink == 'under3'">소주 1-3병</td>
            <td v-else-if="this.drink == 'over3'">소주 3병 이상</td>
          </tr>
          <tr>
            <th scope="row">흡연</th>
            <td v-if="this.cigarette == 'smoke'">흡연</td>
            <td v-else-if="this.cigarette == 'nosmoke'">비흡연</td>
          </tr>
          <tr>
            <th scope="row">자기소개</th>
            <td colspan="3">{{this.description}}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import api from '@/api/api'

export default {
  name: 'ProfileView',
  props: {
    stranger: {
      type: String
    }
  },
  data () {
    return {
      strangerName: this.stranger.split('"')[3],
      birth: 0,
      height: 0,
      weight: 0,
      hobby: null,
      mbti: null,
      drink: null,
      cigarette: null,
      description: null,
      profileList: [
        'birth',
        'height',
        'weight',
        'hobby',
        'mbti',
        'drink',
        'cigarette',
        'description'
      ]

    }
  },
  methods: {
    getProfile () {
      axios.post(api.video.getStrangerProfile(), this.strangerName).then(res => {
        console.log(res.data)
        this.birth = res.data[this.profileList[0]]
        this.height = res.data[this.profileList[1]]
        this.weight = res.data[this.profileList[2]]
        this.hobby = res.data[this.profileList[3]]
        this.mbti = res.data[this.profileList[4]]
        this.drink = res.data[this.profileList[5]]
        this.cigarette = res.data[this.profileList[6]]
        this.description = res.data[this.profileList[7]]
      }).catch(err => {
        console.log(err)
      })
    }
  },
  mounted () {
    this.getProfile()
  }
}
</script>

<style scoped>
@font-face {
    font-family: 'GangwonEdu_OTFBoldA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
.description {
  padding: 20px;
  font-size: 20px;
  background-color: #FAFAFA;
}

.table {
  background-color: transparent;
  color: #FAFAFA;
  /* border: 1px solid #F88F6D; */
}

table, tr, td, th {
 border:none;
 text-align: center;
 font-family: 'GangwonEdu_OTFBoldA';
}
.box {
  width: 400px;
  height: 420px;
  border: 1px solid #FAFAFA;
  border-radius: 10px;
  margin: 0 auto;
  position: relative;
  padding: 0 10px;
  background-color: #5b7b8d;
}
</style>
