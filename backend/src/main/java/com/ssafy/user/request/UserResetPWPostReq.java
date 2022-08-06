package com.ssafy.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원정보 수정 API ([PUT] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserResetPWPostRequest")
public class UserResetPWPostReq {
	@ApiModelProperty(name="유저 ID", example="ssafy_web")
	String userid;
	@ApiModelProperty(name="유저 이메일", example="ssafy@naver.com")
	String email;
}
