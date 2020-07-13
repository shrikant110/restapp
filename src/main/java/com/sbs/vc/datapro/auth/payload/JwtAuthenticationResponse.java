package com.sbs.vc.datapro.auth.payload;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbs.vc.datapro.auth.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    
    @JsonProperty("LoginUser")
    private User user;
    
    @JsonProperty("ExpirationTime")
    private Long expirationTime;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

	public JwtAuthenticationResponse(String accessToken, Long expirationTime) {
		super();
		this.accessToken = accessToken;
		this.expirationTime = expirationTime;
	}

	public JwtAuthenticationResponse(String accessToken, User user, Long expirationTime) {
		super();
		this.accessToken = accessToken;
		this.user = user;
		this.expirationTime = expirationTime;
	}
    
    

   
}
