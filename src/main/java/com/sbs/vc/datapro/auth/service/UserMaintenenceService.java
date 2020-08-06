package com.sbs.vc.datapro.auth.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sbs.vc.config.util.AESEncryption;
import com.sbs.vc.config.util.PasswordGenerator;
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
import com.sbs.vc.datapro.auth.repository.UserActivityRepository;
import com.sbs.vc.datapro.auth.repository.UserCredentialRepository;
import com.sbs.vc.datapro.auth.repository.UserRepository;
import com.sbs.vc.datapro.auth.security.JwtTokenProvider;
import com.sbs.vc.datapro.email.ClientEmails;
import com.sbs.vc.datapro.exceptions.ImageNotAvailable;
import com.sbs.vc.datapro.exceptions.InvalidTokenException;
import com.sbs.vc.datapro.exceptions.ProcessFailedException;

@Service
public class UserMaintenenceService {
	
	public static final Logger logger = LoggerFactory.getLogger(UserMaintenenceService.class);

    
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
	IssueTokenRepository issueTokenRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Autowired
	UserActivityRepository userActivityRepository;

	public User accountCreated(UserRegistration userRegistration) throws ProcessFailedException  {
		String encodePassword=passwordEncoder.encode(userRegistration.getPassword());
		User user=new User();
		user.setUserId(userRegistration.getUserId());
		user.setFirstName(userRegistration.getFirstName());
		user.setLastName(userRegistration.getLastName());
		user.setPassword(encodePassword);
		user.setEmail(userRegistration.getEmailID());
		user.setPreferedLanguage("en");
		Role userRole =null;
		if(userRegistration.getRole().equals("ADMIN")) {
				userRole=roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new AppException("User Role not set."));
		}
		
		if(userRegistration.getRole().equals("USER")) {
			userRole=roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User Role not set."));
		}
		user.setRoles(Collections.singleton(userRole));
		Status status=statusRepository.findById(UserStatus.ACTIVE.getStatusID()).get();
		user.setStatus(status);
		userRepository.save(user);
		clientMail.changePasswordMail(user);
		return user;
	}
	
	public boolean accountDeleted(String username) throws ProcessFailedException  {
		User user=userRepository.findByUserId(username).get();
		userRepository.delete(user);
		return true;
	}
	
		
	public User getUserDetail(String username) throws ProcessFailedException  {
		return userRepository.findByUserId(username).get();
	}

	public User update(User user) {
		return userRepository.save(user);
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
			User userDommain = userRepository.findByUserId(issueToken.getEmailId()).get();
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
		User userDommain=userRepository.findByUserId(issueToken.getEmailId()).get();
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
		User userDommain=userRepository.findByUserId(userDTO.getUserId()).get();
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
		User user=userRepository.findByUserId(userName).get();
		if(user==null) {
			throw new ProcessFailedException("Userid is not registered for user "+userName);
		}
		
		String genPassword=PasswordGenerator.geek_Password(8).toString();
		String activationtoken=AESEncryption.encryptText(user.getUserId()+genPassword);
		
		IssueToken issueToken=new IssueToken();
		issueToken.setEmailId(user.getEmail());
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
		User user=userRepository.findByUserId(userDTO.getUserId()).get();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setMobileNumber(userDTO.getMobileNumber());
		user.setPreferedLanguage(userDTO.getPreferedLanguage());
		userRepository.save(user);
		return true;
	}
	

	public User find(String userName) {
		return userRepository.findByUserId(userName).get();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
	
	
	public User find(Long id) {
		return userRepository.findById(id).get();
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
		User user=userRepository.findByUserId(username).get();
		Status status=statusRepository.findById(UserStatus.INACTIVE.getStatusID()).get();
		user.setStatus(status);
		user.setUserId(username+"_del");
		userRepository.save(user);
		
	}
	
	
}
