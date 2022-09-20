package com.board.domain.email.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Async
    public void send(String email, String authToken) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(email);
        smm.setSubject("회원가입 이메일 인증");
//        smm.setText("43.200.144.129/signs/confirm-email?email="+email+"&authToken="+authToken);
        smm.setText("http://localhost:8082/signs/confirm-email?email="+email+"&authToken="+authToken);

        javaMailSender.send(smm);
    }
}
