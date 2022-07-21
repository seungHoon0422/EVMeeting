package com.ssafy.socket.mapper;

import com.chat.model.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {

	int insertMessage(Message chatMessage);
	List<Message> getMessagesByChatroomId(@Param("roomId") long roomId, @Param("idx") long idx);

}
