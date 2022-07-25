package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class CallMeeting {
    // 화상미팅 대기열 등록 데이터
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id = null;
    String sessionid;
}
