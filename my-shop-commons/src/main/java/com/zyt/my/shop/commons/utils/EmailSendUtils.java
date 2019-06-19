package com.zyt.my.shop.commons.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 邮件发送工具类
 */
public class EmailSendUtils {
    public void send(String subject, String msg, String... to) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName ("smtp.qq.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("173982112@qq.com", "wnnsfrvftifsbibe"));
        email.setSSLOnConnect(true);
        email.setFrom("173982112@qq.com");
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }
}
