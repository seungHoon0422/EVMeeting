package com.ssafy.chat.service;


import com.ssafy.chat.db.entity.ChatRoom;
import com.ssafy.chat.db.entity.Message;
import com.ssafy.chat.model.ChatRoomVO;

import java.util.List;

public interface IChatRoomService {
    long createRoom(ChatRoom newRoom);
    List<ChatRoom> getAllChatRooms();

    ChatRoom getChatRoomByChatRoomId(long id);

    List<ChatRoomVO> findChatRoomByUserid(long id);


    void UpdateRecentMessage(Message message);

    void deleteRoom(long chatroomId);

    void removeRoom(long chatroomId);

    long findChatRoomByUsers(long user1, long user2);

    //    String getRoomTitle(long id);
//    List<ChatRoom> getAllChatRoomsByUserId(long id);

}
