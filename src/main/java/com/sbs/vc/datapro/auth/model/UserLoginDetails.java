package com.sbs.vc.datapro.auth.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_LOGIN_DETAILS")
@Scope("session")
@Getter
@Setter
public class UserLoginDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_details_id_seq")
	@SequenceGenerator(name = "login_details_id_seq", sequenceName = "login_details_id_seq", allocationSize = 50)
	@Column(name = "ID")
	@JsonProperty
	private Long id;
	
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "LOGIN_DATE")
	private Timestamp loginDate;
	
	@Column(name = "REMOTE_IP")
	private String  remoteIP;
	
}
