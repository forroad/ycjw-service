package com.ycjw.service.service.impl;

import com.ycjw.service.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class MailServiceImpl implements MailService {
    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public boolean sendMail(String receiverMail, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(receiverMail);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
            return true;
        }catch (Exception e){
            log.error("send email error! exception:{}", ExceptionUtils.getStackTrace(e));
            return false;
        }
    }
}
