<template>
  <div>
    <chat-list></chat-list>
  </div>
</template>

<script>
import ChatList from '@/views/chat/components/ChatList.vue'

export default {
  components: {
    ChatList: ChatList
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
