package com.ssafy.chat.service;

import com.ssafy.chat.db.entity.Message;
import com.ssafy.chat.db.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService{

    @Autowired
    MessageRepository messageRepository;
    @Override
    public Long insertMessage(Message chatMessage) {
        return messageRepository.save(chatMessage).getId();
    }

    @Override
    public List<Message> getAllMessagesByChatroomId(long id) {
        return messageRepository.findAllByChatroomId(id);
    }

    public List<Message> getMessagesByChatroomId(long id, long idx) {
        return messageRepository.findAllByChatroomId(id);
    }
    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }


}
