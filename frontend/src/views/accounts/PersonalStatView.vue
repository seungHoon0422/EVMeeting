<template>
  <div class="container overflow-hidden">
    <h1 class="header-font">나의 엘리베이터 정보</h1>
    <div class="row gx-5 gy-5">
      <div class="col-6">
        <div class="p-3 border bg-light">
          <h4>매칭율</h4>
          {{matchingRate}} %
        </div>
      </div>
      <div class="col-6">
        <div class="p-3 border bg-light">
          <h4> 매칭 평균 시간</h4>
          {{matchingTime}} 초
        </div>
      </div>
      <div class="col-6">
        <div class="p-3 border bg-light">
          <h3>매칭된 상대 주량</h3>
          <pie-chart-drink></pie-chart-drink>
        </div>
      </div>
      <div class="col-6">
        <div class="p-3 border bg-light">
          <h3>매칭된 상대방 흡연여부</h3>
          <pie-chart-smoke></pie-chart-smoke>
        </div>
      </div>
      <div class="col-12">
        <div class="p-3 border bg-light">
          <h3>매칭된 상대방 MBTI</h3>
          <bar-chart></bar-chart>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PieChartDrink from './components/PieChartDrink.vue'
import PieChartSmoke from './components/PieChartSmoke.vue'
import BarChart from './components/BarChart.vue'
import axios from 'axios'
import api from '@/api/api'

export default {
  components: { PieChartDrink, PieChartSmoke, BarChart },
  data () {
    return {
      matchingRate: '',
      matchingTime: ''
    }
  },
  methods: {
    MatchingRate () {
      axios({
        url: api.accounts.currentUserInfo(),
        method: 'get',
        headers: this.$store.getters.authHeader
      })
        .then(res => {
          axios({
            url: api.statistics.getMatchingRate(res.data.id),
            method: 'get'
          })
            .then(res => {
              this.matchingRate = res.data.matchingPercent
            })
            .catch(err => console.log(err))
        })
        .catch(err => console.log(err))
    },
    MatchingTime () {
      axios({
        url: api.accounts.currentUserInfo(),
        method: 'get',
        headers: this.$store.getters.authHeader
      })
        .then(res => {
          axios({
            url: api.statistics.getMatchingTime(res.data.id),
            method: 'get'
          })
            .then(res => {
              this.matchingTime = res.data.matchingTimeAverage
            })
            .catch(err => console.log(err))
        })
        .catch(err => console.log(err))
    }
  },
  created () {
    this.MatchingRate()
    this.MatchingTime()
  }
}
</script>

<style scoped>
.container {
  border: 2px solid #FAFAFA;
  padding: 30px 30px;
  width: 1000px !important;
  /* height: 550px !important; */
  /* background: rgba(0,0,0,0.4); */
  background: rgba(178, 174, 174, 0.4);

}

.header-font {
  margin-bottom: 20px;
  /* color: #FAFAFA; */
  color: #ffffff;
}
</style>
