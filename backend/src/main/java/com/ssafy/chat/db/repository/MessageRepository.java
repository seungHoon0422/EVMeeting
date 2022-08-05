package com.ssafy.chat.db.repository;

import com.ssafy.chat.db.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {


    @Override
    <S extends Message> S save(S chatMessage);

    Optional<List<Message>> findAllByChatroomId(long id);

    Message findById(long id);

}
