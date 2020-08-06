package com.sbs.vc.datapro.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserRegistration {
	
	@JsonProperty("UserId")
	private String userId;
	
	@JsonProperty("FirstName")
	private String firstName;
	
	@JsonProperty("LastName")
	private String lastName;
	
	@JsonProperty("EmailId")
	private String emailID;

	@JsonProperty("Password")
	private String password;
	
	@JsonProperty("Role")
	private String role;



}
