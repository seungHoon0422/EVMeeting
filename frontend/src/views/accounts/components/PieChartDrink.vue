<template>
  <Pie
    :chart-options="chartOptions"
    :chart-data="{
        labels: drinkLabel,
        datasets: [
          {
            backgroundColor: ['#8B008B', '#9932CC', '#BA55D3', '#FF00FF'],
            data: drinkRate
          }
        ]
      }"
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
import { mapActions, mapGetters } from 'vuex'
import { Pie } from 'vue-chartjs/legacy'

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
  name: 'PieChart',
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
      default: 150
    },
    height: {
      type: Number,
      default: 150
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
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false
      }
    }
  },
  created () {
    this.getdrink()
  },
  methods: {
    ...mapActions(['getdrink'])
  },
  computed: {
    ...mapGetters(['drinkRate', 'drinkLabel'])
  }
}
</script>
