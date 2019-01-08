package com.social.inbound.stocts.entities;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author shrikant.kushwaha CREATE TABLE AUR_TextID_MASTER( Text_Id BIGINT,
 *         Text_Short_Desc Varchar(500), Text_Long_Desc Varchar(500),
 *         Language_Type Varchar(10), User_Created Varchar(50), Date_Created
 *         TIMESTAMPTZ, User_Modified Varchar(50), Date_Modified TIMESTAMPTZ,
 *         Status Varchar(10) );
 */
@Entity
@Table(name = "AUR_TEXTID_MASTER")
@Scope("session")
@Getter
@Setter
public class AurTextIdMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aur_sector_master_id_seq")
	@SequenceGenerator(name = "aur_sector_master_id_seq", sequenceName = "aur_sector_master_id_seq", allocationSize = 1)
	@Column(name = "ID")
	@JsonIgnore
	private Long id;
	@Column(name = "TEXT_ID")
	private Short textId;

	@Column(name = "LANGUAGE_TYPE")
	private String languageType;

	@Column(name = "TEXT_SHORT_DESC")
	private String textShortDesc;

	@Column(name = "TEXT_LONG_DESC")
	private String textLongDesc;
	
	@JsonIgnore
	@Column(name = "USER_CREATED")
	private String userCreated;

	
	@JsonIgnore
	@Column(name = "USER_MODIFIED")
	private String userModified;
	@JsonIgnore
	@Column(name = "DATE_CREATED")
	@CreationTimestamp
	private Timestamp dateCreated;
	@JsonIgnore
	@Column(name = "DATE_MODIFIED")
	@UpdateTimestamp
	private Timestamp dateModifiled;
	@JsonIgnore
	@Column(name = "STATUS")
	private String status;

}
