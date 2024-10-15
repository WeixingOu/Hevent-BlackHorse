package com.itbh.hevent.services.commons;

import com.itbh.hevent.exceptions.EmailSMTPException;
import com.itbh.hevent.exceptions.api.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("");
            mailSender.send(message);
        } catch (MailException ex) {
            throw new EmailSMTPException("Failed to send email: " + ex.getMessage(), ex);
        }
    }
}
