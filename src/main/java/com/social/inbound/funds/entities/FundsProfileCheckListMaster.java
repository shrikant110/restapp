package com.social.inbound.funds.entities;

import java.sql.Timestamp;

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

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 *    FundsProfileCheckListMaster
CREATE Table FUNDS_profiles_CHECKLIST_MASTER (	
ID serial PRIMARY KEY,
profile_ID BIGINT,
LANGUAGE VARCHAR,	
Date_Modified 	TIMESTAMPTZ,
DESCRIPTION VARCHAR	(500) );
 * @author shrikant.kushwaha
 *
 */

@Entity
@Table(name="FUNDS_PROFILES_CHECKLIST_MASTER")
@Scope("session")
public class FundsProfileCheckListMaster {
	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funds_profiles_generator")
	@SequenceGenerator(name = "funds_profiles_generator", sequenceName = "funds_profiles_checklist_master_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	
	@Column(name="PROFILE_ID")
	@JsonProperty
	@Getter
	@Setter
	private Short profileId;
	
	
	@Getter
	@Setter
	@Column(name="LANGUAGE")
	private String language;
	
	@Getter
	@Setter
	@Column(name="DESCRIPTION")
	private String description;
	
	
	@Getter
	@Setter
	@Column(name = "USER_CREATED",columnDefinition="TEXT")
	private String userCreated;
	
	@Getter
	@Setter
	@CreationTimestamp
	@Column(name = "DATE_CREATED")
	private Timestamp dateCreated;
	
	@Getter
	@Setter
	@Column(name = "USER_MODIFIED")
	private String userModified;
	
	@Getter
	@Setter
	@Column(name = "DATE_MODIFIED")
	@UpdateTimestamp
	private Timestamp dateModifiled;

}
