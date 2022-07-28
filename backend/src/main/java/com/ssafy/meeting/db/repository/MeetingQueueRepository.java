package com.ssafy.meeting.db.repository;

import com.ssafy.meeting.db.entitiy.MeetingQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingQueueRepository extends JpaRepository<MeetingQueue, Long> {

//    @Query
//    List<CallMeeting>

    List<MeetingQueue> findAll();
}
