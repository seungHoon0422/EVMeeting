<!-- eslint-disable vue/no-mutating-props -->
<!-- eslint-disable vue/no-parsing-error -->
<template>
  <div>
    <bars
    :data="chartData.datasets.data"
    :gradient="['#001233', '#E5CCE5']"
    :labelData="chartData.labels"
    :barWidth="20"
    :rounding="3"
    :labelSize="0.5"
    :labelRotate="0"
    :padding="12"
    :height="300"
    :width="700"
    :growDuration="1">
    </bars>
  </div>
</template>

<script>
// npm i vuebars -S
import axios from 'axios'
import api from '@/api/api'

import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
} from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

export default {
  name: 'TimeBarChart',
  components: { },
  props: {
    data: {
      type: Array
    },
    width: {
      type: Number,
      default: 300
    },
    height: {
      type: Number,
      default: 300
    },
    plugins: {
      type: Array,
      default: () => []
    },

    //
    labelData: {
      type: Array,
      default: () => ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20']
    },
    labelRotate: {
      type: Number,
      default: -45
    },
    labelColor: {
      type: String,
      default: '#001233'
    },
    labelSize: {
      type: Number,
      default: 0.5
    },
    padding: {
      type: Number,
      default: 10
    },
    rounding: {
      type: Number,
      default: 3
    }
  },
  data () {
    return {
      chartData: {
        labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23'],
        datasets: [
          {
            label: 'Data One',
            backgroundColor: '#001233',
            data: [40, 20, 12, 39, 10, 40, 39, 80, 40, 20, 12, 11, 40, 20, 12, 39, 10, 40, 39, 80, 40, 20, 12]
          }
        ]
      },
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false
      }
    }
  },
  created () {
    this.loaded = false
    this.settingData()
    this.loaded = true
  },
  methods: {
    settingData () {
      console.log('Time bat chart, setting data')
      axios({
        url: api.statistics.getTimeData(),
        method: 'get'
      })
        .then(res => {
          console.log(res)
          this.chartData.labels = []
          this.chartData.datasets.data = []
          console.log('res', res)
          console.log('res.data', res.data)
          res.data.forEach(element => {
            this.chartData.labels.push(element.time)
            if (element.matchingPercent !== 0) this.chartData.datasets.data.push(element.matchingPercent)
            else this.chartData.datasets.data.push(2)
          })
        })
        .catch(err => console.log(err))
    }
  }
}
</script>
