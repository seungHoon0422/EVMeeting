package com.ssafy.chat.service;

import com.ssafy.chat.db.entity.ChatRoom;
import com.ssafy.chat.db.repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService implements IChatRoomService {


    @Autowired
    ChatroomRepository chatroomRepository;
    @Override
    public long createRoom(ChatRoom newRoom) {
        return chatroomRepository.save(newRoom).getId();
    }

    @Override
    public List<ChatRoom> getAllChatRooms() {
        return chatroomRepository.findAll();
    }

    @Override
    public String getRoomTitle(long id) {
        String value = chatroomRepository.findTitleById(id).getTitle();
        System.out.println("value = " + value);
        return value;
    }
}
