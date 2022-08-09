package com.ssafy.meeting.db.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id = null;
    @Column(length = 255)
    String userFrom; // 사용자(본인)의 유저아이디
    @Column(length = 255)
    String userTo; // 상대방의 유저아이디
}
