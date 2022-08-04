package com.ssafy.chat.model;


import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageVO {

    Long id = null;
    String content;
    Long senderId;
    String userId;
    Long chatroomId;

}
