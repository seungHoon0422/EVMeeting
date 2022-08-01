package com.ssafy.chat.service;


import com.ssafy.chat.db.entity.ChatRoom;
import com.ssafy.chat.db.entity.Message;

import java.util.List;

public interface IChatRoomService {
    long createRoom(ChatRoom newRoom);
    List<ChatRoom> getAllChatRooms();

    ChatRoom getChatRoomByChatRoomId(long id);

    List<ChatRoom> getAllChatRoomsByUserId1(long id);
    List<ChatRoom> getAllChatRoomsByUserId2(long id);
    List<ChatRoom> findChatRoomByUserid(long id);


    void UpdateRecentMessage(Message message);

    void deleteRoom(long chatroomId);

    //    String getRoomTitle(long id);
//    List<ChatRoom> getAllChatRoomsByUserId(long id);

}
