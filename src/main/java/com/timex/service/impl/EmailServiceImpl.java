package com.timex.service.impl;

import com.timex.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public void sendEmail(String toEmail, String subject, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(emailFrom);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(text);
        javaMailSender.send(message);
    }

    public void createMail(String recipientEmail, Long taskId, String taskDescription) {
        String subject = "New Task Created";
        String body = "A new task has been created:\n" +
                "ID: " + taskId + "\n" +
                "Description: " + taskDescription;

        try {
            sendEmail(recipientEmail, subject, body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
