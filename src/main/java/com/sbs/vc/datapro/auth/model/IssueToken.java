package com.sbs.vc.datapro.auth.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "issue_token")
@Scope("session")
public class IssueToken {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_token_id_seq")
	@SequenceGenerator(name = "issue_token_id_seq", sequenceName = "issue_token_id_seq", allocationSize = 50)
	@Column(name = "token_id")
	@JsonProperty("TokenId")
	private Long tokenId;
	
	@Column(name = "token")
	@JsonProperty("Token")
	private String  token;
	
	@Column(name = "user_name")
	@JsonProperty("EmailId")
	private String  emailId;

	@Column(name = "purpose")
	@JsonIgnore
	private String  purpose;
	
	@CreationTimestamp
	@Column(name = "date_created")
	private Timestamp dateCreated;
	
	@Column(name = "status")
	@JsonProperty("Status")
	private String  status;
	
	private Timestamp expireDate;
	
}


