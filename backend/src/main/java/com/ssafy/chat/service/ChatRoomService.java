package com.ssafy.chat.service;

import com.ssafy.chat.db.entity.ChatRoom;
import com.ssafy.chat.db.entity.Message;
import com.ssafy.chat.db.repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ChatRoomService implements IChatRoomService {


    @Autowired
    ChatroomRepository chatroomRepository;
    @Override
    public long createRoom(ChatRoom newRoom) {
        newRoom.setRecentMessageTime(LocalDateTime.now().toString());
        return chatroomRepository.save(newRoom).getId();
    }
    @Override
    public List<ChatRoom> getAllChatRooms() {
        return chatroomRepository.findAll();
    }
    @Override
    public ChatRoom getChatRoomByChatRoomId(long id) {
        return chatroomRepository.findById(id).orElse(null);
    }

    @Override
    public List<ChatRoom> findChatRoomByUserid(long id) {

        List<ChatRoom> chatRooms = chatroomRepository.findChatRoomByUserid(id);
        Collections.sort(chatRooms, new Comparator<ChatRoom>() {
            @Override
            public int compare(ChatRoom o1, ChatRoom o2) {
                if(o1.getRecentMessageTime()!=null && o2.getRecentMessageTime()!=null) {
                    return o2.getRecentMessageTime().compareTo(o1.getRecentMessageTime());
                } else {
                    return (int) (o1.getId() - o2.getId());
                }
            }
        });
        return chatRooms;
    }


    // 채팅방의 최신 메세지를 리턴
    @Override
    public void UpdateRecentMessage(Message message) {
        chatroomRepository.findById(message.getChatroomId())
                .map(chatroom -> {
                    chatroom.setRecentMessageId(message.getId());
                    chatroom.setRecentMessage(message.getContent());
                    chatroom.setRecentMessageTime(LocalDateTime.now().toString());
                    return chatroomRepository.save(chatroom);
                });
    }


    // 채팅방 삭제
    // 채팅방 table의 alive flag를 false로 변경
    @Override
    public void deleteRoom(long chatroomId) {
        chatroomRepository.findById(chatroomId)
                .map(chatroom -> {
                    chatroom.setAlive(false);
                    return chatroomRepository.save(chatroom);
                });
    }
}
