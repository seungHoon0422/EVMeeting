package com.ssafy.meeting.db.repository;

import com.ssafy.meeting.db.entitiy.Blacklist;
import org.kurento.client.internal.server.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {

    @Query(
            value = "SELECT b from Blacklist b where b.userFrom = ?1"
    )
    List<Blacklist> findByUserFrom(@Param("userFrom") String userFrom);

    @Query(
            value = "SELECT b from Blacklist b where b.userTo = ?1"
    )
    List<Blacklist> findByUserTo(@Param("userTo") String userTo);
}
