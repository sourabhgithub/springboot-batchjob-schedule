package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Date;

@Component
public class EmailSender {
    private static final String TEMPLATE_FILE= "/mail-template";
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendSimpleMessage(String from ,String to, String subject) {
        final Context ctx = new Context();
        ctx.setVariable("name", "recipientName");
        ctx.setVariable("date", new Date());
        ctx.setVariable("senderName","senderName");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        String text = templateEngine.process(TEMPLATE_FILE, ctx);
        message.setText(text);
        emailSender.send(message);
    }
}
