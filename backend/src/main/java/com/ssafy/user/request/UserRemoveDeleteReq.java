package com.ssafy.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원탈퇴 API ([DELETE] /api/v1/users/remove) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRemoveDeleteRequest")
public class UserRemoveDeleteReq {
	@ApiModelProperty(name="유저 ID", example="ssafy_web")
	String userid;
	@ApiModelProperty(name="유저 Password1", example="your_password")
	String password1;
	@ApiModelProperty(name="유저 Password2", example="your_password")
	String password2;
}
