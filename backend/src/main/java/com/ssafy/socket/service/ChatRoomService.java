package com.ssafy.socket.service;

import com.chat.mapper.ChatRoomMapper;
import com.chat.model.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService implements IChatRoomService {
	private final ChatRoomMapper chatroomMapper;

	@Override
	public long createRoom(ChatRoom newRoom) {
		int result = chatroomMapper.createRoom(newRoom);
		return result == 1 ? newRoom.getId() : -1;
	}

	@Override
	public List<ChatRoom> getAllChatRooms() {
		return chatroomMapper.getAllChatRooms();
	}

	@Override
	public String getRoomTitle(long id) {
		return chatroomMapper.getRoomTitle(id);
	}

}
