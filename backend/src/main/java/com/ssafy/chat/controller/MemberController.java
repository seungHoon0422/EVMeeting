package com.ssafy.chat.controller;

import com.ssafy.chat.db.entity.Member;
import com.ssafy.chat.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@RestController
public class MemberController {

	@Autowired
	IUserService userService;

	// 유저 생성
	@PostMapping("/user")
	public ResponseEntity<Member> createUser(@RequestBody Member newUser) {
		long resultOfCreation = userService.createUser(newUser);
		if (resultOfCreation >= 0)
			return ResponseEntity.status(HttpStatus.OK).body(newUser);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	// 정보 확인
	@PostMapping("/login")
	public ResponseEntity<String> signinUser(@RequestBody Member user) {
		boolean resultOfSignin = userService.signin(user);
		if (resultOfSignin)
			return ResponseEntity.status(HttpStatus.OK).body(user.getNickname());
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

}