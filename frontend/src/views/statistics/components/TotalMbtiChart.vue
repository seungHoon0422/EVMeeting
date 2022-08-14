<!-- eslint-disable vue/no-parsing-error -->
<template>
  <div>
      <Bar
        :chart-options="chartOptions"
        :chart-data="chartData"
        :chart-id="chartId"
        :dataset-id-key="datasetIdKey"
        :plugins="plugins"
        :css-classes="cssClasses"
        :styles="styles"
        :width="width"
        :height="height"
      />
  </div>
</template>

<script>
import { Bar } from 'vue-chartjs/legacy'
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
  name: 'TotalMbtiChart',
  components: {
    Bar
  },
  props: {
    chartId: {
      type: String,
      default: 'bar-chart'
    },
    datasetIdKey: {
      type: String,
      default: 'label'
    },
    width: {
      type: Number,
      default: 400
    },
    height: {
      type: Number,
      default: 400
    },
    cssClasses: {
      default: '',
      type: String
    },
    styles: {
      type: Object,
      default: () => {}
    },
    plugins: {
      type: Array,
      default: () => []
    }
  },
  data () {
    return {
      chartData: {
        labels: [
          'ENFP',
          'ENTJ',
          'INTJ',
          'ISFP',
          'ESFP'
        ],
        datasets: [
          {
            label: 'MBTI Rate',
            backgroundColor: '#6f42c1',
            data: [40, 35, 20, 14, 13],
            borderRadius: 5

          }
        ]
      },
      chartOptions: {
        indexAxis: 'y',
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
      console.log('Time bat chart, setting datas')
      axios({
        url: api.statistics.getMbtiData(),
        method: 'get'
      })
        .then(res => {
          console.log(res)
          this.chartData.labels = []
          this.chartData.datasets.data = []

          for (let index = 0; index < 5; index++) {
            const element = res.data[index]
            this.chartData.labels.push(element.type)
            if (element.percent !== 0) this.chartData.datasets.data.push(element.percent)
            else this.chartData.datasets.data.push(10)
            console.log(element)
          }
        })
        .catch(err => console.log(err))
    }
  }
}
</script>
