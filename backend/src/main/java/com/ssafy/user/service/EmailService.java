package com.ssafy.user.service;

import com.ssafy.user.db.entity.User;

public interface EmailService {
    public void sendMail(User user, String tempPassword);
}
