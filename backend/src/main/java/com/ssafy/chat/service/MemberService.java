package com.ssafy.chat.service;

import com.ssafy.chat.db.entity.Member;
import com.ssafy.chat.db.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class MemberService implements IUserService{


    @Autowired
    MemberRepository memberRepository;

    @Override
    public long createUser(Member newUser) {
        memberRepository.save(newUser);
        Optional<Member> member = memberRepository.
                findById(Long.valueOf(newUser.getId()));
        return member.get().getId();
    }

    @Override
    public boolean signin(Member user) {
        return true;
    }
}
