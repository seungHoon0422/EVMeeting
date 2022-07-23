package com.ssafy.chat.service;

import com.ssafy.chat.db.entity.ChatRoom;

import java.util.List;

public interface IChatRoomService {
	long createRoom(ChatRoom newRoom);
	List<ChatRoom> getAllChatRooms();
	String getRoomTitle(long id);
}
