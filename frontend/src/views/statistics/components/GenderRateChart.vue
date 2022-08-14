<template>
  <Pie
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
</template>

<script>
import { Pie } from 'vue-chartjs/legacy'
import axios from 'axios'
import api from '@/api/api'

import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  ArcElement,
  CategoryScale
} from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, ArcElement, CategoryScale)

export default {
  name: 'GenderRateChart',
  components: {
    Pie
  },
  props: {
    chartId: {
      type: String,
      default: 'pie-chart'
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
        labels: ['남성', '여성'],
        datasets: [
          {
            backgroundColor: ['#8a2be2', '#ff00ff'],
            data: [65, 40]
          }
        ]
      },
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false
      }
    }
  },
  async mounted () {
    this.loaded = false
    try {
      const res = await axios.get(api.statistics.getGenderData())
      console.log('Gender Statistics Data Response', res)
      const lb = ['male', 'female']
      const datas = [res.data.malePercent, res.data.femalePercent]

      this.chartData = {
        labels: lb,
        datasets: [
          {
            backgroundColor: ['#8a2be2', '#ff00ff'],
            data: datas,
            borderRadius: 10
          }
        ]
      }
      this.loaded = true
    } catch (e) {
      console.error(e)
    }
  },

  methods: {
    settingData () {
      axios({
        url: api.statistics.getGenderData(),
        method: 'get'
      })
        .then(res => {
          console.log('Gender Statistics Data Response', res.data)
          this.chartData.labels = ['male', 'female']
          this.chartData.datasets.data = []
          this.chartData.datasets.data.push(res.data.malePercent)
          this.chartData.datasets.data.push(res.data.femalePercent)
          console.log(this.chartData.datasets.data)
        })
        .catch(err => console.log(err))
    }
  }

}
</script>
