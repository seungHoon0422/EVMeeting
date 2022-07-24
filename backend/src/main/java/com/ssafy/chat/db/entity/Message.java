package com.ssafy.chat.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String content;

    @Column(name="sender_id")
    int senderId;

    @Column(name="chatroom_id")
    int chatroomId;
}
