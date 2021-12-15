package com.ycjw.service.controller;

import com.ycjw.service.service.MailService;
import com.ycjw.service.vo.MailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping("sendmail")
    public boolean sendMailPost(@RequestBody MailVo mailVo) {
        return mailService.sendMail(mailVo.getMail(), mailVo.getSubject(), mailVo.getText());
    }

    @GetMapping("sendmail")
    public boolean sendMail(@ModelAttribute MailVo mailVo) {
        return mailService.sendMail(mailVo.getMail(), mailVo.getSubject(), mailVo.getText());
    }
}
