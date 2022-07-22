package com.ssafy.chat.db.entity;

import lombok.*;

import javax.persistence.*;

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

    @OneToMany(mappedBy = "user")
    @Column(name="sender_id")
    int senderId;

    @OneToMany(mappedBy = "chatroom")
    @Column(name="chatroom_id")
    int chatroomId;
}
