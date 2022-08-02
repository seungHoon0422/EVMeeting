package com.ssafy.meeting.service;

import com.ssafy.meeting.db.entitiy.MeetingQueue;
import com.ssafy.meeting.db.repository.MeetingQueueRepository;
import com.ssafy.user.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("meetingQueueService")
public class MeetingQueueServiceImpl implements MeetingQueueService {
    @Autowired
    MeetingQueueRepository meetingQueueRepository;
    @Override
    public MeetingQueue createMeeting(MeetingQueue meetingQueueDto) {
        return meetingQueueRepository.save(meetingQueueDto);
    }
    @Override
    public MeetingQueue getCallMeetingByCategory() {
        List<MeetingQueue> list = meetingQueueRepository.findAll(); // 미팅큐에 대기중인 유저가 있는지 확인
        // 미팅큐 리스트를 쿼리문으로 조회할 때 필터링을 추가하면 된다
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
    public void deleteMatch(MeetingQueue meetingQueue) {
        meetingQueueRepository.delete(meetingQueue);
    }
    @Override
    public List<User> joinUserid(String userid){
        List<User> temp = meetingQueueRepository.findByUserId(userid);
        return temp;
    }
}
