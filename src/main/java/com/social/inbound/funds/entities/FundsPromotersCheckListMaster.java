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
 * 
 * @author shrikant.kushwaha
 *
 *	 
     FundsPromotersCheckListMaster
		CREATE Table FUNDS_promoters_CHECKLIST_MASTER ( 
		ID serial PRIMARY KEY,
		promoter_ID BIGINT,	
		DATE_MODIFIED 	TIMESTAMPTZ,
		DESCRIPTION VARCHAR	(500) );
 */

@Entity
@Table(name="FUNDS_PROMOTERS_CHECKLIST_MASTER")
@Scope("session")
public class FundsPromotersCheckListMaster {
	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funds_promoters_generator")
	@SequenceGenerator(name = "funds_promoters_generator", sequenceName = "funds_promoters_checklist_master_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	
	@Column(name="PROMOTER_ID")
	@JsonProperty
	@Getter
	@Setter
	private Integer promoterId;
	
	
	
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
