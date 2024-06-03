package com.timex.service;

import com.timex.model.Task;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String text) throws MessagingException;


    void createMail(String recipientEmail, Long taskId, String taskDescription);
}
