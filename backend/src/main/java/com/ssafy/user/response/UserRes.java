package com.ssafy.user.response;

import com.ssafy.user.db.entitiy.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public class UserRes {
    @ApiModelProperty(name = "User ID")
    String userid;
    @ApiModelProperty(name = "유저 닉네임")
    String username;
    @ApiModelProperty(name = "유저 키")
    String height;
    @ApiModelProperty(name = "유저 몸무게")
    String weight;
    @ApiModelProperty(name = "유저 성별")
    String gender;
    @ApiModelProperty(name = "유저 생년월일")
    String birth;
    @ApiModelProperty(name = "유저 취미")
    String hobby;
    @ApiModelProperty(name = "유저 MBTI")
    String mbti;
    @ApiModelProperty(name = "유저 학교")
    String school;
    @ApiModelProperty(name = "유저 이메일")
    String email;
    @ApiModelProperty(name = "유저 주량")
    String drink;
    @ApiModelProperty(name = "유저 흡연여부")
    String cigarette;
    @ApiModelProperty(name = "유저 자기소개")
    String description;

    public static UserRes of(User user) {
        UserRes res = new UserRes();
        res.setUserid(user.getUserid());
        res.setUsername(user.getUsername());
        res.setHeight(user.getHeight());
        res.setWeight(user.getWeight());
        res.setGender(user.getGender());
        res.setBirth(user.getBirth());
        res.setHobby(user.getHobby());
        res.setMbti(user.getMbti());
        res.setSchool(user.getSchool());
        res.setEmail(user.getEmail());
        res.setDrink(user.getDrink());
        res.setCigarette(user.getCigarette());
        res.setDescription(user.getDescription());
        return res;
    }
}