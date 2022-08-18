package com.ssafy.statistics.db.repository;

import com.ssafy.chat.db.entity.ChatRoom;
import com.ssafy.chat.db.entity.Message;
import com.ssafy.statistics.db.entity.Statistics;
import com.ssafy.user.db.entity.User;
import org.kurento.client.internal.server.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    @Override
    <S extends Statistics> S save(S Statistics);

    @Override
    List<Statistics> findAll();

    @Override
    Optional<Statistics> findById(Long id);

    @Query(value = "select * from statistics where (userid1= :userid1 and userid2= :userid2)\n" +
            "order by id desc limit 1;", nativeQuery = true)
    Optional<Statistics> getCorrectMatchingHistory(@Param("userid1") Long userid1, @Param("userid2") Long userid2);

    @Query(value = "set sql_safe_updates=0;", nativeQuery = true)
    Optional<Statistics> deleteStartingMatchingHistory();
    @Query(value = "delete from statistics where (userid1= :userid or userid2= :userid);", nativeQuery = true)
    Optional<Statistics> deleteMatchingHistory(@Param("userid") Long userid);


}