package com.sumit.learning.service;

import com.sumit.learning.Utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailSenderService")
public class EmailSenderService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(String to, String from,String subject, String text) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
        Logger.printLog("Mail sent to: "+to);
    }

    public void sendEmail() {
    }
}

//org.springframework.mail.MailSendException: Mail server connection failed; nested exception is com.sun.mail.util.MailConnectException: Couldn't connect to host, port: smtp.gmail.com, 587; timeout 5000;
//        nested exception is:
//        java.net.UnknownHostException: smtp.gmail.com. Failed messages: com.sun.mail.util.MailConnectException: Couldn't connect to host, port: smtp.gmail.com, 587; timeout 5000;
//        nested exception is:
//        java.net.UnknownHostException: smtp.gmail.com