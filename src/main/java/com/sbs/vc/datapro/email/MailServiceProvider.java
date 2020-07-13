package com.sbs.vc.datapro.email;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.sbs.vc.datapro.email.model.MailDomain;
@PropertySources({ @PropertySource("classpath:application_env.properties") })
@Service
public class MailServiceProvider {
	
	@Autowired
	Environment environment;
	
	private JavaMailSender mailSender;
    private MailContentBuilder mailContentBuilder;

    @Autowired
    public MailServiceProvider(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
        this.mailSender = mailSender;
        this.mailContentBuilder = mailContentBuilder;
    }
    
   

    public void prepareAndSend(MailDomain mailDomain, Map<String,Object> messageList) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom(mailDomain.getFrom());
            messageHelper.setTo(mailDomain.getTo());
            messageHelper.setSubject(mailDomain.getSubject());
            if(mailDomain.getIsAttachment()) {
            	messageHelper.addAttachment(mailDomain.getFilename(),new File(mailDomain.getLocation()));
            }
           
            if(mailDomain.getIsTempate()) {
            	String content = mailContentBuilder.build(messageList,mailDomain.getTemplateName());
                messageHelper.setText(content, true);
            }else {
            	String content=mailDomain.getContent();
            	for (Map.Entry<String, Object> entry : messageList.entrySet()) {
            	        content=content.replaceAll("\\{"+entry.getKey()+"\\}",entry.getValue()+"");
            	  //      System.out.println("Mail content:--->"+content);
            	}
            	//messageList.forEach((k, v) -> this.content=this.content.replaceAll("{"+k+"}",v+""));
            	messageHelper.setText(content, true);
            	FileSystemResource res = new FileSystemResource(new File(environment.getProperty("LOGO_LOCATION")));
            	messageHelper.addInline("aurora_logo", res);
            }
        };
        
        
        
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
          System.out.println("Error on sending email :"+e);
        	// runtime exception; compiler will not force you to handle it
        }
    }
    
    
    public void sendtemplateMail(MailDomain mailDomain, Map<String,Object> messageList) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(mailDomain.getFrom());
            messageHelper.setTo(mailDomain.getTo());
            messageHelper.setSubject(mailDomain.getSubject());
            if(mailDomain.getIsAttachment()) {
            	messageHelper.addAttachment(mailDomain.getFilename(),new File(mailDomain.getLocation()));
            }
           
            if(mailDomain.getIsTempate()) {
            	String content = mailContentBuilder.build(messageList,mailDomain.getTemplateName());
                messageHelper.setText(content, true);
            }else {
            
            }
        };
        
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
          System.out.println(e);
        	// runtime exception; compiler will not force you to handle it
        }
    }
    public void prepareAndBCCSend(MailDomain mailDomain, Map<String,Object> messageList) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom(mailDomain.getFrom());
            messageHelper.setBcc(mailDomain.getBcc().split(","));
            messageHelper.setSubject(mailDomain.getSubject());
            if(mailDomain.getIsAttachment()) {
            	messageHelper.addAttachment(mailDomain.getFilename(),new File(mailDomain.getLocation()));
            }
           
            if(mailDomain.getIsTempate()) {
            	String content = mailContentBuilder.build(messageList,mailDomain.getTemplateName());
                messageHelper.setText(content, true);
            }else {
            	String content=mailDomain.getContent();
            	for (Map.Entry<String, Object> entry : messageList.entrySet()) {
            	        content=content.replaceAll("\\{"+entry.getKey()+"\\}",entry.getValue()+"");
            	  //      System.out.println("Mail content:--->"+content);
            	}
            	//messageList.forEach((k, v) -> this.content=this.content.replaceAll("{"+k+"}",v+""));
            	messageHelper.setText(content, true);
            	FileSystemResource res = new FileSystemResource(new File(environment.getProperty("LOGO_LOCATION")));
            	messageHelper.addInline("aurora_logo", res);
            }
        };
        
        
        
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
          System.out.println("Error on sending email :"+e);
        	// runtime exception; compiler will not force you to handle it
        }
    }
 }
