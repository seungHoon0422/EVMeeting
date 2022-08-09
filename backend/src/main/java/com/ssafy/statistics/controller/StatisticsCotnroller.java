package com.ssafy.statistics.controller;


import com.ssafy.statistics.model.MBTIDto;
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

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
@RestController

public class StatisticsCotnroller {

    @Autowired
    private final StatisticsService statisticsService;
    @ApiOperation(value = "MBTI 전체 통계 정보", notes = "모든 user에 대한 MBTI 통계 정보 입니다.")
    @GetMapping("/total/MBTI")
    public ResponseEntity<List<MBTIDto>> totalMbtiStatistics() {

        List<MBTIDto> mbtiResult = statisticsService.totalStatisticsForMbti();
        return ResponseEntity.status(HttpStatus.OK).body(mbtiResult);
    }


}
