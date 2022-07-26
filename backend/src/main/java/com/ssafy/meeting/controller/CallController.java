package com.ssafy.meeting.controller;

import com.ssafy.meeting.service.CallService;
import com.ssafy.meeting.db.entitiy.CallMeeting;
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
@RequestMapping("/api/v1/call")
public class CallController {
    @Autowired
    CallService callService;

    @PostMapping("/join")
    @ApiOperation(value = "화상통화 요청")
//    @ApiResponse()
    public String join(@RequestBody @ApiParam(value="접속 정보", required = true) CallMeeting callMeeting){

        // callMeeting DB 리스트에 대기중인 세션이 있는지 확인
        List<CallMeeting> list = callService.getCallMeetingByCategory(); // 테이블 전체를 읽어오는건 비효율적일듯하다
        // 필터링 만들지 의논 -> 필터링을 쓴다면 해당 값을 기준으로 검색한 뒤 limit 1 적용하면 될듯 (jpa findAllTopten... 참고)

        // 대기중인 세션이 있다면 해당 세션의 세션아이디를 리턴 -> 리턴하기 전에 해당 세션아이디를 테이블에서 지워준다
        if(!list.isEmpty()){
            String sessionId = list.get(0).getSessionid();
            callService.deleteMatch(list.get(0));
            return sessionId;
        }
        else{ // 대기중인 세션이 없다면 callMeeting 데이터로 대기열 생성
            CallMeeting temp = callService.createCall(callMeeting);
            return callMeeting.getSessionid(); // 생성한 세션아이디를 리턴 -> 이때는 세션아이디를 테이블에서 지우면 안된다
        }
        // !! 동시성 문제 발생 -> 매칭된 세션아이디를 테이블에서 지우기 전 다른 사용자가 접근하면 3명이 한 세션아이디를 공유하게 된다
        // 세션아이디를 조회할 때 삭제를 우선으로 하고, 실패하면 다시 올리는 방식으로 접근?
    }
}
