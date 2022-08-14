package com.ssafy.statistics.controller;


import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.statistics.db.entity.Statistics;
import com.ssafy.statistics.model.*;
import com.ssafy.statistics.service.IndividualStatisticsService;
import com.ssafy.user.db.entity.User;
import com.ssafy.user.request.UserRegisterPostReq;
import com.ssafy.user.response.UserLoginPostRes;
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

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/statistics/individual")
@RequiredArgsConstructor
@RestController

public class IndividualStatisticsCotnroller {

    @Autowired
    private final IndividualStatisticsService individualStatisticsService;

    @GetMapping("/matchingRate/{id}")
    @ApiOperation(value = "본인의 매칭율", notes = "본인의 기록들에서 매칭이 얼마나 됐는지 매칭율을 알아본다.")
    public ResponseEntity<MatchingRateDto> individualMatchingRate(@PathVariable Long id) {
        MatchingRateDto matchingRateResult = individualStatisticsService.matchingRate(id);
        return ResponseEntity.status(HttpStatus.OK).body(matchingRateResult);
    }

    @GetMapping("/matchingTime/{id}")
    @ApiOperation(value = "본인의 매칭 평균 시간", notes = "매칭 성공까지 시간이 얼마나 걸리는지 평균을 알아본다.")
    public ResponseEntity<MatchingTimeDto> individualMatchingTime(@PathVariable Long id) {
        MatchingTimeDto matchingTimeResult = individualStatisticsService.matchingTime(id);
        return ResponseEntity.status(HttpStatus.OK).body(matchingTimeResult);
    }

    @GetMapping("/mbtiRate/{id}")
    @ApiOperation(value = "본인과 매칭된 사람들의 mbti 비율", notes = "매칭이 성공한 사람들 중 mbti의 비율이 얼마나 되는지 체크해본다.")
    public ResponseEntity<List<MBTIDto>> individualMbtiRate(@PathVariable Long id) {
        List<MBTIDto> mbtiResult = individualStatisticsService.mbtiRate(id);
        return ResponseEntity.status(HttpStatus.OK).body(mbtiResult);
    }

    @GetMapping("/drinkRate/{id}")
    @ApiOperation(value = "본인과 매칭된 사람들의 음주량 비율", notes = "매칭이 성공한 사람들 중 음주량 비율이 얼마나 되는지 체크해본다.")
    public ResponseEntity<List<DrinkDto>> individualDrinkRate(@PathVariable Long id) {
        List<DrinkDto> drinkResult = individualStatisticsService.drinkRate(id);
        return ResponseEntity.status(HttpStatus.OK).body(drinkResult);
    }

    @GetMapping("/cigaretteRate/{id}")
    @ApiOperation(value = "본인과 매칭된 사람들의 흡연여부 비율", notes = "매칭이 성공한 사람들 중 흡연여부 비율이 얼마나 되는지 체크해본다.")
    public ResponseEntity<List<CigaretteDto>> individualCigaretteRate(@PathVariable Long id) {
        List<CigaretteDto> cigaretteResult = individualStatisticsService.cigaretteRate(id);
        return ResponseEntity.status(HttpStatus.OK).body(cigaretteResult);
    }

    @PostMapping("/addMatchingHistory")
    @ApiOperation(value = "매칭 기록 새롭게 남기기", notes = "3단계 매칭 시작할 때, 매칭 기록을 남긴다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> addMatchingHistory(
            @RequestBody @ApiParam(value = "매칭 기록에 관련된 정보들", required = true) MatchingDto matchingdto) {
        individualStatisticsService.createStatistic(matchingdto);
        return ResponseEntity.ok(UserLoginPostRes.of(200, "Success"));
    }

    @PostMapping("/editMatchingHistory")
    @ApiOperation(value = "매칭 기록 최종 성공 남기기", notes = "매칭이 최종 성사되고 채팅방이 새롭게 만들어질 때, 매칭 기록을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> editMatchingHistory(
            @RequestBody @ApiParam(value = "매칭 기록에 관련된 정보들", required = true) MatchingDto matchingdto) {
        individualStatisticsService.editStatistic(matchingdto);
        return ResponseEntity.ok(UserLoginPostRes.of(200, "Success"));
    }
}
