package com.ssafy.meeting.db.repository;

import com.ssafy.meeting.db.entitiy.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {

}
