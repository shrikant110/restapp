package com.social.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_CREDENTIAL_DETAILS")
@Scope("session")
@Getter
@Setter
public class UserCredentialDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credentials_id_seq")
	@SequenceGenerator(name = "credentials_id_seq", sequenceName = "credentials_id_seq", allocationSize = 50)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "PASSWORD")
	private String  password;
	
	@Column(name = "DATE_MODIFIED")
	@UpdateTimestamp
	private Timestamp dateModifiled;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;


}
