package com.ssafy.chat.db.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    Long userid1;

    Long userid2;


    @ColumnDefault("true")
    Boolean alive;

    Long recentMessageId;

    String recentMessage;

    String recentMessageTime;
}
