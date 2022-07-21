package com.ssafy.socket.service;

import com.chat.model.Message;

import java.util.List;

public interface IMessageService {
	int insertMessage(Message chatMessage);
	List<Message> getMessagesByChatroomId(long id, long idx);
}
