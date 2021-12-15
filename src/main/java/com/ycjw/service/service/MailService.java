package com.ycjw.service.service;

public interface MailService {
    /**
     * 发送纯文本邮件
     * @param receiverMail 接收者邮箱
     * @param subject 主题
     * @param text 邮箱内容
     * @return 是否发送成功
     */
    boolean sendMail(String receiverMail,String subject,String text);
}
