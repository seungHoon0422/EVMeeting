package com.ssafy.meeting.controller;

import com.ssafy.meeting.db.entitiy.Blacklist;
import com.ssafy.meeting.db.entitiy.MeetingQueue;
import com.ssafy.meeting.service.BlacklistService;
import com.ssafy.meeting.service.MeetingQueueService;
import com.ssafy.user.db.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "화상통화 API")
@RestController
@RequestMapping("/api/v1/meeting")
public class MeetingQueueController {
    @Autowired
    MeetingQueueService meetingQueueService;

    @Autowired
    BlacklistService blacklistService;

    @PostMapping("/join")
    @ApiOperation(value = "화상통화 요청")
//    @ApiResponse()
    public String join(@RequestBody @ApiParam(value="접속 정보", required = true) MeetingQueue meetingQueue){
        MeetingQueue list = meetingQueueService.getCallMeetingByCategory(meetingQueue);
        // 대기중인 세션이 있다면 해당 세션의 세션아이디를 리턴 -> 리턴하기 전에 해당 세션아이디를 테이블에서 지워준다
        if(list != null){
//            List<User> userlist = meetingQueueService.joinUserid(meetingQueue.getUserid());
//            User userinfo = userlist.get(0);
            String sessionId = list.getUserid();
//            meetingQueueService.deleteMatch(list); // 지워주는 부분을 조회 직후에 함으로써 동시성 문제를 최소화한다
            return sessionId;
        }
        else{ // 대기중인 세션이 없다면 callMeeting 데이터로 대기열 생성
            MeetingQueue temp = meetingQueueService.createMeeting(meetingQueue);
            return meetingQueue.getUserid(); // 생성한 세션아이디를 리턴 -> 이때는 세션아이디를 테이블에서 지우면 안된다
//            return meetingQueue.getUser().getUserid();
        }
    }

    @PostMapping("/userinfo")
    @ApiOperation(value = "상대방 정보 검색")
    public User userinfo(@RequestBody @ApiParam(value = "상대방 유저 아이디", required = true) String userid){
        System.out.println(userid);
        return meetingQueueService.joinUserid(userid).get(0);
    }

    @PostMapping("/exit")
    @ApiOperation(value = "화상통화 종료")
    public void exit(@RequestBody @ApiParam(value = "접속 정보", required = true) MeetingQueue meetingQueue){
        // 매칭이 종료되면 다시 대기큐에 등록
        // 프로필을 보고 닫힘버튼을 눌렀을 때 두 사용자의 정보를 테이블에 다시 올려줘야된다
        // MeetingQueue temp = meetingQueueService.createMeeting(meetingQueue); // 미팅 종료되었을 때 join화면으로 이동하는 것으로 명세 변경 -> api 삭제
    }
    @PostMapping("/endservice")
    @ApiOperation(value = "서비스 종료")
    public void endservice(@RequestBody @ApiParam(value = "접속 정보", required = true) Blacklist blacklist){
        meetingQueueService.deleteMatch(blacklist.getUserFrom()); // 대기큐에서 유저 데이터를 삭제
        if(blacklist.getUserTo() == null || blacklist.getUserFrom() == null) return;
        else{
            blacklistService.createBlacklist(blacklist); // 블랙리스트에 추가하는 로직 구현
        }
        // 접속종료 버튼을 누르지 않고, 비정상 종료를 할 때 처리가 필요
        // 사이트를 종료할 때 post를 보내도록 구현
    }
}
