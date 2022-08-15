package com.ssafy.meeting.service;

import com.ssafy.meeting.db.entitiy.Blacklist;
import com.ssafy.meeting.db.entitiy.MeetingQueue;
import com.ssafy.meeting.db.repository.BlacklistRepository;
import com.ssafy.meeting.db.repository.MeetingQueueRepository;
import com.ssafy.user.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("meetingQueueService")
public class MeetingQueueServiceImpl implements MeetingQueueService {
    @Autowired
    MeetingQueueRepository meetingQueueRepository;

    @Autowired
    BlacklistRepository blacklistRepository;
    @Override
    public MeetingQueue createMeeting(MeetingQueue meetingQueueDto) {
        return meetingQueueRepository.save(meetingQueueDto);
    }
    @Override
    public MeetingQueue getCallMeetingByCategory(MeetingQueue meetingQueue) {
//        List<MeetingQueue> list = meetingQueueRepository.findAll(); // 미팅큐에 대기중인 유저가 있는지 확인
        List<MeetingQueue> list; // gender 필터링 추가
        if(meetingQueue.getGender().equals("male")) list = meetingQueueRepository.findByGender("female");
        else list = meetingQueueRepository.findByGender("male");

        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i).getUserid());
            System.out.println(list.get(i).getGender());
        }
        // 블랙리스트 조회 필터링
        List<Blacklist> blist1 = blacklistRepository.findByUserFrom(meetingQueue.getUserid()); // userFrom 컬럼 기준으로 검색
        List<Blacklist> blist2 = blacklistRepository.findByUserTo(meetingQueue.getUserid()); // userTo 컬럼 기준으로 검색
        
        // list와 blist1 탐색, list와 blist2 탐색해서 블랙리스트에 존재하는 컬럼은 삭제해준다
        for(int i=0; i<blist1.size(); i++){ // blacklist userFrom 조회
            for(int j=list.size()-1; j>=0; j--){
                if(list.get(j).getUserid().equals(blist1.get(i).getUserTo())) list.remove(j);
            }
        }
        for(int i=0; i<blist2.size(); i++){ // blacklist userTo 조회
            for(int j=list.size()-1; j>=0; j--){
                if(list.get(j).getUserid().equals(blist2.get(i).getUserFrom())) list.remove(j);
            }
        }

        // 1. O(n^2)으로 탐색하는 방법
        // 2. 내정보, 상대정보(list)를 들고 select and 활용해 쿼리문을 던져보는 방법 -> DB에 접근하는 시간 > for문 돌아가는 시간

        if(list.isEmpty()){
            return null;
        }
        else{
            MeetingQueue temp = list.get(0);
            // 있다면 즉시 삭제하는게 동시성 문제를 최소화하는 방법
            meetingQueueRepository.delete(temp);
            return temp;
        }
    }
    @Override
    public void deleteMatch(String userid) {
        meetingQueueRepository.deleteByUserid(userid);
    }
    @Override
    public List<User> joinUserid(String userid){
        List<User> temp = meetingQueueRepository.findByUserId(userid);
        return temp;
    }
}
