package com.ssafy.meeting.db.repository;

import com.ssafy.meeting.db.entitiy.MeetingQueue;
import com.ssafy.user.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingQueueRepository extends JpaRepository<MeetingQueue, Long> {

    @Query(
            "SELECT * from user u where u.userid = ?1"
    )
    User findByUserId(String userid);


    List<MeetingQueue> findAll();
}
