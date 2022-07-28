package com.ssafy.meeting.db.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MeetingQueue {
    // 화상미팅 대기열 등록 데이터
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id = null;
    @Column(length = 255)
    String sessionid;
    @Column(length = 10)
    String gender; //
    // 사용자 정보 (성별, 나이, 등등을 여기에 추가해서 필터링할 수 있다)
}
