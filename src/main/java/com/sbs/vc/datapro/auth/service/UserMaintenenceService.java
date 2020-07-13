package com.sbs.vc.datapro.auth.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sbs.vc.config.util.AESEncryption;
import com.sbs.vc.config.util.DateUtil;
import com.sbs.vc.config.util.PasswordGenerator;
import com.sbs.vc.config.util.URLEncodeDecode;
import com.sbs.vc.datapro.auth.constant.TokenIssuePurpose;
import com.sbs.vc.datapro.auth.constant.UserStatus;
import com.sbs.vc.datapro.auth.exception.AppException;
import com.sbs.vc.datapro.auth.model.ImageModel;
import com.sbs.vc.datapro.auth.model.IssueToken;
import com.sbs.vc.datapro.auth.model.Role;
import com.sbs.vc.datapro.auth.model.RoleName;
import com.sbs.vc.datapro.auth.model.Status;
import com.sbs.vc.datapro.auth.model.User;
import com.sbs.vc.datapro.auth.model.UserActivity;
import com.sbs.vc.datapro.auth.model.UserRegistration;
import com.sbs.vc.datapro.auth.repository.ImageRepository;
import com.sbs.vc.datapro.auth.repository.IssueTokenRepository;
import com.sbs.vc.datapro.auth.repository.RoleRepository;
import com.sbs.vc.datapro.auth.repository.StatusRepository;
import com.sbs.vc.datapro.auth.repository.SubscriptionMasterRepository;
import com.sbs.vc.datapro.auth.repository.UserActivityRepository;
import com.sbs.vc.datapro.auth.repository.UserCredentialRepository;
import com.sbs.vc.datapro.auth.repository.UserRegistrationRepository;
import com.sbs.vc.datapro.auth.repository.UserRepository;
import com.sbs.vc.datapro.auth.security.JwtTokenProvider;
import com.sbs.vc.datapro.email.ClientEmails;
import com.sbs.vc.datapro.exceptions.ImageNotAvailable;
import com.sbs.vc.datapro.exceptions.InvalidTokenException;
import com.sbs.vc.datapro.exceptions.ProcessFailedException;

@Service
public class UserMaintenenceService {
	
	public static final Logger logger = LoggerFactory.getLogger(UserMaintenenceService.class);

	
     private static final String INVALIDTOKEN = "invalidtoken";

	private static final String ACCOUNTACTIVATE = "accountactivate";

	private static final String ALREADYACTIVATED = "alreadyactivated";
	
    
	@Autowired
	JwtTokenProvider tokenProvider;
	    

	@Autowired
	UserRepository userRepository;
	
	@Autowired
    RoleRepository roleRepository;
	
	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	UserCredentialRepository userCredentialRepository;
	
	@Autowired
	ClientEmails clientMail;
	
	
	@Autowired
	SubscriptionMasterRepository subscriptionMasterRepository;
	
	
	@Autowired
	UserRegistrationRepository userRegistrationRepository;
	
	
	@Autowired
	IssueTokenRepository issueTokenRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Autowired
	UserActivityRepository userActivityRepository;
	 /**
	  * This method used to register the user and send email
	  * @param userRegistration
	  * @param country
	  * @return
	  * @throws ProcessFailedException
	  */
	 public UserRegistration register(UserRegistration userRegistration,String country) throws ProcessFailedException{
	    	String encodePassword=passwordEncoder.encode(userRegistration.getPassCode());
	    	System.out.println("encodePassword>>"+encodePassword);
		 	userRegistration.setPassCode(encodePassword);
	    	String activationtoken=URLEncodeDecode.encode(AESEncryption.encryptText(userRegistration.getUsername()));
	    	userRegistration.setAccActivationToken(activationtoken);
	    	userRegistrationRepository.save(userRegistration);
	    	String pLanguage=country.equalsIgnoreCase("Israel")?"heb":"en";
			
	    	accountCreated(userRegistration,pLanguage);
//			if(country.equalsIgnoreCase("Israel")) {
//	    		clientMail.sendStaticContent(18L, userRegistration.getUsername());
//	    	}else {
//	    		clientMail.sendStaticContent(13L, userRegistration.getUsername());
//	    	}
	    	return userRegistration;
	  } 
	 
   // Old One
	public UserRegistration userRegistration(UserRegistration user,String country) throws ProcessFailedException  {
		String activationtoken=URLEncodeDecode.encode(AESEncryption.encryptText(user.getUsername()));
		user.setAccActivationToken(activationtoken);
		userRegistrationRepository.save(user);
		clientMail.registrationMail(user,activationtoken,country);
		return user;
	}
	

