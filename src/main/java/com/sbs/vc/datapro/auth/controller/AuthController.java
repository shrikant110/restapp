package com.sbs.vc.datapro.auth.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.sbs.vc.config.util.CommonUtils;
import com.sbs.vc.config.util.HttpStatusCodes;
import com.sbs.vc.config.util.ResponseMessageDTO;
import com.sbs.vc.datapro.auth.model.User;
import com.sbs.vc.datapro.auth.model.UserRegistration;
import com.sbs.vc.datapro.auth.payload.JwtAuthenticationResponse;
import com.sbs.vc.datapro.auth.payload.LoginRequest;
import com.sbs.vc.datapro.auth.repository.UserRepository;
import com.sbs.vc.datapro.auth.security.CurrentUser;
import com.sbs.vc.datapro.auth.security.JwtTokenProvider;
import com.sbs.vc.datapro.auth.security.UserPrincipal;
import com.sbs.vc.datapro.auth.service.UserMaintenenceService;
import com.sbs.vc.datapro.exceptions.AnonymousUserException;
import com.sbs.vc.datapro.exceptions.InvalidTokenException;
import com.sbs.vc.datapro.exceptions.ProcessFailedException;
import com.sbs.vc.file.controller.FileStorageService;

@PropertySources({ @PropertySource("classpath:application_env.properties") })
@RestController
@RequestMapping("/account")
public class AuthController {

	@Autowired
	Environment environment;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMaintenenceService userMaintenenceService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	FileStorageService fileStorageService;

	

