package com.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.social.mail.MailService;
import com.social.mail.repository.MailRepository;


@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class SpringBootSocialAuthApplication  {// implements ApplicationRunner{

	@Autowired
    private MailRepository mailRepository;
	
	@Autowired
	MailService mailTemplateService;

	private static Logger log = LoggerFactory.getLogger(SpringBootSocialAuthApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSocialAuthApplication.class, args);
	}
	
	/*
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
    	
    	Mail email=mailRepository.findOne(1L);
    	email.setTo("shri110@gmail.com");
        Map<String,String> message=new HashMap<>();
        message.put("1","shri");
        message.put("2","shri");
        
        //emailService.sendSimpleMessage(mail);
        
        mailTemplateService.prepareAndSend(email, message);
    }*/
}
