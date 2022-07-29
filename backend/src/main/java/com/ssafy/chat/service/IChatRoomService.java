package com.ssafy.chat.service;


import com.ssafy.chat.db.entity.ChatRoom;

import java.util.List;

public interface IChatRoomService {
    long createRoom(ChatRoom newRoom);
    List<ChatRoom> getAllChatRooms();

    ChatRoom getChatRoomByChatRoomId(long id);

    List<ChatRoom> getAllChatRoomsByUserId1(long id);
    List<ChatRoom> getAllChatRoomsByUserId2(long id);

    //    String getRoomTitle(long id);
//    List<ChatRoom> getAllChatRoomsByUserId(long id);

}
