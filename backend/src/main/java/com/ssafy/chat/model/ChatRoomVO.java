package com.ssafy.chat.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id = null;

    Long userid1;

    Long userid2;


    @ColumnDefault("true")
    Boolean alive;

    Long recentMessageId;

    String recentMessage;

    String recentMessageTime;

    String sendUserId;
}
