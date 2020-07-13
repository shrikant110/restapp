/**
 * 
 */
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author santosh.panigrahi
 *
 */
@Getter
@Setter
@Entity
@Table(name = "USER_ACTIVITY")
@Scope("session")
public class UserActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_id_seq")
	@SequenceGenerator(name = "activity_id_seq", sequenceName = "activity_id_seq", allocationSize = 1)
	@Column(name = "Activity_ID")
	@JsonIgnore
	private Long id;
	
	@Column(name = "USER_NAME")
	@JsonProperty("UserName")
	private String userName;
	
	@Column(name = "ACTIVITY_TYPE")
	@JsonProperty("ActivityType")
	private String activity_Type;
	
	@Column(name = "ACTION_TIME")
	@JsonProperty("ActionTime")
	private Timestamp action_Time;
	
	@Column(name = "USER_CREATED")
	@JsonProperty("UserCreated")
	private String userCreated;
	
	
	@Column(name = "IP_ADDRESS")
	@JsonProperty("IpAddress")
	private String ipAddress;
	

}
