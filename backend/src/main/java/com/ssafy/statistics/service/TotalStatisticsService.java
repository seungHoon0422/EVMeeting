package com.ssafy.statistics.service;

import com.ssafy.statistics.db.entity.Statistics;
import com.ssafy.statistics.db.repository.StatisticsRepository;
import com.ssafy.statistics.model.GenderDto;
import com.ssafy.statistics.model.MBTIDto;
import com.ssafy.statistics.model.MatchingTimeDto;
import com.ssafy.user.db.entity.User;
import com.ssafy.user.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TotalStatisticsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatisticsRepository statisticsRepository;


    public List<MBTIDto> totalStatisticsForMbti() {

        Map<String, Integer> mbtiCount = new HashMap<>();
        List<User> users = userRepository.findAll();

        int totalCount = users.size();
        System.out.println("totalCount = " + totalCount);
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
        return MbtiResult;
    }

    private double calcPercent(int count, int totalCount) {
        return Math.round(((double) count / totalCount) * 100);
    }

    public GenderDto totalGenderStatistics() {

        GenderDto genderDto = new GenderDto();
        int maleCount = userRepository.countUserByGender("Male");
        int femaleCount = userRepository.countUserByGender("Female");
        int totalCount = maleCount + femaleCount;
        genderDto.setTotalCount(totalCount);
        genderDto.setMaleCount(maleCount);
        genderDto.setFemaleCount(femaleCount);
        genderDto.setMalePercent(calcPercent(maleCount, totalCount));
        genderDto.setFemalePercent(calcPercent(femaleCount, totalCount));
        System.out.println("genderDto = " + genderDto);

        return genderDto;
    }

    public List<MatchingTimeDto> getTimeStatistics() {

        List<Statistics> matchTimes = statisticsRepository.findAll();
        Map<Integer, Integer> timeCount = new HashMap<>();
        for(int time = 0; time < 24; time++)
            timeCount.put(time, 0);

        int startHour;
        for(Statistics statistic : matchTimes) {
            startHour = statistic.getStartTimeHour();

            timeCount.put(startHour, timeCount.get(startHour)+1);
        }
        List<MatchingTimeDto> dtoResult = new ArrayList<>();

        for(int i=0; i<24; i++) {
            dtoResult.add(new MatchingTimeDto(i,
                    timeCount.get(i),
                    calcPercent(timeCount.get(i), matchTimes.size())));
        }


        return dtoResult;
    }

}
