package com.ssafy.api.service;

import com.ssafy.db.entity.CallMeeting;
import jdk.nashorn.internal.codegen.CompilerConstants;

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
