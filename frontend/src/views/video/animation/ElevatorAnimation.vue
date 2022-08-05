<template>
  <div>
    <button @click="elevator">호출</button>
    <svg width="64" height="64" viewBox="0 0 64 64" fill="none" xmlns="http://www.w3.org/2000/svg">
      <g id="elevator">
      <g id="inside-elevator">
      <path id="inside-elevator_2" d="M18 20.5H46C46.8284 20.5 47.5 21.1716 47.5 22V58.5H16.5V22C16.5 21.1716 17.1716 20.5 18 20.5Z" fill="#7D929B" stroke="#3E3546"/>
      <path id="Vector 9" d="M20.5 52.5L16.5 58.5H47.5L43.5 52.5H20.5Z" fill="#625565"/>
      <rect id="Rectangle 39" x="23.5" y="23.5" width="17" height="17" rx="1.5" fill="#8FD3FF" stroke="#3E3546"/>
      <path id="Vector 8" d="M29 35L35 29M32 36L36 32M28 32L32 28" stroke="#F4EDF5" stroke-linecap="round"/>
      <path id="Vector 7" d="M16.5 49.5L20.5 43.5H43.5L47.5 49.5" stroke="#3E3546"/>
      <path id="Vector 6" d="M20.5 52.5L16.5 58.5H47.5L43.5 52.5M20.5 52.5H43.5M20.5 52.5V20.5M43.5 52.5V20.5" stroke="#3E3546"/>
      </g>
      <path id="left-door" d="M19 20.5H31.5V58.5H16.5V23C16.5 21.6193 17.6193 20.5 19 20.5Z" fill="#9BABB2" stroke="#3E3546"/>
      <path id="right-door" d="M32.5 20.5H45C46.3807 20.5 47.5 21.6193 47.5 23V58.5H32.5V20.5Z" fill="#9BABB2" stroke="#3E3546"/>
      <path id="elevator-bg" fill-rule="evenodd" clip-rule="evenodd" d="M64 0H0V64H64V0ZM16 11C13.7909 11 12 12.7909 12 15V59H52V15C52 12.7909 50.2091 11 48 11H16Z" fill="#F2F7F4"/>
      <g id="elevator-front">
      <mask id="path-10-inside-1_102_4" fill="white">
      <path fill-rule="evenodd" clip-rule="evenodd" d="M16 11C13.7909 11 12 12.7909 12 15V59H17V23C17 21.8954 17.8954 21 19 21H45C46.1046 21 47 21.8954 47 23V59H52V15C52 12.7909 50.2091 11 48 11H16Z"/>
      </mask>
      <path fill-rule="evenodd" clip-rule="evenodd" d="M16 11C13.7909 11 12 12.7909 12 15V59H17V23C17 21.8954 17.8954 21 19 21H45C46.1046 21 47 21.8954 47 23V59H52V15C52 12.7909 50.2091 11 48 11H16Z" fill="#9BABB2"/>
      <path d="M12 59H11V60H12V59ZM17 59V60H18V59H17ZM47 59H46V60H47V59ZM52 59V60H53V59H52ZM13 15C13 13.3431 14.3431 12 16 12V10C13.2386 10 11 12.2386 11 15H13ZM13 59V15H11V59H13ZM17 58H12V60H17V58ZM18 59V23H16V59H18ZM18 23C18 22.4477 18.4477 22 19 22V20C17.3431 20 16 21.3431 16 23H18ZM19 22H45V20H19V22ZM45 22C45.5523 22 46 22.4477 46 23H48C48 21.3431 46.6569 20 45 20V22ZM46 23V59H48V23H46ZM52 58H47V60H52V58ZM51 15V59H53V15H51ZM48 12C49.6569 12 51 13.3431 51 15H53C53 12.2386 50.7614 10 48 10V12ZM16 12H48V10H16V12Z" fill="#3E3546" mask="url(#path-10-inside-1_102_4)"/>
      </g>
      <line id="floor" x1="4.5" y1="58.5" x2="59.5" y2="58.5" stroke="#3E3546" stroke-linecap="round"/>
      <circle id="floor-1" cx="18" cy="16" r="1.5" fill="#F04F78" stroke="#3E3546"/>
      <circle id="floor-2" cx="25" cy="16" r="1.5" fill="#F04F78" stroke="#3E3546"/>
      <circle id="floor-3" cx="32" cy="16" r="1.5" fill="#F04F78" stroke="#3E3546"/>
      <circle id="floor-4" cx="39" cy="16" r="1.5" fill="#F04F78" stroke="#3E3546"/>
      <circle id="floor-5" cx="46" cy="16" r="1.5" fill="#0EAF9B" stroke="#3E3546"/>
      <g id="control-up">
      <rect id="base" x="54.5" y="40.5" width="5" height="6" rx="1.5" fill="#9BABB2" stroke="#3E3546"/>
      <path id="arrow-up" d="M56 44.5L57 42L58 44.5H56Z" fill="#3E3546"/>
      </g>
      <g id="plant">
      <g id="leaf">
      <g id="Rectangle 40">
      <mask id="path-20-inside-2_102_4" fill="white">
      <path d="M3.69056 34.4498C3.26247 31.025 5.93289 28 9.38434 28V28C12.8358 28 15.5062 31.025 15.0781 34.4498L12.8843 52H5.88434L3.69056 34.4498Z"/>
      </mask>
      <path d="M3.69056 34.4498C3.26247 31.025 5.93289 28 9.38434 28V28C12.8358 28 15.5062 31.025 15.0781 34.4498L12.8843 52H5.88434L3.69056 34.4498Z" fill="#0EAF9B" stroke="#3E3546" stroke-width="2" mask="url(#path-20-inside-2_102_4)"/>
      </g>
      <path id="Line 11" d="M9.38434 51.5V35M9.38434 32L9.38434 35M9.38434 35L11.8843 33M9.38434 35L6.88434 33M11.8843 36L9.38434 38L6.88434 36M11.8843 39L9.38434 41L6.88434 39M11.8843 42L9.38434 44L6.88434 42M11.3843 45.5L9.38434 47L7.38434 45.5M10.8843 49L9.38434 50L7.88434 49" stroke="#3E3546" stroke-linecap="round"/>
      </g>
      <g id="potter">
      <g id="Rectangle 38">
      <mask id="path-22-inside-3_102_4" fill="white">
      <path d="M4.19497 52.2425C4.03719 51.6114 4.51454 51 5.16511 51H13.6036C14.2541 51 14.7315 51.6114 14.5737 52.2425L12.8843 59H5.88434L4.19497 52.2425Z"/>
      </mask>
      <path d="M4.19497 52.2425C4.03719 51.6114 4.51454 51 5.16511 51H13.6036C14.2541 51 14.7315 51.6114 14.5737 52.2425L12.8843 59H5.88434L4.19497 52.2425Z" fill="#F68181" stroke="#3E3546" stroke-width="2" mask="url(#path-22-inside-3_102_4)"/>
      </g>
      <path id="Vector 5" d="M5.38434 53.5H13.3843" stroke="#3E3546"/>
      </g>
      </g>
      </g>
    </svg>

  </div>
