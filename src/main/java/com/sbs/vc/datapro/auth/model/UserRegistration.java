package com.sbs.vc.datapro.auth.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_registration")
public class UserRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "user_name")
	@JsonProperty("UserName")
	private String username;
	
	@Column(name = "FIRST_NAME")
	@JsonProperty("FirstName")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	@JsonProperty("LastName")
	private String lastName;

	@JsonIgnore
	@Column(name = "ACCOUNT_ACTIVATION_TOKEN")
	private String accActivationToken;
	
	
	@JsonProperty("PassCode")
	@Column(name = "PASSWORD")
	private String passCode;

	@JsonIgnore
	@Column(name = "DATE_CREATED")
	@CreationTimestamp
	private Timestamp dateCreated;

}
