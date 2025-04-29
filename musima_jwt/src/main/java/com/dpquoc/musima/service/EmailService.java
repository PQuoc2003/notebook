package com.dpquoc.musima.service;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendEmail(String to, String subject, String body);

    void sendOtpEmail(String to, String subject, String name, String otp) throws MessagingException;
}
