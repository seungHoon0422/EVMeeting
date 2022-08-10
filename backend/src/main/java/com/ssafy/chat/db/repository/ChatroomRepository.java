package com.ssafy.chat.db.repository;


import com.ssafy.chat.db.entity.ChatRoom;
import org.kurento.client.internal.server.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("SELECT cr FROM ChatRoom AS cr WHERE cr.userid1= :id or cr.userid2= :id")
    public List<ChatRoom> findChatRoomByUserid(@Param("id") Long id);

    @Query("SELECT cr FROM ChatRoom AS cr WHERE cr.userid1= :id1 and cr.userid2= :id2")
    public ChatRoom existChatroomByUserIds(@Param("id1") Long id1, @Param("id2") Long id2);
}