	public User accountCreated(UserRegistration userRegistration,String pLanguage) throws ProcessFailedException  {
		User user=new User();
		user.setUsername(userRegistration.getUsername());
		user.setFirstName(userRegistration.getFirstName());
		user.setLastName(userRegistration.getLastName());
		user.setPassword(userRegistration.getPassCode());
		user.setPreferedLanguage(pLanguage);
		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
		user.setRoles(Collections.singleton(userRole));
		Status status=statusRepository.findById(UserStatus.ACTIVE.getStatusID()).get();
		user.setStatus(status);
		userRepository.save(user);
		
		return user;
	}
	
	public boolean accountDeleted(String username) throws ProcessFailedException  {
		User user=userRepository.findByUsername(username).get();
		userRepository.delete(user);
		return true;
	}
	
		
	public User getUserDetail(String username) throws ProcessFailedException  {
		return userRepository.findByUsername(username).get();
	}

	public User update(User user) {
		return userRepository.save(user);
	}
	/**
	 * This method validate the account status and token shared with the customer
	 * Account will be activated if user is inactive
	 * @param userName
	 * @param token
	 * @return
	 * @throws ProcessFailedException
	 */
	public String activateUser(String userName,long Id,String token,String country) throws ProcessFailedException{
		System.out.println("Token:>UI"+token);
		
		if(!userRepository.findByUsername(userName).isPresent()) {
			UserRegistration registeredUser=userRegistrationRepository.findById(Id).get();
			System.out.println("Token:>DB"+registeredUser.getAccActivationToken());
			String encodeToken=URLEncodeDecode.decode(registeredUser.getAccActivationToken());
			encodeToken=encodeToken.replaceAll(" ", "+");
			System.out.println("Encoded:>DB           :"+encodeToken);
			System.out.println("Encoded:>Token Compare:"+token);
			if(!encodeToken.equalsIgnoreCase(token)) {
				return INVALIDTOKEN;
			}
			String pLanguage=country.equalsIgnoreCase("Israel")?"heb":"en";
			User user = accountCreated(registeredUser,pLanguage);
			clientMail.activationMail(user,country);
			return ACCOUNTACTIVATE;
		}else {
			return ALREADYACTIVATED;
		}
	}
	/**
	 * 
	 * @param userDTO
	 * @return
	 */
	/**
	 * This method used to change password
	 * @param userDTO
	 * @param encryptedToken
	 * @param username
	 * @return
	 * @throws InvalidTokenException
	 */
	public boolean changePassword(User userDTO, String encryptedToken, String username) throws InvalidTokenException {
		
	IssueToken issueToken = issueTokenRepository.findByToken(encryptedToken);
		
		if (issueToken == null) {
			throw new InvalidTokenException("Invalid Token : Not Issued");
		}
		
		if (!issueToken.getStatus().equalsIgnoreCase("Active")) {
			throw new InvalidTokenException("Invalid Token : Allready Used");
		}

		if (!issueToken.getEmailId().equalsIgnoreCase(username)) {
			throw new InvalidTokenException("Invalid Token: Not Issue for specified emails.");
		}
		if (!TokenIssuePurpose.CHANGE_PASSWORD.getPurpose().equalsIgnoreCase(issueToken.getPurpose())) {
			throw new InvalidTokenException("Invalid Token : Token Issued but different purpose");
		}
		try {
			User userDommain = userRepository.findByUsername(issueToken.getEmailId()).get();
			if (userDommain == null) {
				throw new InvalidTokenException("Invalid Token : Token issue for different user");
			}
			System.out.println("New Password:"+userDTO.getPassCode());
			userDommain.setPassword(passwordEncoder.encode(userDTO.getPassCode()));
			userDommain.setPassCode(passwordEncoder.encode(userDTO.getPassCode()));
			userRepository.save(userDommain);
			clientMail.changePasswordMail(userDommain);
			issueToken.setStatus("InActive");
			issueTokenRepository.save(issueToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean changePassword(User userDTO,String encryptedToken) throws InvalidTokenException{
		IssueToken issueToken= issueTokenRepository.findByToken(encryptedToken);
		if(issueToken==null) {
			throw new InvalidTokenException("Invalid Token : Null");
		}
		if(!TokenIssuePurpose.CHANGE_PASSWORD.getPurpose().equalsIgnoreCase(issueToken.getPurpose())){
			throw new InvalidTokenException("Invalid Token : Token Issued but different purpose");
		}
		User userDommain=userRepository.findByUsername(issueToken.getEmailId()).get();
		if(userDommain==null) {
			throw new InvalidTokenException("Invalid Token : Token issue for different user");
		}
		userDommain.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userRepository.save(userDommain);
		clientMail.changePasswordMail(userDommain);
		return true;
	}
	
	
	/**
	 * 
	 * @param userDTO
	 * @return
	 */
	public boolean changePassword(User userDTO) throws ProcessFailedException{
		User userDommain=userRepository.findByUsername(userDTO.getUsername()).get();
		System.out.println(passwordEncoder.matches(userDTO.getOldPassword(), userDommain.getPassword()));
		if(!passwordEncoder.matches(userDTO.getOldPassword(), userDommain.getPassword())) {
			throw new ProcessFailedException("Old Passwword does not match !!");
		}
		userDommain.setPassword(passwordEncoder.encode(userDTO.getNewPassword()));
		userRepository.save(userDommain);
		clientMail.changePasswordMail(userDommain);
		return true;
	}
	/**
	 * Reset password
	 * @param userName
	 * @return
	 */
	public boolean resetPassword(String userName) throws ProcessFailedException {
		User user=userRepository.findByUsername(userName).get();
		if(user==null) {
			throw new ProcessFailedException("Userid is not registered for user "+userName);
		}
		
		//Generate the password
		String genPassword=PasswordGenerator.geek_Password(8).toString();
		String activationtoken=AESEncryption.encryptText(user.getUsername()+genPassword);
		
		IssueToken issueToken=new IssueToken();
		issueToken.setEmailId(user.getUsername());
		issueToken.setPurpose(TokenIssuePurpose.CHANGE_PASSWORD.getPurpose());
		issueToken.setStatus("Active");
		issueToken.setToken(activationtoken);
		issueToken.setExpireDate(new Timestamp(Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()));
		
		issueTokenRepository.save(issueToken);
		user.setAccActivationToken(activationtoken);
		clientMail.resetPasswordMail(user);
		return true;
	}
	
	
	public boolean editProfile(User userDTO) throws ProcessFailedException {
		User user=userRepository.findByUsername(userDTO.getUsername()).get();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setMobileNumber(userDTO.getMobileNumber());
		user.setPreferedLanguage(userDTO.getPreferedLanguage());
		userRepository.save(user);
		return true;
	}
	

	public User find(String userName) {
		return userRepository.findByUsername(userName).get();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
	
	
	public User find(Long id) {
		return userRepository.findById(id).get();
	}
	
	


	public boolean changePasswordReq(long id, String token) throws InvalidTokenException {
		User user=userRepository.findById(id).get();
		/*if(!(AESEncryption.encryptText(user.getUsername()).equalsIgnoreCase(token.replaceAll(" ", "+")))) {
			return false;
		}*/
		try {
			if(!(AESEncryption.decryptText(token.replaceAll(" ", "+")).contains(user.getUsername()))) {
				return false;
			}
		}catch(Exception e) {
			throw new InvalidTokenException("Invalid Token :");
		}
		return true;
	}
	
	@Autowired
	ImageRepository imageRepository;
	
	public void saveUploadedFiles(String username,List<MultipartFile> files) throws IOException {
		ImageModel imageModel=imageRepository.findOneByUsername(username);
		if(imageModel==null) {
			imageModel=new ImageModel();
		}
		
        for (MultipartFile file : files) {
        	byte[] bytes = file.getBytes();
            imageModel.setPic(bytes);
            imageModel.setName(file.getOriginalFilename());
            imageModel.setType(file.getContentType());
            imageModel.setUsername(username);
            imageRepository.save(imageModel);
        }

    }
	
	
	public ImageModel getImage(String username) throws ImageNotAvailable {
		ImageModel image=imageRepository.findOneByUsername(username);
		if(image==null || image.getId() == null ) {
			throw new ImageNotAvailable("Image is not Available");
		}
		return image;

    }
	
	public void userActivity(String message,String userId,HttpServletRequest request  ) {
		UserActivity userActivity = new UserActivity();
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    userActivity.setActivity_Type(message);
	    userActivity.setUserName(userId);
	    userActivity.setAction_Time(timestamp);
	    userActivity.setIpAddress(request.getRemoteHost());
	    userActivity.setUserCreated("System");
		userActivityRepository.save(userActivity);
	}

	/*
	 * Delete account
	 */
	public void deleteAccount(String username) {
		User user=userRepository.findByUsername(username).get();
		Status status=statusRepository.findById(UserStatus.INACTIVE.getStatusID()).get();
		user.setStatus(status);
		user.setUsername(username+"_del");
		userRepository.save(user);
		
	}
	
	public void sendRegistrationGreetingMail() {
	 send3DaysGreetingMail();
	 send7DaysGreetingMail();
	 send10DaysGreetingMail();
	 send13DaysGreetingMail();
	 send1MonthGreetingMail();
	}
	
	public void send3DaysGreetingMail() {
		Date  from=DateUtil.getBackDate(3);
		Date  to=DateUtil.getBackDate(2);
		List<User> usersList=userRepository.getQualifiedUsers(new Timestamp(from.getTime()),new Timestamp(to.getTime()));
		System.out.println(usersList);
		if(usersList !=null && usersList.size() >0) {
			String emails=getEmailList(usersList,"en");
			logger.info("3 Days ENG Emails:"+emails);
			if(emails!=null)
			clientMail.sendGroupEmail(14L, emails);
			emails=getEmailList(usersList,"heb");
			logger.info("3 Days HEB Emails:"+emails);
			if(emails!=null)
			clientMail.sendGroupEmail(19L, emails);
		}
		
				
	}
	public void send7DaysGreetingMail() {
		Date  from=DateUtil.getBackDate(7);
		Date  to=DateUtil.getBackDate(6);
		List<User> usersList=userRepository.getQualifiedUsers(new Timestamp(from.getTime()),new Timestamp(to.getTime()));
		System.out.println(usersList);
		if(usersList !=null && usersList.size() >0) {
			String emails=getEmailList(usersList,"en");
			logger.info("7 Days ENG Emails:"+emails);
			if(emails!=null)
			clientMail.sendGroupEmail(15L, emails);
			emails=getEmailList(usersList,"heb");
			logger.info("7 Days HEB Emails:"+emails);
			if(emails!=null)
			clientMail.sendGroupEmail(20L, emails);
		}
			
	}
	
	public void send10DaysGreetingMail() {
		Date  from=DateUtil.getBackDate(10);
		Date  to=DateUtil.getBackDate(9);
		List<User> usersList=userRepository.getQualifiedUsers(new Timestamp(from.getTime()),new Timestamp(to.getTime()));
		System.out.println(usersList);
		if(usersList !=null && usersList.size() >0) {
			String emails=getEmailList(usersList,"en");
			logger.info("10 Days ENG Emails:"+emails);
			clientMail.sendGroupEmail(16L, emails);
			if(emails!=null)
			emails=getEmailList(usersList,"heb");
			logger.info("10 Days HEB Emails:"+emails);
			if(emails!=null)
			clientMail.sendGroupEmail(21L, emails);
		}
		
				
	}
	
	public void send13DaysGreetingMail() {
		Date  from=DateUtil.getBackDate(13);
		Date  to=DateUtil.getBackDate(12);
		List<User> usersList=userRepository.getQualifiedUsers(new Timestamp(from.getTime()),new Timestamp(to.getTime()));
		System.out.println(usersList);
		if(usersList !=null && usersList.size() >0) {
			// English Email
			String emails=getEmailList(usersList,"en");
			logger.info("13 Days ENG Emails:"+emails);
			if(emails!=null)
			clientMail.sendGroupEmail(17L, emails);
			emails=getEmailList(usersList,"heb");
			logger.info("13 Days ENG Emails:"+emails);
			if(emails!=null)
			clientMail.sendGroupEmail(22L, emails);
		}
				
	}
	
	
	public void send1MonthGreetingMail() {
		Date  from=DateUtil.getBackDate(30);
		Date  to=DateUtil.getBackDate(29);
		List<User> usersList=userRepository.getQualifiedUsers(new Timestamp(from.getTime()),new Timestamp(to.getTime()));
		if(usersList !=null && usersList.size() >0) {
			// English Email
			String emails=getEmailList(usersList,"en");
			logger.info("Monthly ENG Emails:"+emails);
			if(emails!=null)
			clientMail.sendGroupEmail(23L, emails);
			emails=getEmailList(usersList,"heb");
			logger.info("Monthly HEB Emails:"+emails);
			if(emails!=null)
			clientMail.sendGroupEmail(24L, emails);
		}
				
	}
	
	public String getEmailList(List<User> users,String pLanguage) {
		String emails=null;
		String ppLung=null;
		for(User user:users) {
			ppLung=user.getPreferedLanguage()==null?"en":user.getPreferedLanguage();
			if(pLanguage.equals(ppLung)){
				if(emails==null) {
					emails=user.getUsername();
				}else {
					emails+=","+user.getUsername();
				}
			}
		}
		return emails;
	}
}
