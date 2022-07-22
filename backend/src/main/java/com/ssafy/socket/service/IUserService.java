package com.ssafy.socket.service;

import com.chat.model.User;

public interface IUserService {

	long createUser(User newUser);
	boolean signin(User user);

}
