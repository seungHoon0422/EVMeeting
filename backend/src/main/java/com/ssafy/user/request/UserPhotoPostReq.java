package com.ssafy.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserPhotoPostRequest")
public class UserPhotoPostReq {
	@ApiModelProperty(name="유저 ID", example="ssafy_web")
	String userid;
	@ApiModelProperty(name="유저 프로필 사진", example="photo")
	MultipartFile frm;

	MultipartFile imgUpload1;
}
