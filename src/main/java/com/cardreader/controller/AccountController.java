package com.cardreader.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cardreader.config.exception.ProcessFailedException;
import com.cardreader.entities.User;
import com.cardreader.services.UserService;
import com.cardreader.util.CustomErrorType;

/**
 * @author shrikant.kushwaha
 * This class is responsible for handle the user authentication and maintain the user login 
 * logout, change password, reset password and activate the user account
 *
 */
@RestController
@RequestMapping("account")
public class AccountController {

	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private UserService userService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User newUser) throws ProcessFailedException {
	    if (userService.find(newUser.getUsername()) != null) { 
			logger.error("username Already exist " + newUser.getUsername());
			return new ResponseEntity(new CustomErrorType("user with username " + newUser.getUsername() + "already exist"),	HttpStatus.CONFLICT);
		}
	    logger.info("username is successfully registered " + newUser.getUsername());
		return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
	}


	/*@CrossOrigin
	@RequestMapping("/login")
	public Principal user(Principal principal) {
		logger.info("user logged "+principal);
		
		//SecurityContextHolder.getContext().setAuthentication(principal);
		return principal;
	}
	*/
	
	
	@CrossOrigin
	@RequestMapping(value = "/activateUser", method = RequestMethod.GET)
	public ResponseEntity<?> activateUser(@RequestParam(required = true) String emailId,@RequestParam(required = true) String token) throws ProcessFailedException{
		//userService.activateUser(emailId,token);
		return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/changePassord", method = RequestMethod.GET)
	public ResponseEntity<?> changePassword(@RequestBody User newUser) {
		userService.changePassword(newUser);
		return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication(), HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public ResponseEntity<?> resetPassword(@RequestParam(required = true) String emailId) {
		userService.resetPassword(emailId);
		return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication(), HttpStatus.OK);
	}
	
		
	
}
