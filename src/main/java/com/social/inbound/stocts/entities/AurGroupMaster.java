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
 *         CREATE TABLE AUR_GROUP_MASTER ( Group_Id BIGINT, Group_Name
 *         Varchar(500), Language_Type Varchar(10), User_Created Varchar(50),
 *         Date_Created TIMESTAMPTZ, User_Modified Varchar(50), Date_Modified
 *         TIMESTAMPTZ, Status Varchar(10));
 */
@Entity
@Table(name = "AUR_GROUP_MASTER")
@Scope("session")
@Getter
@Setter
public class AurGroupMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aur_sector_master_id_seq")
	@SequenceGenerator(name = "aur_sector_master_id_seq", sequenceName = "aur_sector_master_id_seq", allocationSize = 50)
	@Column(name = "ID")
	@JsonIgnore
	private Long id;

	@Column(name = "GROUP_ID")
	private int groupId;
	
	@Column(name = "GROUP_NAME")
	private String groupName;
	
	@Column(name = "LANGUAGE_TYPE")
	private String languageType;
	
	@JsonIgnore
	@Column(name = "USER_CREATED")
	private String userCreated;
	
	@CreationTimestamp
	@JsonIgnore
	@Column(name = "DATE_CREATED")
	private Timestamp dateCreated;
	
	@Column(name = "USER_MODIFIED")
	@JsonIgnore
	private String userModified;
	
	@UpdateTimestamp
	@Column(name = "DATE_MODIFIED")
	@JsonIgnore
	private Timestamp dateModifiled;
	
	@Column(name = "STATUS")
	@JsonIgnore
	private String status;

	@Override
	public String toString() {
		return "AurGroupMaster [id=" + id + ", groupId=" + groupId + ", groupName=" + groupName + ", languageType="
				+ languageType + ", userCreated=" + userCreated + ", dateCreated=" + dateCreated + ", userModified="
				+ userModified + ", dateModifiled=" + dateModifiled + ", status=" + status + "]";
	}
	
	

}
