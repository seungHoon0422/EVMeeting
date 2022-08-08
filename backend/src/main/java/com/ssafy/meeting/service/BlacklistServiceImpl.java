package com.ssafy.meeting.service;

import com.ssafy.meeting.db.entitiy.Blacklist;
import com.ssafy.meeting.db.repository.BlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("blacklistService")
public class BlacklistServiceImpl implements BlacklistService{
    @Autowired
    BlacklistRepository blacklistRepository;
    @Override
    public void createBlacklist(Blacklist blacklist) {
        List<Blacklist> blist = blacklistRepository.findByUserFromAndUserTo(blacklist.getUserFrom(), blacklist.getUserTo()); // 블랙리스트에 이미 등록되었는지 확인
        if(blist.isEmpty()) blacklistRepository.save(blacklist); // 등록되어있지 않을 때만 블랙리스트에 등록
    }
}
