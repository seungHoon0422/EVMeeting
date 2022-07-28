package com.ssafy.chat.db.repository;

import com.ssafy.chat.db.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {


    @Override
    <S extends Message> S save(S chatMessage);

    List<Message> findAllByChatroomId(long id);

}
