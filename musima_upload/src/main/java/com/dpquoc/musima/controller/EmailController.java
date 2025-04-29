package com.dpquoc.musima.controller;

import com.dpquoc.musima.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public String sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {
        emailService.sendEmail(to, subject, body);
        return "Email sent successfully!";
    }

    @GetMapping("/send-otp-email")
    public String sendOtpEmail() {

        String to = "dinhphuquoc2003@gmail.com";
        String subject = "OTP for reset password";
        String name = "Dinh Phu Quoc";
        String otp = "123456";


        try {
            emailService.sendOtpEmail(to, subject, name, otp);
            return "OTP email sent successfully!";
        } catch (Exception e) {
            return "Error sending email: " + e.getMessage();
        }
    }

}
