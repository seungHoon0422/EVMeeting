package com.ssafy.chat.service;

import com.ssafy.chat.db.entity.ChatRoom;
import com.ssafy.chat.db.entity.Message;
import com.ssafy.chat.db.repository.ChatroomRepository;
import com.ssafy.chat.db.repository.MessageRepository;
import com.ssafy.chat.model.MessageVO;
import com.ssafy.user.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService{

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ChatroomRepository chatroomRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Long insertMessage(Message chatMessage) {
        return messageRepository.save(chatMessage).getId();
    }

    @Override
    public List<MessageVO> getAllMessagesByChatroomId(long id) {

        List<Message> messages = messageRepository.findAllByChatroomId(id).orElse(null);

        // 입력된 chatroom id에 해당하는 ChatRoom 반환
        ChatRoom chatRoom = chatroomRepository.findById(id).orElse(null);
        // 채팅방에 속해있는 유저의 userid를 반환합니다.
        Long userid1 = chatRoom.getUserid1();
        Long userid2 = chatRoom.getUserid2();

        String userNickname1 = userRepository.findById(userid1).get().getUserid();
        String userNickname2 = userRepository.findById(userid2).get().getUserid();

        List<MessageVO> MessageVoList = new ArrayList<>();
        for(Message message : messages) {
            MessageVO vo = new MessageVO();
            vo.setId(message.getId());
            vo.setContent(message.getContent());
            vo.setChatroomId(message.getChatroomId());
            vo.setSenderId(message.getSenderId());
            if(message.getSenderId().equals(userid1)){
                vo.setUserId(userNickname1);
            } else{
                vo.setUserId(userNickname2);
            }
            MessageVoList.add(vo);
//            System.out.println("vo = " + vo);
        }
        return MessageVoList;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }


}
