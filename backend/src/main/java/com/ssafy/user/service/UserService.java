package com.ssafy.user.service;

import com.ssafy.user.request.*;
import com.ssafy.user.db.entitiy.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo);
	User getUserByUserId(String userId);
	void removeUser(String userId);
	User editUserInfor(UserEditInforPutReq userEditInfo);
	User editUserPW(UserEditPWPutReq userEditPW);
	User editUserImage(UserEditImagePutReq userEditImage);
}
