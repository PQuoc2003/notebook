package com.dpquoc.musima.service.impl;

import com.dpquoc.musima.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String emailFrom;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(emailFrom);
        mailSender.send(message);
    }

    @Override
    public void sendOtpEmail(String to, String subject, String name, String otp) throws MessagingException {
        Context context = new Context();
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", name);
        variables.put("otp", otp);
        context.setVariables(variables);



        String htmlContent = templateEngine.process("email/otp-template", context);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(emailFrom);
        helper.setText(htmlContent, true);


        mailSender.send(mimeMessage);
    }


}