</template>

<script>

export default {
  data () {
    return {
      leftDoor: document.getElementById('left-door'),
      rightDoor: document.getElementById('right-door'),
      floor1: document.getElementById('floor-1'),
      floor2: document.getElementById('floor-2'),
      floor3: document.getElementById('floor-3'),
      floor4: document.getElementById('floor-4'),
      floor5: document.getElementById('floor-5'),
      controlUp: document.getElementById('control-up'),
      arrowUp: document.getElementById('arrow-up')
    }
  },
  methods: {
    elevator () {
      console.log(this.leftDoor)
      console.log(this.rightDoor)
      console.log(this.floor1)
      console.log(this.floor2)
      console.log(this.floor3)
      console.log(this.floor4)
      console.log(this.floor5)
      console.log(this.controlUp)
      console.log(this.arrowUp)
      this.leftDoor.classList.add('left-door')
      this.rightDoor.classList.add('right-door')
      this.floor1.classList.add('floor-1')
      this.floor2.classList.add('floor-2')
      this.floor3.classList.add('floor-3')
      this.floor4.classList.add('floor-4')
      this.floor5.classList.add('floor-5')
      this.arrowUp.classList.add('arrow-up')
    }
  }
}
</script>

<style>
/* RESET */
*::before,
*::after {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

/* VARIABLES */
:root {
  --light: #F2F7F4;
  --red: #F04F78;
  --green: #0EAF9B;
  --on: #30E1B9;
}

/* GENERAL */
body {
    width: 100%;
    height: 100vh;

    display: flex;
    justify-content: center;
    align-items: center;

    background-color: var(--light);
}

svg {
    width: 256px;
    height: 256px;
}

/* ANIMATIONS */
.left-door {
  animation: left-door 1s ease 10s forwards;
}

@keyframes left-door {
    0% { transform: translateX(0); }
    100% { transform: translateX(-14px); }
}

.right-door {
  animation: right-door 1s ease 10s forwards;
}

@keyframes right-door {
    0% { transform: translateX(0); }
    100% { transform: translateX(14px); }
}

.floor-5 {
  animation: light-to-red 2s ease 0s forwards;
}

.floor-4 {
  animation: light-to-red 2s ease 2s forwards;
}

.floor-3 {
  animation: light-to-red 2s ease 4s forwards;
}

.floor-2 {
  animation: light-to-red 2s ease 6s forwards;
}

.floor-1 {
  animation: light-to-green 2s ease 8s forwards;
}

@keyframes light-to-red {
  0% { fill: var(--green); }
  80% { fill: var(--green); }
  100% { fill: var(--red); }
}

@keyframes light-to-green {
  0% { fill: var(--green); }
  100% { fill: var(--green); }
}

#control-up {
  cursor: pointer;
}

.arrow-up {
  fill: var(--on);
}

</style>
