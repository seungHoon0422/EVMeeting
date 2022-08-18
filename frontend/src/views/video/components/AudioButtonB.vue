<template>
  <div>
    <button class="buttonB" @click="changeAudioB">B</button>
  </div>
</template>

<script>
export default {
  props: {
    session: {
      type: Object
    }
  },
  name: 'changeAudioB',
  setup (props, { emit }) {
    const changeAudioB = () => {
      // Sending Signal
      props.session.signal({
        data: '',
        to: [],
        type: 'changeAudioB'
      })
    }
    return {
      changeAudioB
    }
  }
}
</script>
<style scopeed>
.buttonA {
 /* padding: 17px 40px; */
 border-radius: 50px;
 border: 0;
 height: 40px;
 width: 140px;
 padding: auto;
 background-color: #5b7b8d;
 box-shadow: rgb(0 0 0 / 5%) 0 0 8px;
 letter-spacing: 1.5px;
 text-transform: uppercase;
 font-size: 15px;
 transition: all .5s ease;
}

.buttonA:hover {
 letter-spacing: 3px;
 background-color: hsl(261deg 80% 48%);
 color: hsl(0, 0%, 100%);
 box-shadow: rgb(93 24 220) 0px 7px 29px 0px;
}

.buttonA:active {
 letter-spacing: 3px;
 background-color: hsl(261deg 80% 48%);
 color: hsl(0, 0%, 100%);
 box-shadow: rgb(93 24 220) 0px 0px 0px 0px;
 transform: translateY(10px);
 transition: 100ms;
}

</style>
