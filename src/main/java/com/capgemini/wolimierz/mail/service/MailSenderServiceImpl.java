package com.capgemini.wolimierz.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailSenderServiceImpl {

    private final JavaMailSender mailSender;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    //TODO specify details- attachment, text of mail subject etc
    public void senMessage(String subject, String to, String text, File attachment) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        helper.addAttachment("Oferta", attachment);
        mailSender.send(message);
    }
}
