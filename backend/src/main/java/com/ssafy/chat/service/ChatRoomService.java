package com.ssafy.chat.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.chat.db.entity.ChatRoom;
import com.ssafy.chat.db.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService implements IChatRoomService{


    @Autowired
    ChatRoomRepository chatRoomRepository;
    @Override
    public long createRoom(ChatRoom newRoom) {
        return chatRoomRepository.save(newRoom).getId();
    }

    @Override
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    @Override
    public String getRoomTitle(long id) {
        return chatRoomRepository.findTitleById(id);
    }
}
