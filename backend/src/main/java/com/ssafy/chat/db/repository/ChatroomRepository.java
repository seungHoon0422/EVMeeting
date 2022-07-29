package com.ssafy.chat.db.repository;


import com.ssafy.chat.db.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatroomRepository extends JpaRepository<ChatRoom, Long> {


    @Override
    <S extends ChatRoom> S save(S chatRoom);

    @Override
    List<ChatRoom> findAll();

    @Override
    Optional<ChatRoom> findById(Long aLong);

    ChatRoom findTitleById(long id);

    List<ChatRoom> findAllByUserid1(long id);

    List<ChatRoom> findAllByUserid2(long id);
}
