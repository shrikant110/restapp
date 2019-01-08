package com.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.config.exception.ProcessFailedException;
import com.social.dao.RoleRepository;
import com.social.dao.StatusRepository;
import com.social.dao.UserCredentialRepository;
import com.social.dao.UserRepository;
import com.social.entities.Roles;
import com.social.entities.Status;
import com.social.entities.User;
import com.social.entities.UserProfileDetails;
import com.social.mail.ClientEmails;
import com.social.util.AESEncryption;
import com.social.util.PasswordGenerator;

@Service
public class UserService {

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

	public User save(User user) throws ProcessFailedException  {
		String activationtoken=AESEncryption.encryptText(user.getUsername());
		Roles role=roleRepository.findOne(1L);
		Status status=statusRepository.findOne(2L);
		UserProfileDetails userCredentialDetails=new UserProfileDetails();
		userCredentialDetails.setPassword(user.getPass());
		userCredentialRepository.save(userCredentialDetails);
		user.setUserCredentialDetails(userCredentialDetails);
		user.setRole(role);
		user.setStatus(status);
		user.setAccActivationToken(activationtoken);
		userRepository.save(user);
		clientMail.registrationMail(user,activationtoken);
		return user;
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
	public boolean activateUser(String userName,String token) throws ProcessFailedException{
		User user=userRepository.findOneByUsername(userName);
		if(UserStatus.ACTIVE.getStatusID()==user.getStatus().getStatusId()) {
			throw new ProcessFailedException("User is already activated");
		}else if(user.getAccActivationToken().equalsIgnoreCase(token.replaceAll(" ", "+"))) {
			Status status=statusRepository.findOne(UserStatus.ACTIVE.getStatusID());
			user.setStatus(status);
			userRepository.saveAndFlush(user);		  /* Activate user account */
			clientMail.activationMail(user);  /* Send activation mail */
			return true;
		}else {
			throw new ProcessFailedException("This is an invalid token");
		}
	}
	/**
	 * 
	 * @param userDTO
	 * @return
	 */
	public boolean changePassword(User userDTO) {
		User userDommain=userRepository.findOneByUsername(userDTO.getUsername());
		userDommain.getUserCredentialDetails().setPassword(userDTO.getPass());
		userRepository.save(userDommain);
		clientMail.changePasswordMail(userDommain);
		return true;
	}
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public boolean resetPassword(String userName) {
		User user=userRepository.findOneByUsername(userName);
		//Generate the password
		String genPassword=PasswordGenerator.geek_Password(8).toString();
		User userDommain=userRepository.findOneByUsername(userName);
		userDommain.getUserCredentialDetails().setPassword(genPassword);
		userRepository.save(userDommain);
		clientMail.resetPasswordMail(user);
		return true;
	}
	

	public User find(String userName) {
		return userRepository.findOneByUsername(userName);
	}

	public User find(Long id) {
		return userRepository.findOne(id);
	}
}
