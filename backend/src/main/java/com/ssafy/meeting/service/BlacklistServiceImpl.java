package com.ssafy.meeting.service;

import com.ssafy.meeting.db.entitiy.Blacklist;
import com.ssafy.meeting.db.repository.BlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blacklistService")
public class BlacklistServiceImpl implements BlacklistService{
    @Autowired
    BlacklistRepository blacklistRepository;
    @Override
    public void createBlacklist(Blacklist blacklist) {
        blacklistRepository.save(blacklist);
    }
}
