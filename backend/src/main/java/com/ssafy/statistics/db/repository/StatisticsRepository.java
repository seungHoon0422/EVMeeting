package com.ssafy.statistics.db.repository;

import com.ssafy.chat.db.entity.ChatRoom;
import com.ssafy.statistics.db.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    @Override
    List<Statistics> findAll();

    @Override
    Optional<Statistics> findById(Long id);



}