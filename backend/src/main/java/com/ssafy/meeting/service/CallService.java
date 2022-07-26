package com.ssafy.meeting.service;

import com.ssafy.meeting.db.entitiy.CallMeeting;

import java.util.List;

public interface CallService {
    // maven to gradle

    // application.properties update

    // ubuntu kms server (docker)

    // One2OneCall code fix

    // advanced, recording...
    CallMeeting createCall(CallMeeting callMeetingDto);

    List<CallMeeting> getCallMeetingByCategory();

    void deleteMatch(CallMeeting callMeeting);
}
