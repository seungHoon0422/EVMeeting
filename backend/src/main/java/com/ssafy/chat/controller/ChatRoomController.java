package com.ssafy.chat.controller;

import com.ssafy.chat.db.entity.ChatRoom;
import com.ssafy.chat.db.entity.Message;
import com.ssafy.chat.service.IChatRoomService;
import com.ssafy.chat.service.IMessageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@RestController
public class ChatRoomController {

    @Autowired
    private final IChatRoomService chatroomService;
    @Autowired
    private final IMessageService messageService;
    final int PAGE = 10;


    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ApiOperation(value = "모든 채팅방 목록", notes = "생성된 모든 채팅방의 목록을 가져옵니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "채팅 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<ChatRoom>> room() {
        List<ChatRoom> rooms = chatroomService.getAllChatRooms();
        if (rooms == null || rooms.size() == 0)
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(rooms);

    }

    // 방 생성
    @PostMapping("/room")
    @ApiOperation(value = "채팅방 생성", notes = "user 정보와 함께 채팅방을 개설합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "채팅방 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Long> createRoom(@RequestBody ChatRoom newRoom) {
        long resultOfCreation = chatroomService.createRoom(newRoom);
        if (resultOfCreation >= 0)
            return ResponseEntity.status(HttpStatus.OK).body(resultOfCreation);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Long.MIN_VALUE);
    }

    // 특정 채팅방 의 메세지 최근 10개
    @GetMapping("/room/message/{id}")
    public ResponseEntity<List<Message>> roomInfo(@PathVariable long id,
                                                  @RequestParam(value = "page", defaultValue = "0") String page) {
        long idx = page.equals("0") ? 0 : Integer.parseInt(page) * PAGE + 1;
        List<Message> msgList = messageService.getMessagesByChatroomId(id, idx);
        return ResponseEntity.status(HttpStatus.OK).body(msgList);
    }

    // 특정 채팅방의 모든 메세지
    @GetMapping("/room/allMessage/{id}")
    @ApiOperation(value = "채팅방의 전체 채팅 목록", notes = "chatroomId를 사용하여 특정 채팅방의 전체 채팅 목록을 반환합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "채팅방 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<Message>> roomInfos(@PathVariable long id) {
        List<Message> msgList = messageService.getAllMessagesByChatroomId(id);
        return ResponseEntity.status(HttpStatus.OK).body(msgList);
    }


    @GetMapping("/room/chatroomInfo/{id}")
    @ApiOperation(value = "유저가 포함된 채팅방 목록", notes = "접속 유저가 속해있는 채팅방 목록을 반환합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "채팅방 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<ChatRoom>> roomInfosByUserid(@PathVariable long id) {
        List<ChatRoom> chatroom1 = chatroomService.getAllChatRoomsByUserId1(id);
        List<ChatRoom> chatroom2 = chatroomService.getAllChatRoomsByUserId1(id);
        List<ChatRoom> chatRoomList = new ArrayList<>();
        for(ChatRoom chatroom : chatroom1) chatRoomList.add(chatroom);
        for(ChatRoom chatroom : chatroom2) chatRoomList.add(chatroom);
        return ResponseEntity.status(HttpStatus.OK).body(chatRoomList);
    }

    //    // 특정 채팅방 타이틀 가져오기
//    @GetMapping("/room/{id}")
//    @ApiOperation(value = "채팅방 이름 가져오기", notes = "room id를 통해 채팅방의 제목을 가져옵니다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 404, message = "채팅방 없음"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public ResponseEntity<String> roomTitle(@PathVariable long id) {
//        String roomTitle = chatroomService.getRoomTitle(id);
//        if (roomTitle == null)
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//        return ResponseEntity.status(HttpStatus.OK).body(roomTitle);
//    }
    @PostMapping("/message/save")
    @ApiOperation(value = "채팅 저장", notes = "DB에 채팅 내용을 저장합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "채팅 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Message> saveMessage(@RequestBody @ApiParam(value="채팅 정보", required = true) Message message) {

        Message msg= messageService.saveMessage(message);
        return ResponseEntity.status(HttpStatus.OK).body(msg);


    }

}
