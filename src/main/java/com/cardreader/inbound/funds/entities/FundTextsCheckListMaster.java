package com.cardreader.inbound.funds.entities;

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

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author shrikant.kushwaha CREATE Table FUNDS_TEXTS_CHECKLIST_MASTER ( ID
 *         serial PRIMARY KEY, TEXT_ID BIGINT, LANGUAGE VARCHAR (2), FUND_TYPE
 *         VARCHAR (100), DESCRIPTION VARCHAR (500) );
 *
 */
@Entity
@Table(name = "FUNDS_TEXTS_CHECKLIST_MASTER")
@Scope("session")

@Setter
public class FundTextsCheckListMaster {
	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funds_texts_generator")
	@SequenceGenerator(name = "funds_texts_generator", sequenceName = "funds_texts_checklist_master_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "TEXT_ID")
	private Short textId;

	@Column(name = "LANGUAGE")
	private String language;

	@Column(name = "FUND_TYPE")
	private String fundType;

	@Column(name = "DESCRIPTION",columnDefinition="TEXT")
	private String description;

	@Column(name = "USER_CREATED")
	private String userCreated;
	
	@CreationTimestamp
	@Column(name = "DATE_CREATED")
	private Timestamp dateCreated;

	@Column(name = "USER_MODIFIED")
	private String userModified;

	@Column(name = "DATE_MODIFIED")
	@UpdateTimestamp
	private Timestamp dateModifiled;
}
