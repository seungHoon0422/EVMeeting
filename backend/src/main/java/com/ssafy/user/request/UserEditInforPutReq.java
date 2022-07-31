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
@ApiModel("UserEditInforPutRequest")
public class UserEditInforPutReq {
	@ApiModelProperty(name="유저 ID", example="ssafy_web")
	String userid;
	@ApiModelProperty(name="유저 닉네임", example="김싸피")
	String username;
	@ApiModelProperty(name="유저 키", example="180")
	String height;
	@ApiModelProperty(name="유저 몸무게", example="80")
	String weight;
	@ApiModelProperty(name="유저 성별", example="M")
	String gender;
	@ApiModelProperty(name="유저 생년월일", example="2022-01-07")
	String birth;
	@ApiModelProperty(name="유저 취미", example="산책")
	String hobby;
	@ApiModelProperty(name="유저 MBTI", example="ISTJ")
	String mbti;
	@ApiModelProperty(name="유저 학교", example="싸피대학교")
	String school;
	@ApiModelProperty(name="유저 이메일", example="kim@ssafy.com")
	String email;
	@ApiModelProperty(name="유저 주량", example="1병")
	String drink;
	@ApiModelProperty(name="유저 흡연여부", example="N")
	String cigarette;
	@ApiModelProperty(name="유저 자기소개", example="편하게 연락주세요")
	String description;
}
