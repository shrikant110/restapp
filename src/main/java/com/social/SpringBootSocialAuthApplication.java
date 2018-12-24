package com.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.social.mail.EmailService;
import com.social.mail.Mail;

@EnableScheduling
@SpringBootApplication
public class SpringBootSocialAuthApplication  implements ApplicationRunner {

	@Autowired
    private EmailService emailService;

	private static Logger log = LoggerFactory.getLogger(SpringBootSocialAuthApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSocialAuthApplication.class, args);
	}
	
	
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("Spring Mail - Sending Email Attachment Configuration Example");

        Mail mail = new Mail();
        mail.setFrom("shrikant110@gmail.com");
        mail.setTo("shri110@gmail.com");
        mail.setSubject("Sending Email Attachment Configuration Example");
        mail.setContent("This tutorial demonstrates how to send an email with attachment using Spring Framework.");

        emailService.sendSimpleMessage(mail);
    }
}
