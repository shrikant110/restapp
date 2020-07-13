package com.sbs.vc.datapro.email;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.sbs.vc.datapro.auth.model.User;
import com.sbs.vc.datapro.auth.model.UserRegistration;
import com.sbs.vc.datapro.email.model.MailDomain;
import com.sbs.vc.datapro.email.repository.MailDomainRepository;
import com.sbs.vc.datapro.exceptions.ProcessFailedException;

@PropertySources({ @PropertySource("classpath:application_env.properties") })
@Service
public class ClientEmails {

	@Autowired
	MailServiceProvider mailTemplateService;

	@Autowired
	MailDomainRepository emailDomainRepository;

	@Autowired
	Environment environment;

	public String registrationMail(UserRegistration user, String activationtoken, String country) throws ProcessFailedException {
		MailDomain email = emailDomainRepository.findById(1L).get();
		email.setTo(user.getUsername());
		Map<String, Object> message = new HashMap<>();
		message.put("1", user.getFirstName());
		message.put("2", environment.getProperty("BASE_URL") + "/account/activateUser?emailId=" + user.getUsername()
				+ "&Id=" + user.getId() + "&token=" + activationtoken);
		mailTemplateService.prepareAndSend(email, message);
		return activationtoken;
	}

	public void activationMail(User user,String country) {
		/*MailDomain email = emailDomainRepository.findById(2L).get();
		email.setTo(user.getUsername());
		Map<String, Object> message = new HashMap<>();
		message.put("1", user.getFirstName());
		message.put("2", user.getPassword());
		mailTemplateService.prepareAndSend(email, message); */
		
		if(country.equalsIgnoreCase("Israel")) {
    		sendStaticContent(12L, user.getUsername());
    	}else {
    		sendStaticContent(11L, user.getUsername());
    	}

	}

	public void changePasswordMail(User userDommain) {
		MailDomain email = emailDomainRepository.findById(3L).get();
		email.setTo(userDommain.getUsername());
		Map<String, Object> message = new HashMap<>();
		message.put("1", userDommain.getFirstName());
		// message.put("2", BIOSEncryption.dncrypt(userDommain.getPassword()));
		mailTemplateService.prepareAndSend(email, message);

	}

	public void resetPasswordMail(User userDommain) {
		MailDomain email = emailDomainRepository.findById(4L).get();
		email.setTo(userDommain.getUsername());
		Map<String, Object> message = new HashMap<>();
		message.put("1", userDommain.getFirstName());
		message.put("2", environment.getProperty("BASE_URL") + "/account/changepasswordReq?Id=" + userDommain.getId()
				+ "&token=" + userDommain.getAccActivationToken());
		mailTemplateService.prepareAndSend(email, message);
	}

	public void errorNotification(String error) {
		MailDomain email = emailDomainRepository.findById(5L).get();
		Map<String, Object> message = new HashMap<>();
		message.put("error", error);
		mailTemplateService.prepareAndSend(email, message);
	}

	public void sendNotification(String messageContent) {
		MailDomain email = emailDomainRepository.findById(6L).get();
		Map<String, Object> message = new HashMap<>();
		message.put("message", messageContent);
		mailTemplateService.prepareAndSend(email, message);
	}

	/**
	 * This method used to sent the mail bases on the email ID and all static content
	 * @param templateId
	 */
	public void sendStaticContent(long templateId,String email) {
		MailDomain mailDomain = emailDomainRepository.findById(templateId).get();
		mailDomain.setTo(email);
		Map<String, Object> message = new HashMap<>();
		mailTemplateService.prepareAndSend(mailDomain, message);
	}
	
	/**
	 * This method used to sent the mail bases on the email ID and all static content
	 * @param emailId
	 */
	public void sendDynamicContent(long templateId,String email,Map<String, Object> message) {
		MailDomain mailDomain = emailDomainRepository.findById(templateId).get();
		mailDomain.setTo(email);
		mailTemplateService.prepareAndSend(mailDomain, message);
	}
	
	
	public void testMailTemplate(long id) {
		MailDomain email = emailDomainRepository.findById(id).get();
		Map<String, Object> message = new HashMap<>();

		message.put("name", "shrikant ");
		message.put("subscriptionDate", new Date());
		message.put("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
		message.put("imageResourceName", "imageResourceName");
		mailTemplateService.prepareAndSend(email, message);
	}
	
	
	/**
	 * This method used to sent the mail bases on the email ID and all static content
	 * @param templateId
	 */
	public void sendGroupEmail(long templateId,String emails) {
		System.out.println("EMail List: "+emails);
		MailDomain mailDomain = emailDomainRepository.findById(templateId).get();
		mailDomain.setBcc(emails);
		Map<String, Object> message = new HashMap<>();
		mailTemplateService.prepareAndBCCSend(mailDomain, message);
	}

}
