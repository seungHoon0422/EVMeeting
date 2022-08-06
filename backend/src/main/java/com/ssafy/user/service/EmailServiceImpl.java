package com.ssafy.user.service;

import com.ssafy.user.db.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Slf4j
@RequiredArgsConstructor
@Service("emailService") // 서비스 빈으로 등록
public class EmailServiceImpl implements EmailService {

    @Inject
    JavaMailSender mailSender; // root-context.xml에 설정한 bean, 의존성을 주입

    @Value("${EMAIL.ID}")
    private String senderMail;

    @Override
    public void sendMail(User user, String tempPassword) {
        try {
            // 이메일 객체
            MimeMessage msg;
            msg = mailSender.createMimeMessage();

            // 받는 사람을 설정 (수신자, 받는사람의 이메일 주소 객체를 생성해서 수신자 이메일주소를 담음)
            msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(user.getEmail()));

            /*
             * createMimeMessage() : MimeMessage객체를 생성시킴 (이것을 이용해서 메시지를 구성한 뒤 메일 발송)
             * addRecipient() : 메시지의 발신자를 설정 InternetAddress() : 이메일 주소 getReceiveMail() :
             * 수신자 이메일 주소
             */

            // 보내는 사람(이메일주소+이름)
            // (발신자, 보내는 사람의 이메일 주소와 이름을 담음)
            // 이메일 발신자
            msg.addFrom(new InternetAddress[] { new InternetAddress(senderMail+"@gmail.com", "ELEVATOR") });

            // 이메일 제목 (인코딩을 해야 한글이 깨지지 않음)
            msg.setSubject("[엘리베이터 안에서 우린 사랑을 나누지] 임시 비밀번호를 발급해드립니다.", "utf-8");
            // 이메일 본문 (인코딩을 해야 한글이 깨지지 않음)
            msg.setText("안녕하세요, " + user.getUsername() + "님,\n" +
                    "요청하신 임시 비밀번호를 발급해드립니다.\n" +
                    "아래의 비밀번호를 입력하신 후, 비밀번호를 재설정해주세요.\n\n" +
                    "임시 비밀번호 : " + tempPassword, "utf-8");

//            html로 보낼 경우
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper
//            = new MimeMessageHelper(message, true);
//            helper.setTo("test@host.com");
//            helper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);

            // 이메일 보내기
            mailSender.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
