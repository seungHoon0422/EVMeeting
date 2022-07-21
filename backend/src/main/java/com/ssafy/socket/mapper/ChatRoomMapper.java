package com.ssafy.socket.mapper;

import com.chat.model.ChatRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomMapper {

	int createRoom(ChatRoom newRoom);
	List<ChatRoom> getAllChatRooms();
	String getRoomTitle(long id);

}
