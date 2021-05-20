package com.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class MailConfiguration {
    @Value("${send.from}")
    private String form;
    @Value("${send.to}")
    private String to;
    @Value("${mail.subject}")
    private String subject;
}
