package com.ssafy.chat.service;

import com.ssafy.chat.db.entity.Message;
import com.ssafy.chat.model.MessageVO;

import java.util.List;

public interface IMessageService {
    Long insertMessage(Message chatMessage);

    List<MessageVO> getAllMessagesByChatroomId(long id);

    Message saveMessage(Message message);
}
