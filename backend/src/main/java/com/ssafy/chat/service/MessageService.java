package com.ssafy.chat.service;

import com.ssafy.chat.db.entity.Message;
import com.ssafy.chat.db.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MessageService implements IMessageService{

    @Autowired
    MessageRepository messageRepository;
    @Override
    public int insertMessage(Message chatMessage) {
        return messageRepository.save(chatMessage).getId();
    }

    @Override
    public List<Message> getMessagesByChatroomId(long id, long idx) {
        return messageRepository.findAllByChatRoomId(id);
    }
}
