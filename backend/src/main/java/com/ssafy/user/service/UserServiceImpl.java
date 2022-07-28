package com.ssafy.user.service;

import com.ssafy.user.request.*;
import com.ssafy.user.request.UserEditImagePutReq;
import com.ssafy.user.request.UserEditInforPutReq;
import com.ssafy.user.request.UserEditPWPutReq;
import com.ssafy.user.request.UserRegisterPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.user.db.entity.User;
import com.ssafy.user.db.repository.UserRepository;
import com.ssafy.user.db.repository.UserRepositorySupport;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setUserid(userRegisterInfo.getUserid());
		user.setUsername(userRegisterInfo.getUsername());
		user.setHeight(userRegisterInfo.getHeight());
		user.setWeight(userRegisterInfo.getWeight());
		user.setGender(userRegisterInfo.getGender());
		user.setBirth(userRegisterInfo.getBirth());
		user.setHobby(userRegisterInfo.getHobby());
		user.setMbti(userRegisterInfo.getMbti());
		user.setSchool(userRegisterInfo.getSchool());
		user.setEmail(userRegisterInfo.getEmail());
		user.setDrink(userRegisterInfo.getDrink());
		user.setCigarette(userRegisterInfo.getCigarette());
		user.setDescription(userRegisterInfo.getDescription());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword1()));
		return userRepository.save(user);
	}

	@Override
	public User getUserByUserId(String userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepositorySupport.findUserByUserid(userId).get();
		return user;
	}

	@Override
	public void removeUser(String userId){
		User user = getUserByUserId(userId);
		userRepository.delete(user);

	}

	//사용자 프로필 정보 변경
	@Override
	public User editUserInfor(UserEditInforPutReq userEditInfo){
		User user = getUserByUserId(userEditInfo.getId());
		user.setUsername(userEditInfo.getUsername());
		return userRepository.save(user);
	}

	//사용자 비밀번호 변경
	@Override
	public User editUserPW(UserEditPWPutReq userEditPW){
		User user = getUserByUserId(userEditPW.getUserid());
		user.setPassword(passwordEncoder.encode(userEditPW.getNewpassword1()));
		return userRepository.save(user);
	}

	//사용자 프로필 사진 변경
	@Override
	public User editUserImage(UserEditImagePutReq userEditImage){
		/**
		 * TODO
		 * 프로필 사진을 DB에 저장하고 불러오는 과정 구현 예정
		 */
		return null;
	}
}
