package com.ssafy.socket.service;

import com.chat.model.ChatRoom;

import java.util.List;

public interface IChatRoomService {
	long createRoom(ChatRoom newRoom);
	List<ChatRoom> getAllChatRooms();
	String getRoomTitle(long id);
}
