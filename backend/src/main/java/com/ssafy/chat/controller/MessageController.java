package com.ssafy.chat.controller;

import com.ssafy.chat.db.entity.Message;
import com.ssafy.chat.service.IMessageService;
import com.ssafy.chat.service.MessageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController

public class MessageController {
    private final IMessageService messageService;
    private final SimpMessagingTemplate template;

    @MessageMapping("/message")
    public void sendMessage(@Payload Message chatMessage) {
        //	log.info("전달 메세지 : " + chatMessage);

        long id = messageService.insertMessage(chatMessage);

        template.convertAndSend("/sub/" + chatMessage.getChatroomId(), chatMessage);
    }






}
