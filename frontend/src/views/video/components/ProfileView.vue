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
            <th scope="row" ><span style="font-size: 12px;">키</span></th>
            <td>{{this.height}} <span style="font-size: 12px;"></span>cm</td>
          </tr>
          <tr>
            <th scope="row"><span style="font-size: 12px;">몸무게</span></th>
            <td>{{this.weight}} <span style="font-size: 12px;">kg</span></td>
          </tr>
          <tr>
            <th scope="row"><span style="font-size: 12px;">관심사</span></th>
            <td><span style="font-size: 12px;"></span>{{this.hobby}}</td>
          </tr>
          <tr>
            <th scope="row"><span style="font-size: 12px;">MBTI</span></th>
            <td><span style="font-size: 12px;">{{this.mbti}}</span></td>
          </tr>
          <tr>
            <th scope="row"><span style="font-size: 12px;">음주</span></th>
            <td v-if="this.drink == 'zero'"><span style="font-size: 12px;">소주 한 잔</span></td>
            <td v-else-if="this.drink == 'under1'"><span style="font-size: 12px;">소주 1병 미만</span></td>
            <td v-else-if="this.drink == 'under3'"><span style="font-size: 12px;">소주 1-3병</span></td>
            <td v-else-if="this.drink == 'over3'"><span style="font-size: 12px;">소주 3병 이상</span></td>
          </tr>
          <tr>
            <th scope="row"><span style="font-size: 12px;">흡연</span></th>
            <td v-if="this.cigarette == 'smoke'"><span style="font-size: 12px;">흡연</span></td>
            <td v-else-if="this.cigarette == 'nosmoke'"><span style="font-size: 12px;">비흡연</span></td>
          </tr>
          <tr>
            <th scope="row"><span style="font-size: 12px;">자기소개</span></th>
            <td colspan="3"><span style="font-size: 12px;">{{this.description}}</span></td>
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
  padding: 10px;
  font-size: 15px;
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
  width: 100%;
  border: none;
  border-radius: 10px;
  margin: 0;
  position: relative;
  padding: 0 10px;
  background-color: #5b7b8d;
}
</style>
