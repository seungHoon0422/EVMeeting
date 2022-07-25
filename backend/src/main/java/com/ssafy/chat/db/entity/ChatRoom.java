package com.ssafy.chat.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id = null;

    String title;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    Long masterId;

}
