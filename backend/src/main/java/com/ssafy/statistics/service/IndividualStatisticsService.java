package com.ssafy.statistics.service;

import com.ssafy.statistics.db.entity.Statistics;
import com.ssafy.statistics.db.repository.StatisticsRepository;
import com.ssafy.statistics.model.*;
import com.ssafy.user.db.entity.User;
import com.ssafy.user.db.repository.UserRepository;
import com.ssafy.user.request.UserRegisterPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    public List<MBTIDto> mbtiRate(Long id){
        List<User> users = userRepository.findMatchedThemById(id);

        //User들의 MBTI를 분석하여 mbtiRateResult에 넣기
        Map<String, Integer> mbtiCount = new HashMap<>();
        int totalCount = users.size();
        System.out.println("TOTAL COUNT : " + totalCount);
        for(User user : users) {
            String Mbti = user.getMbti();
            if(!mbtiCount.containsKey(Mbti)) mbtiCount.put(Mbti, 1);
            else mbtiCount.put(Mbti, mbtiCount.get(Mbti)+1);
        }

        List<MBTIDto> MbtiResult = new ArrayList<>();
        for(String mbti : mbtiCount.keySet()) {
            MBTIDto dto = new MBTIDto();
            dto.setType(mbti);
            dto.setCount(mbtiCount.get(mbti));
            dto.setPercent(calcPercent(mbtiCount.get(mbti), totalCount));
            MbtiResult.add(dto);
        }

        Collections.sort(MbtiResult, new Comparator<MBTIDto>() {
            @Override
            public int compare(MBTIDto o1, MBTIDto o2) {
                return o2.getCount() - o1.getCount();
            }
        });

        System.out.println("MbtiResult = " + MbtiResult.size());

        //결과 출력
        return MbtiResult;

    }

    public List<DrinkDto> drinkRate(Long id){
        List<User> users = userRepository.findMatchedThemById(id);

        //User들의 음주량을 분석하여 drinkRateResult에 넣기
        Map<String, Integer> drinkCount = new HashMap<>();
        int totalCount = users.size();
        System.out.println("TOTAL COUNT : " + totalCount);
        for(User user : users) {
            String Drink = user.getDrink();
            if(!drinkCount.containsKey(Drink)) drinkCount.put(Drink, 1);
            else drinkCount.put(Drink, drinkCount.get(Drink)+1);
        }

        List<DrinkDto> DrinkResult = new ArrayList<>();
        for(String drink : drinkCount.keySet()) {
            DrinkDto dto = new DrinkDto();
            dto.setType(drink);
            dto.setCount(drinkCount.get(drink));
            dto.setPercent(calcPercent(drinkCount.get(drink), totalCount));
            DrinkResult.add(dto);
        }

        Collections.sort(DrinkResult, new Comparator<DrinkDto>() {
            @Override
            public int compare(DrinkDto o1, DrinkDto o2) {
                return o2.getCount() - o1.getCount();
            }
        });

        System.out.println("DrinkResult = " + DrinkResult.size());

        //결과 출력
        return DrinkResult;

    }

    public List<CigaretteDto> cigaretteRate(Long id){
        List<User> users = userRepository.findMatchedThemById(id);

        //User들의 흡연 여부를 분석하여 cigaretteRateResult에 넣기
        Map<String, Integer>  cigaretteCount = new HashMap<>();
        int totalCount = users.size();
        System.out.println("TOTAL COUNT : " + totalCount);
        for(User user : users) {
            String Cigarette = user.getCigarette();
            if(!cigaretteCount.containsKey(Cigarette)) cigaretteCount.put(Cigarette, 1);
            else cigaretteCount.put(Cigarette, cigaretteCount.get(Cigarette)+1);
        }

        List<CigaretteDto> CigaretteResult = new ArrayList<>();
        for(String cigarette : cigaretteCount.keySet()) {
            CigaretteDto dto = new CigaretteDto();
            dto.setType(cigarette);
            dto.setCount(cigaretteCount.get(cigarette));
            dto.setPercent(calcPercent(cigaretteCount.get(cigarette), totalCount));
            CigaretteResult.add(dto);
        }

        Collections.sort(CigaretteResult, new Comparator<CigaretteDto>() {
            @Override
            public int compare(CigaretteDto o1, CigaretteDto o2) {
                return o2.getCount() - o1.getCount();
            }
        });

        System.out.println("CigaretteResult = " + CigaretteResult.size());

        //결과 출력
        return CigaretteResult;

    }
    public Statistics createStatistic(MatchingDto matchingdto) {
        Statistics statistic = new Statistics();
        statistic.setUserid1(matchingdto.getUserid1());
        statistic.setUserid2(matchingdto.getUserid2());

        //현재 날짜 구하기 (년,월,일)
        LocalDate nowDate = LocalDate.now();
        statistic.setDate(nowDate.getYear() + "-" + nowDate.getMonthValue() + "-" + nowDate.getDayOfMonth());
        statistic.setDateYear(nowDate.getYear());
        statistic.setDateMonth(nowDate.getMonthValue());
        statistic.setDateDay(nowDate.getDayOfMonth());

        //현재 시작시간 구하기 (시,분,초)
        LocalTime nowTime = LocalTime.now();
        statistic.setStartTime(nowTime.getHour() + ":" + nowTime.getMinute() + ":" + nowTime.getSecond());
        statistic.setStartTimeHour(nowTime.getHour());
        statistic.setStartTimeMin(nowTime.getMinute());
        statistic.setStartTimeSec(nowTime.getSecond());

        statistic.setMatched(false);    //기본값을 false로 설정
        return statisticsRepository.save(statistic);
    }

    public Statistics editStatistic(MatchingDto matchingdto) {
        //DB에서 해당하는 데이터 불러오기
        Statistics statistic = statisticsRepository.getCorrectMatchingHistory(matchingdto.getUserid1(), matchingdto.getUserid2()).get();
        statistic.setUsedTime(matchingdto.getUsedTime());
        statistic.setMatched(true);    //기본값을 false로 설정
        return statisticsRepository.save(statistic);
    }

    public void deleteStatistic(Long userid) {
        //DB에서 해당하는 데이터 전부 지우기
        statisticsRepository.deleteStartingMatchingHistory();
        statisticsRepository.deleteMatchingHistory(userid);
        return;
    }

    private double calcPercent(int count, int totalCount) {
        return Math.round(((double) count / totalCount) * 100);
    }

}