package com.ssafy.chat.service;

import com.ssafy.chat.db.entity.Message;

import java.util.List;

public interface IMessageService {
    Long insertMessage(Message chatMessage);

    List<Message> getAllMessagesByChatroomId(long id);

    Message saveMessage(Message message);
}
