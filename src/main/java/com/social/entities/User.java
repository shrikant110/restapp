package com.social.entities;


import java.io.Serializable;

/*******************************************************************************
 * 2017, this is the user entity class ,
 * this class implements users details of the spring security framework
 *******************************************************************************/

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="USER_INFO")
@Scope("session")
public  class User implements UserDetails,Serializable{
	public static enum Role{ ROLE_USER }
	
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userid_generator")
	@SequenceGenerator(name="userid_generator", sequenceName = "userid_seq", allocationSize=50)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id ;
	
	@Getter
	@Setter
	@Column(unique = true)
	private String username ;
	@Getter
	@Setter
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password ;
	@Getter
	@Setter
	private String  role;
	@Getter
	@Setter
    private String fullName;
	
	@Getter
	@Setter
    private String mobile_no;
	
	@Getter
	@Setter
    private String date_created;

    public User(){
    	
    }
    
    public User(String username,String password,String fullName,String mobile_no){
    	this.username=username;
    	this.password= password;
    	this.fullName=fullName;
    	this.mobile_no=mobile_no;
    }
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

}
