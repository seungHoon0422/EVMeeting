package com.ssafy.chat.controller;

import com.ssafy.chat.db.entity.ChatRoom;
import com.ssafy.chat.db.entity.Message;
import com.ssafy.chat.model.MessageVO;
import com.ssafy.chat.service.IChatRoomService;
import com.ssafy.chat.service.IMessageService;
import com.ssafy.user.db.entity.User;
import com.ssafy.user.service.UserService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@RestController
public class ChatRoomController {

    @Autowired
    private final IChatRoomService chatroomService;
    @Autowired
    private final IMessageService messageService;
    @Autowired
    private final UserService userService;
//    final int PAGE = 10;

    // 채팅방 생성
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

    // 유저가 포함된 채팅방 목록 반환
    @GetMapping("/rooms/{id}")
    @ApiOperation(value = "유저가 포함된 채팅방 목록", notes = "접속 유저가 속해있는 채팅방 목록을 반환합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "채팅방 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<ChatRoom>> roomsByUserid(@PathVariable long id) {

        List<ChatRoom> rooms = chatroomService.findChatRoomByUserid(id);
        if(rooms == null || rooms.size() == 0)
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(rooms);

    }

    // 특정 채팅방의 모든 메세지
    @GetMapping("/room/allMessages/{id}")
    @ApiOperation(value = "채팅방의 전체 채팅 목록", notes = "chatroomId를 사용하여 특정 채팅방의 전체 채팅 목록을 반환합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "채팅방 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<Message>> roomInfos(@PathVariable long id) {

        ChatRoom chatRoom = chatroomService.getChatRoomByChatRoomId(id);

        Long userid1 = chatRoom.getUserid1();
        Long userid2 = chatRoom.getUserid2();

        List<Message> msgList = messageService.getAllMessagesByChatroomId(id);
        if(msgList == null || msgList.size() == 0)
            return ResponseEntity.status(HttpStatus.OK).body(null);

        List<MessageVO> MessageVoList = new ArrayList<>();
        for(Message message : msgList) {

            MessageVO vo = new MessageVO();
            vo.setId(message.getId());
            vo.setContent(message.getContent());
            vo.setChatroomId(message.getChatroomId());
            vo.setSenderId(message.getSenderId());
            System.out.println("vo = " + vo);
//            if(message.getSenderId().equals(user1.getId())){
//                vo.setUserId(user1.getUserid());
//            } else{
//                vo.setUserId(user2.getUserid());
//            }
            MessageVoList.add(vo);
        }
        return ResponseEntity.status(HttpStatus.OK).body(msgList);
    }

    // <추가> 채팅방 삭제
    // 채팅방 테이블에 삭제여부 표시
    @PutMapping("/room/delete/{chatroomId}")
    public ResponseEntity<Long> deleteRoom(@PathVariable long chatroomId) {
        chatroomService.deleteRoom(chatroomId);
            return ResponseEntity.status(HttpStatus.OK).body(chatroomId);
    }



    @PostMapping("/message/save")
    @ApiOperation(value = "채팅 저장", notes = "DB에 채팅 내용을 저장합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "채팅 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Message> saveMessage(@RequestBody @ApiParam(value="채팅 정보", required = true) Message message) {
        // 전송 메세지 저장
        Message msg= messageService.saveMessage(message);
        // 채팅방의 최신 메세지 id 저장
        chatroomService.UpdateRecentMessage(message);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }


}
