package com.ssafy.db.repository;

import com.ssafy.db.entity.CallMeeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<CallMeeting, Long> {

//    @Query
//    List<CallMeeting>

    List<CallMeeting> findAll();
}
