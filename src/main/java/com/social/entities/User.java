package com.social.entities;

import java.io.Serializable;
import java.sql.Timestamp;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
@Table(name = "USER_INFO")
@Scope("session")
@Getter
@Setter
public class User implements UserDetails, Serializable {
	public static enum Role {
		ROLE_USER
	}

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userid_generator")
	@SequenceGenerator(name = "userid_generator", sequenceName = "userid_seq", allocationSize = 50)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(unique = true)
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String role;
	private String fullName;

	@Column(name = "mobile_no")
	private String mobileNumber;
	
	@Column(name = "date_created")
	@CreationTimestamp
	@Getter
	@Setter
	private Timestamp dateCreated;

	@Column(name = "date_updated")
	@UpdateTimestamp
	@Getter
	@Setter
	private Timestamp dateUpdated;

	public User() {

	}

	public User(String username, String password, String fullName, String mobile_no) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.mobileNumber = mobileNumber;
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
