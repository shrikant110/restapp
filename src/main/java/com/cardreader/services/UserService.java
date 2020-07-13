package com.cardreader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardreader.config.exception.ProcessFailedException;
import com.cardreader.config.util.AESEncryption;
import com.cardreader.config.util.PasswordGenerator;
import com.cardreader.dao.RoleRepository;
import com.cardreader.dao.StatusRepository;
import com.cardreader.dao.UserCredentialRepository;
import com.cardreader.dao.UserRepository;
import com.cardreader.entities.Roles;
import com.cardreader.entities.Status;
import com.cardreader.entities.User;
import com.cardreader.entities.UserProfileDetails;
import com.cardreader.mail.ClientEmails;

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
	//	clientMail.registrationMail(user,activationtoken);
		return user;
	}

	public User update(User user) {
		return userRepository.save(user);
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
		//clientMail.changePasswordMail(userDommain);
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
		//clientMail.resetPasswordMail(user);
		return true;
	}
	

	public User find(String userName) {
		return userRepository.findOneByUsername(userName);
	}

	public User find(Long id) {
		return userRepository.findOne(id);
	}
}
