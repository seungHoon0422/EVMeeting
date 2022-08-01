package com.ssafy.chat.service;

import com.ssafy.chat.db.entity.Message;
import com.ssafy.chat.db.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return messageRepository.findAllByChatroomId(id).orElse(null);
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }


}
