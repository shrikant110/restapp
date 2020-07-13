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

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


/**
 * 	@author shrikant.kushwaha
 *      FundItemsCheckListMaster
		CREATE Table FUNDS_ITEMS_CHECKLIST_MASTER (	
		ID  serial PRIMARY KEY,
		ITEM_TAG VARCHAR	(50),
		LANGUAGE VARCHAR	(2),
		Date_Modified 	TIMESTAMPTZ,
		DESCRIPTION VARCHAR	(500) );
 */

@Entity
@Table(name="FUNDS_ITEMS_CHECKLIST_MASTER")
@Scope("session")

public class FundItemsCheckListMaster {
	
	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funds_items_generator")
	@SequenceGenerator(name = "funds_items_generator", sequenceName = "funds_items_checklist_master_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	
	@Column(name="ITEM_TAG")
	@JsonProperty
	@Getter
	@Setter
	private String itemTag;
	

	
	@Getter
	@Setter
	@Column(name="LANGUAGE")
	private String language;
	
	@Getter
	@Setter
	@Column(name="DESCRIPTION",columnDefinition="TEXT")
	private String description;
	
	
	@Getter
	@Setter
	@Column(name = "USER_CREATED")
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


