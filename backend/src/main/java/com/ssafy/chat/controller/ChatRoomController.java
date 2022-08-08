package com.ssafy.chat.controller;

import com.ssafy.chat.db.entity.ChatRoom;
import com.ssafy.chat.db.entity.Message;
import com.ssafy.chat.model.ChatRoomVO;
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
        if (resultOfCreation >= 0)          // 채팅방 생성 성공
            return ResponseEntity.status(HttpStatus.OK).body(resultOfCreation);
        else                                // 채팅방 생성 실패
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

        // 생성되어있는 모든 채팅방 리스트
        List<ChatRoom> rooms = chatroomService.getAllChatRooms();

        if (rooms == null || rooms.size() == 0)         // 생성된 채팅방이 없는 경우
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else                                            // 모든 채팅방 목록을 리턴합니다.
            return ResponseEntity.status(HttpStatus.OK).body(rooms);
    }

    // 유저가 포함된 채팅방 목록 반환
    @GetMapping("/rooms/{id}")
    @ApiOperation(value = "특정 유저의 채팅 목록", notes = "user의 id를 통해 포함되어있는 모든 채팅방 목록을 반환합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "채팅방 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<ChatRoomVO>> roomsByUserid(@PathVariable long id) {

        // 유저가 포함된 모든 채팅방 목록
        List<ChatRoomVO> rooms = chatroomService.findChatRoomByUserid(id);

        if(rooms == null || rooms.size() == 0)  // 채팅목록이 없는 경우
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else                                    // 유저가 포함된 채팅방 목록을 리턴합니다.
            return ResponseEntity.status(HttpStatus.OK).body(rooms);

    }

    // 특정 채팅방의 모든 메세지
    @GetMapping("/room/allMessages/{id}")
    @ApiOperation(value = "채팅방의 전체 채팅 목록", notes = "chatroomId를 사용하여 채팅방의 전체 채팅 목록을 반환합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "채팅방 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<MessageVO>> roomInfos(@PathVariable long id) {


        List<MessageVO> msgList = messageService.getAllMessagesByChatroomId(id);
        if(msgList == null || msgList.size() == 0)
            return ResponseEntity.status(HttpStatus.OK).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(msgList);
    }

    // 채팅방 삭제
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

    @DeleteMapping("/room/delete{chatroomId}")
    @ApiOperation(value = "채팅방 삭제", notes = "채팅방 삭제 후 모든 채팅메세지도 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "채팅 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<String> removeRoom(@PathVariable long chatroomId) {

        chatroomService.removeRoom(chatroomId);
        return ResponseEntity.status(HttpStatus.OK).body("Delete Success");
    }


}
