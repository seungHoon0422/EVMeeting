package com.ssafy.statistics.service;

import com.ssafy.statistics.db.entity.Statistics;
import com.ssafy.statistics.db.repository.StatisticsRepository;
import com.ssafy.statistics.model.GenderDto;
import com.ssafy.statistics.model.MBTIDto;
import com.ssafy.statistics.model.MatchingRateDto;
import com.ssafy.statistics.model.MatchingTimeDto;
import com.ssafy.user.db.entity.User;
import com.ssafy.user.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndividualStatisticsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatisticsRepository statisticsRepository;

    public MatchingRateDto matchingRate(Long id) {
        MatchingRateDto matchingRateResult = new MatchingRateDto();
        //userid를 기반으로 Statistics DB에서 findAll()을 통해 모든 기록을 가져온다.
        List<Statistics> statistics = statisticsRepository.findAll();

        //가져온 기록들을 바탕으로, 찾고자 하는 사용자의 기록을 고른다.
        for(Statistics statistic : statistics){
            if((statistic.getUserid1() == id) || (statistic.getUserid2() == id)){
                //Total Count 1 증가
                matchingRateResult.setTotalCount(matchingRateResult.getTotalCount()+1);

                //matched가 True인지 False인지 비교하고, True라면 1 증가
                if(statistic.getMatched()){
                    matchingRateResult.setMatchingCount(matchingRateResult.getMatchingCount()+1);
                }
            }
        }

        //전체 기록에 비해 matched의 비율이 얼마나 되는지 계산
        matchingRateResult.setMatchingPercent(Math.round(matchingRateResult.getMatchingCount() / (double) matchingRateResult.getTotalCount() * 100 * 10) / 10.0);

        //결과 출력
        return matchingRateResult;
    }
    public MatchingTimeDto matchingTime(Long id) {
        MatchingTimeDto matchingTimeResult = new MatchingTimeDto();
        //userid를 기반으로 Statistics DB에서 findAll()을 통해 모든 기록을 가져온다.
        List<Statistics> statistics = statisticsRepository.findAll();

        //계산에 사용되는 변수들
        int totalTime = 0;  //sec
        int count = 0;

        //가져온 기록들을 바탕으로, 찾고자 하는 사용자의 기록을 고른다.
        for(Statistics statistic : statistics){
            if((statistic.getUserid1() == id) || (statistic.getUserid2() == id)){
                //matched가 True인지 False인지 비교하고, True라면 1 증가
                if(statistic.getMatched()){
                    totalTime += statistic.getUsedTime();
                    count++;
                }
            }
        }

        //결과를 객체에 저장
        matchingTimeResult.setTime(totalTime);
        matchingTimeResult.setMatchingCount(count);
        if(count == 0){
            matchingTimeResult.setMatchingTimeAverage(0);
        }
        else{
            matchingTimeResult.setMatchingTimeAverage(Math.round((totalTime / (double) count) * 10 ) / 10.0);
        }

        //결과 출력
        return matchingTimeResult;
    }
}
