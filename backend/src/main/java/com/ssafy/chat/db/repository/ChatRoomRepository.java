package com.ssafy.chat.db.repository;

import com.ssafy.chat.db.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {


    @Override
    <S extends ChatRoom> S save(S chatRoom);

    @Override
    List<ChatRoom> findAll();

    String findTitleById(long id);
}
