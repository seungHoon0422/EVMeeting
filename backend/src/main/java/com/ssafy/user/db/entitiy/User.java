package com.ssafy.user.db.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
public class User extends BaseEntity{
    String userid;
    String username;
    String height;
    String weight;
    String gender;
    String birth;
    String hobby;
    String mbti;
    String school;
    String email;
    String drink;
    String cigarette;
    String description;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
}
