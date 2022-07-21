package com.ssafy.socket.mapper;

import com.chat.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	int createUser(User newUser);
	int signin(User user);
}
