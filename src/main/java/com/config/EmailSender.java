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
    @Autowired
    private MailConfiguration mailConfig;
    public void sendSimpleMessage(Long jobId, String jobStatus) {
        final Context ctx = new Context();
        ctx.setVariable("name", "Team");
        ctx.setVariable("date", new Date());
        ctx.setVariable("senderName",mailConfig.getForm());
        ctx.setVariable("id",jobId);
        ctx.setVariable("status",jobStatus);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailConfig.getForm());
        message.setTo(mailConfig.getTo());
        message.setSubject(mailConfig.getSubject()+jobId + " JOB STATUS: "+jobStatus);
        String text = templateEngine.process(TEMPLATE_FILE, ctx);
        message.setText(text);
        emailSender.send(message);
    }
}
