package com.ssafy.chat.db.repository;

import com.ssafy.chat.db.entity.Member;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 멤버 객체를 저장
    @Override
    <S extends Member> S save(S user);

    @Override
    Optional<Member> findById(Long id);

    // 로그인 확인 쿼리 : 입력된 id, password를 통해서 로그인 정보 확인

    boolean exists(Member user);
    boolean existsByIdAndPassword(Long id, String Password);
}