	public static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/loginRequest")
	public ResponseMessageDTO authenticateUser(HttpServletRequest request) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getParameter("username"), request.getParameter("password")));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		boolean isRememberMe = request.getParameter("remember-me") != null? Boolean.parseBoolean(request.getParameter("remember-me")): false;
		String jwt = tokenProvider.generateToken(authentication, isRememberMe);
		Long tokenExpiration = tokenProvider.getTokenEpiration(isRememberMe);
		User user = userMaintenenceService.find(authentication.getName());
		JwtAuthenticationResponse jwtAuth = new JwtAuthenticationResponse(jwt, user, tokenExpiration);
		if (user.getStatus().getStatusId() == 1) {
			return CommonUtils.getSuccessMessage(HttpStatusCodes.LOGINSUCCESS.getCode(), "Login Successful.", jwtAuth);
		} else {
			return CommonUtils.getSuccessMessage(HttpStatusCodes.ACCOUNTINACTIVE.getCode(), "Account Inactive.", null);
		}
	}

	@PostMapping("/login")
	public ResponseMessageDTO authenticateUser(@Valid @RequestBody LoginRequest loginRequest,HttpServletRequest request) throws ProcessFailedException, IOException {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication, loginRequest.getRememberMe());

		Long tokenExpiration = tokenProvider.getTokenEpiration(loginRequest.getRememberMe());
		User user = userMaintenenceService.find(authentication.getName());
		JwtAuthenticationResponse jwtAuth = new JwtAuthenticationResponse(jwt, user, tokenExpiration);
		userMaintenenceService.userActivity("Login", user.getUserId(), request);
		if (user.getStatus().getStatusId() == 1) {
			return CommonUtils.getSuccessMessage(HttpStatusCodes.LOGINSUCCESS.getCode(), "Login Successful.", jwtAuth);
		} else {
			return CommonUtils.getSuccessMessage(HttpStatusCodes.ACCOUNTINACTIVE.getCode(), "Account Inactive.", null);
		}

	}
	
	@PostMapping("/createUser")
	public ResponseMessageDTO registerUser(@Valid @RequestBody UserRegistration signUpRequest,HttpServletRequest request) throws ProcessFailedException, IOException {
		if (userRepository.existsByUserId(signUpRequest.getUserId())) {
			logger.error("username Already exist " + signUpRequest.getUserId());
			return CommonUtils.getSuccessMessage(HttpStatus.CONFLICT.value(),
					"User Id  " + signUpRequest.getUserId() + " already exist", null);
		}
		User user=userMaintenenceService.accountCreated(signUpRequest);
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "User Created Successfully", user);
	}

	@CrossOrigin
	@RequestMapping(value = "/getloginUser", method = RequestMethod.GET)
	public ResponseMessageDTO loginUser(@CurrentUser UserPrincipal currentUser)	throws AnonymousUserException, ProcessFailedException {
		if (currentUser == null) {
			throw new AnonymousUserException("Anonymous User");
		} else {
			String userName = currentUser.getUsername();
			User user = userRepository.findByUserId(userName).get();
			return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "fetch successfully of active user", user);

		}

	}

	/**
	 * This method used to
	 * 
	 * @return
	 * @throws AnonymousUserException
	 * @throws ProcessFailedException
	 */
	@CrossOrigin
	@RequestMapping(value = "/isValidToken", method = RequestMethod.GET)
	public ResponseMessageDTO isValidToken(@CurrentUser UserPrincipal currentUser)throws AnonymousUserException, ProcessFailedException {
		if (currentUser == null) {
			throw new AnonymousUserException("Anonymous User");
		}
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Requested headers are valid", currentUser);
	}

	/**
	 * 
	 * @Logout Process
	 * @return
	 * @throws AnonymousUserException
	 * @throws ProcessFailedException
	 */
	@CrossOrigin
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseMessageDTO logout(@CurrentUser UserPrincipal currentUser)throws AnonymousUserException, ProcessFailedException {
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Logged out successfully.", null);
	}

	

	/**
	 * This method used to change the password
	 * 
	 * @param newUser
	 * @return
	 * @throws ProcessFailedException
	 * @throws AnonymousUserException
	 */
	@CrossOrigin
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseMessageDTO changePassword(@RequestBody User newUser, @CurrentUser UserPrincipal currentUser)
			throws ProcessFailedException, AnonymousUserException {
		String username = currentUser.getUsername();
		newUser.setUserId(username);
		userMaintenenceService.changePassword(newUser);
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Password has been changed successfully", null);
	}

	

	/**
	 * Change password without login
	 * 
	 * @param newUser
	 * @param request
	 * @return
	 * @throws InvalidTokenException
	 * @throws UnsupportedEncodingException
	 */
	@CrossOrigin
	@RequestMapping(value = "/changePasswordWOL", method = RequestMethod.POST)
	public ResponseMessageDTO changePasswordWOL(@RequestBody User newUser, HttpServletRequest request)
			throws InvalidTokenException, UnsupportedEncodingException {
		String encryptedToken = request.getHeader("token");
		String username = request.getHeader("username");
		System.out.println(encryptedToken);
		if (encryptedToken == null || encryptedToken.equalsIgnoreCase("")) {
			return CommonUtils.getSuccessMessage(HttpStatusCodes.TOKENINVALID.getCode(), "Token Required", null);
		} else {
			encryptedToken = URLDecoder.decode(encryptedToken, "UTF-8");
			encryptedToken = encryptedToken.replace(" ", "+");
			System.out.println("encryptedToken::>>" + encryptedToken);
			userMaintenenceService.changePassword(newUser, encryptedToken, username);
			return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Password has been changed successfully", null);
		}

	}

	/**
	 * Reset the User password
	 * 
	 * @param emailId
	 * @return
	 * @throws ProcessFailedException
	 */
	@CrossOrigin
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public ResponseMessageDTO resetPassword(@RequestParam(required = true) String emailId)
			throws ProcessFailedException {
		userMaintenenceService.resetPassword(emailId);
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(),
				"Password change link has been sent to registered email id.", null);
	}

	/**
	 * This method used to edit the user profile
	 * 
	 * @param userDetails
	 * @return
	 * @throws ProcessFailedException
	 */
	@CrossOrigin
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public ResponseMessageDTO resetPassword(@RequestBody User userDetails) throws ProcessFailedException {
		boolean isUpdated = userMaintenenceService.editProfile(userDetails);
		if (isUpdated) {
			return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Profile is updated successfully.", null);
		} else {
			return CommonUtils.getSuccessMessage(HttpStatusCodes.ERROR.getCode(), "Profile is not updated.", null);
		}

	}

	/**
	 * This method used to edit the user profile
	 * 
	 * @param userDetails
	 * @return
	 * @throws ProcessFailedException
	 * @throws GeoIp2Exception 
	 * @throws IOException 
	 */
	@CrossOrigin
	@RequestMapping(value = "/deleteProfile", method = RequestMethod.POST)
	public ResponseMessageDTO deleteAccount(@CurrentUser UserPrincipal currentUser, HttpServletRequest request) throws ProcessFailedException, IOException {
		String username = currentUser.getUsername();
		userMaintenenceService.accountDeleted(username);
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Account has been successfully deleted", null);

	}

	
	
	@GetMapping("/deletedUser/{username}")
	public ResponseMessageDTO deletedUser(@PathVariable String username) throws ProcessFailedException {
		userMaintenenceService.accountDeleted(username);
		return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Registration Visitor", "");
	}
	
	@CrossOrigin
	@RequestMapping(value = "/activeuser", method = RequestMethod.GET)
	public ResponseMessageDTO getLogginUser(@CurrentUser UserPrincipal currentUser, HttpServletRequest request) throws ProcessFailedException, IOException {
		String username=null;
		try {
			username = currentUser.getUsername();
			return CommonUtils.getSuccessMessage(HttpStatus.OK.value(), "Active Session", username);
		} catch (Exception e) {
			return CommonUtils.getSuccessMessage(HttpStatus.FORBIDDEN.value(), "Inactive Session", username);
		}
		

	}
	
	
	
}
