package com.ssafy.meeting.service;


import com.ssafy.meeting.db.entitiy.MeetingQueue;

import java.util.List;

public interface MeetingQueueService {
    // maven to gradle

    // application.properties update

    // ubuntu kms server (docker)

    // One2OneCall code fix

    // advanced, recording...
    MeetingQueue createMeeting(MeetingQueue meetingQueueDto);

    List<MeetingQueue> getCallMeetingByCategory();

    void deleteMatch(MeetingQueue meetingQueue);
}
