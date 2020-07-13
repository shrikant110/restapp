package com.sbs.vc.datapro.auth.payload;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    @JsonProperty("UserName")
    private String usernameOrEmail;

    @NotBlank
    @JsonProperty("Password")
    private String password;
    
    
    @JsonProperty("remember-me")
    private Boolean rememberMe;


	@Override
	public String toString() {
		return "LoginRequest [usernameOrEmail=" + usernameOrEmail + ", password=" + password + ", rememberMe="
				+ rememberMe + "]";
	}

    
}
