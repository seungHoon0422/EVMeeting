<template>
  <Pie
    :chart-options="chartOptions"
    :chart-data="{
        labels: ['흡연', '비흡연'],
        datasets: [
          {
            backgroundColor: ['#41B883', '#E46651'],
            data: cigaretteRate
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
import { mapGetters, mapActions } from 'vuex'
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
    this.getciga()
  },
  methods: {
    ...mapActions(['getciga'])
  },
  computed: {
    ...mapGetters(['cigaretteRate'])
  }
}
</script>
