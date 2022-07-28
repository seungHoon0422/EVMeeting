<template>
  <div>
    <h1>It's question List</h1>
    <h1>{{currentUser.gender}}</h1>
    <button @click="showProfile">profile</button>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default ({
  data () {
    return {
      pickProfile: '',
      pickList: [
        'mbti',
        'height',
        'bobby',
        'drink',
        'cigarette'
      ]
    }
  },
  computed: {
    ...mapGetters(['currentUser'])
  },
  methods: {
    showProfile () {
      this.pickprofile = this.pickList[0]
      console.log(this.pickprofile)
      console.log(this.currentUser[this.pickProfile])
    }
  }
})
</script>
