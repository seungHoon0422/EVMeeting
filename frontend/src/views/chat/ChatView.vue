<template>
  <div>
    <chat></chat>
  </div>
</template>

<script>
import Chat from '@/views/chat/components/Chat.vue'

export default {
  components: {
    Chat: Chat
  }
}
</script>

<style>
@import url("https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css");
* {
  font-family: Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto,
    "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR",
    "Malgun Gothic", sans-serif;
}
</style>
