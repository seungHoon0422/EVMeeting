package com.ssafy.socket.service;

import com.chat.mapper.MessageMapper;
import com.chat.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class MessageService implements IMessageService {
	private final MessageMapper messageMapper;

	@Override
	public int insertMessage(Message chatMessage) {
		return messageMapper.insertMessage(chatMessage);
	}

	@Override
	public List<Message> getMessagesByChatroomId(long id, long idx) {
		return messageMapper.getMessagesByChatroomId(id, idx);
	}

}
