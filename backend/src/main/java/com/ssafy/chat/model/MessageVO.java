package com.ssafy.chat.model;


import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class MessageVO {

    Long id = null;
    String content;
    Long senderId;
    String userId;
    Long chatroomId;

}
