package com.ssafy.meeting.db.repository;

import com.ssafy.meeting.db.entitiy.CallMeeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<CallMeeting, Long> {

//    @Query
//    List<CallMeeting>

    List<CallMeeting> findAll();
}
