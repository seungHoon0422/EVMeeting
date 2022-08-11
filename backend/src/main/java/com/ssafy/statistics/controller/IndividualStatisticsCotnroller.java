package com.ssafy.statistics.controller;


import com.ssafy.statistics.model.MBTIDto;
import com.ssafy.statistics.model.MatchingRateDto;
import com.ssafy.statistics.model.MatchingTimeDto;
import com.ssafy.statistics.service.IndividualStatisticsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
