<template>
  <div>
    <button class="button1" @click="randomButton">대화 주제</button>
  </div>
</template>

<script>
export default {
  props: {
    session: {
      type: Object
    },
    tenseconds: {
      type: Number
    }
  },
  name: 'randomButton',
  setup (props, { emit }) {
    const randomButton = () => {
      // Sending Signal
      props.session.signal({
        data: '',
        to: [],
        type: 'randomButton'
      })
    }
    return {
      randomButton
    }
  },
  watch: {
    tenseconds () {
      if (this.tenseconds % 60 === 0) {
        this.randomButton()
      }
    }
  }
}
</script>
<style scopeed>
.button1 {
 /* padding: 17px 40px; */
 border-radius: 50px;
 border: 0;
 height: 40px;
 width: 140px;
 padding: auto;
 background-color: rgb(187, 115, 201);
 box-shadow: rgb(0 0 0 / 5%) 0 0 8px;
 letter-spacing: 1.5px;
 text-transform: uppercase;
 font-size: 15px;
 transition: all .5s ease;
}

.button1:hover {
 letter-spacing: 3px;
 background-color: hsl(261deg 80% 48%);
 color: hsl(0, 0%, 100%);
 box-shadow: rgb(93 24 220) 0px 7px 29px 0px;
}

.button1:active {
 letter-spacing: 3px;
 background-color: hsl(261deg 80% 48%);
 color: hsl(0, 0%, 100%);
 box-shadow: rgb(93 24 220) 0px 0px 0px 0px;
 transform: translateY(10px);
 transition: 100ms;
}

</style>
