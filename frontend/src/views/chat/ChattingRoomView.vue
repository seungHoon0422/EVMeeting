
<script>
import { mapGetters, mapActions } from 'vuex'
export default {
  name: 'chatRooms',
  data: () => {
    return {
      id: -1,
      name: '',
      room_list: [1, 2, 3],
      isShowing: false
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn', 'currentUser'])
  },
  created () {
    this.fetchCurrentUser()
    this.id = this.currentUser.id
    this.name = this.currentUser.username
    this.userid = this.currentUser.userid
    this.callMyRoomList()
  },
  methods: {
    ...mapActions(['fetchCurrentUser']),
    callMyRoomList () {
      this.$router.push({
        name: 'chatlist',
        query: { id: this.currentUser.id, name: this.currentUser.username, userId: this.currentUser.userid }
      })
    }
  }
}
</script>
<style scoped>
.chat_list{
  width: 505px;
  height: 712px;
  background-color: #bad8da;
  margin: 5rem auto 0rem;
  border-radius: 1.5rem;
  box-shadow: 0px 1px 20px #9c9cc855;
}
.title {
  margin-top: 20px;
  margin-left: 10px;
  font-size: large;
  font-family: "Golden Plains - Demo";
  color: #FFFFEA;
}
h3 {
  margin-left: 15px;
  margin-bottom: 10px;
}
.roomList {
  margin-left: 15px;
}
/* .search_room {
  margin-left: 15px;
  margin-bottom: 30px;
  height: 20px;
} */
.rooms {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 2px #fefefe solid;
  height: 70px;
  max-width: 90%;
  border-radius: 10px;
  font-size: 20px;
  background: #ffffea;
  margin-bottom: 5px;
  text-align: center;
}
</style>
