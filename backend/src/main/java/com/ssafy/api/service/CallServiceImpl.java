package com.ssafy.api.service;

import com.ssafy.db.entity.CallMeeting;
import com.ssafy.db.repository.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("callService")
public class CallServiceImpl implements CallService {

    @Autowired
    CallRepository callRepository;

    @Override
    public CallMeeting createCall(CallMeeting callMeetingDto) {
        return callRepository.save(callMeetingDto);
    }

    @Override
    public List<CallMeeting> getCallMeetingByCategory() {
        return callRepository.findAll();
    }

    @Override
    public void deleteMatch(CallMeeting callMeeting) {
        callRepository.delete(callMeeting);
    }
}
