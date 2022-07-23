package com.ssafy.chat.service;

import com.ssafy.chat.db.entity.Member;

public interface IUserService {

	long createUser(Member newUser);
	boolean signin(Member user);

}
