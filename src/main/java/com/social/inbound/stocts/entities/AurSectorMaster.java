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
 * @author shrikant.kushwaha
 *
 * 
 *         CREATE TABLE AUR_Sector_MASTER( Sector_Id BIGINT, Sector_Name
 *         Varchar(500), Language_Type Varchar(10), User_Created Varchar(50),
 *         Date_Created TIMESTAMPTZ, User_Modified Varchar(50), Date_Modified
 *         TIMESTAMPTZ, Status Varchar(10));
 */
@Getter
@Setter
@Entity
@Table(name = "AUR_SECTOR_MASTER")
@Scope("session")
public class AurSectorMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aur_sector_master_id_seq")
	@SequenceGenerator(name = "aur_sector_master_id_seq", sequenceName = "aur_sector_master_id_seq", allocationSize = 50)
	@JsonIgnore
	@Column(name = "ID")
	private Long id;

	@Column(name = "SECTOR_ID")
	private int sectorId;

	@Column(name = "SECTOR_NAME")
	private String sectorName;

	@Column(name = "LANGUAGE_TYPE")
	private String languageType;
	@JsonIgnore
	@Column(name = "USER_CREATED")
	private String userCreated;
	@JsonIgnore
	@Column(name = "DATE_CREATED")
	@CreationTimestamp
	private Timestamp dateCreated;
	@JsonIgnore
	@Column(name = "USER_MODIFIED")
	private String userModified;
	@JsonIgnore
	@Column(name = "DATE_MODIFIED")
	@UpdateTimestamp
	private Timestamp dateModifiled;
	@JsonIgnore
	@Column(name = "STATUS")
	private String status;

}
