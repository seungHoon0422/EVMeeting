package com.ssafy.statistics.service;

import com.ssafy.statistics.model.GenderDto;
import com.ssafy.statistics.model.MBTIDto;
import com.ssafy.user.db.entity.User;
import com.ssafy.user.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticsService {

    @Autowired
    UserRepository userRepository;

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
}
