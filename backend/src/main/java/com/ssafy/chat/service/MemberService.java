package com.ssafy.chat.service;

import com.ssafy.chat.db.entity.Member;
import com.ssafy.chat.db.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

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
        return memberRepository.exists(user);
    }
}
