package com.ssafy.statistics.controller;


import com.ssafy.statistics.model.GenderDto;
import com.ssafy.statistics.model.MBTIDto;
import com.ssafy.statistics.model.MatchingTimeDto;
import com.ssafy.statistics.service.StatisticsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
@RestController

public class StatisticsCotnroller {

    @Autowired
    private final StatisticsService statisticsService;



    @GetMapping("/total/mbti")
    @ApiOperation(value = "MBTI 전체 통계 정보", notes = "모든 user에 대한 MBTI 통계 정보 입니다.")
    public ResponseEntity<List<MBTIDto>> totalMbtiStatistics() {

        List<MBTIDto> mbtiResult = statisticsService.totalStatisticsForMbti();
        return ResponseEntity.status(HttpStatus.OK).body(mbtiResult);
    }

    @GetMapping("/total/gender")
    @ApiOperation(value = "남녀 비율 통계", notes = "모든 user에 대한 남녀비율 통계 정보 입니다.")
    public ResponseEntity<GenderDto> totalGenderStatistics() {

        GenderDto genderDto = statisticsService.totalGenderStatistics();
        return ResponseEntity.status(HttpStatus.OK).body(genderDto);
    }


    @GetMapping("/total/matchingTime")
    @ApiOperation(value = "시간별 매칭 정보", notes = "매칭 현황에 따라 시간별 매칭 정보를 제공합합니다.")
    public ResponseEntity<List<MatchingTimeDto>> totalMatchingPerTimeStatistics() {

        List<MatchingTimeDto> timeDtoList = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(timeDtoList);
    }

}
