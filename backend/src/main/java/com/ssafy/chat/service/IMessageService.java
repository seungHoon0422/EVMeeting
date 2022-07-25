package com.ssafy.chat.service;

import com.ssafy.chat.db.entity.Message;

import java.util.List;

public interface IMessageService {
	Long insertMessage(Message chatMessage);
	List<Message> getMessagesByChatroomId(long id, long idx);
}
