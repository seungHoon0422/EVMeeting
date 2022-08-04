package com.ssafy.user.service;

import com.ssafy.user.db.entity.User;
import com.ssafy.user.request.UserEditInforPutReq;
import com.ssafy.user.request.UserEditPWPutReq;
import com.ssafy.user.request.UserPhotoPostReq;
import com.ssafy.user.request.UserRegisterPostReq;
import org.springframework.web.multipart.MultipartFile;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo);
	User getUserByUserId(String userId);
	void removeUser(String userId);
	User editUserInfor(UserEditInforPutReq userEditInfo);
	User editUserPW(UserEditPWPutReq userEditPW);
	User editUserPhoto(MultipartFile file, String userid);
}
