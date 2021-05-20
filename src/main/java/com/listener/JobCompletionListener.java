package com.listener;

import com.config.EmailSender;
import com.config.MailConfiguration;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.spring5.SpringTemplateEngine;

public class JobCompletionListener extends JobExecutionListenerSupport {

    @Autowired
    private EmailSender emailSender;
    @Autowired
    private MailConfiguration mailConfig;

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("JOB ID = " + jobExecution.getJobId());
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            System.out.println("BATCH JOB FAILED...!!!");
            emailSender.sendSimpleMessage(mailConfig.getForm(), mailConfig.getTo(), mailConfig.getSubject());
        }
    }

}
