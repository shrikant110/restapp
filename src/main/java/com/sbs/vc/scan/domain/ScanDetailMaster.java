package com.sbs.vc.scan.domain;

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

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SCAN_DET")
@Scope("session")
public class ScanDetailMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DET_ID")
	@SequenceGenerator(name = "SEQ_DET_ID", sequenceName = "SEQ_DET_ID", allocationSize = 1)
	@Column(name = "SCAN_DET_ID")
	@JsonIgnore
	private Long id;
	
	@Column(name = "DESIGNATION")
	@JsonProperty("DESIGNATION")
	private String designation;
	
	@Column(name = "NAME")
	@JsonProperty("NAME")
	private String name;
	
	
	@Column(name = "MOBILE_NO")
	@JsonProperty("MOBILE_NO")
	private String mobileNO ;
	
	@Column(name = "EMAIL_ID")
	@JsonProperty("EMAIL_ID")
	private String emailId;
	
	@Column(name = "COUNTRY")
	@JsonProperty("COUNTRY")
	private String country;
	
	@Column(name = "ADDRESS")
	@JsonProperty("ADDRESS")
	private String address;
	
	@Column(name = "COMMENTS")
	@JsonProperty("COMMENTS")
	private String comments;
	
	@Column(name = "OTH_1")
	@JsonProperty("OTH_1")
	private String other1;
	
	@Column(name = "OTH_2")
	@JsonProperty("OTH_2")
	private String other2;
	
	@Column(name = "OTH_3")
	@JsonProperty("OTH_3")
	private String other3;
	
	@Column(name = "OTH_4")
	@JsonProperty("OTH_4")
	private String other4;
	

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCAN_MST_ID")
	ScanMaster scanMaster;

}
