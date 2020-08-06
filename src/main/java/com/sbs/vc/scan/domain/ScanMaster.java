/**
 * 
 */
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

/**
 * 
 * @author Shrikant.Kushwaha
 *
 */
@Getter
@Setter
@Entity
@Table(name = "SCAN_MST")
@Scope("session")
public class ScanMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SCAN_MST_ID")
	@SequenceGenerator(name = "SEQ_SCAN_MST_ID", sequenceName = "SEQ_SCAN_MST_ID", allocationSize = 1)
	@Column(name = "SCAN_MST_ID")
	@JsonIgnore
	private Long id;
	
	@Column(name = "F_1")
	@JsonProperty("f1")
	private String f1;
	
	@Column(name = "F_2")
	@JsonProperty("f2")
	private String f2;
	
	@Column(name = "F_3")
	@JsonProperty("f3")
	private String f3;
	
	@Column(name = "F_4")
	@JsonProperty("f4")
	private String f4;
	
	@Column(name = "F_5")
	@JsonProperty("f5")
	private String f5;
	
	@Column(name = "F_6")
	@JsonProperty("f6")
	private String f6;
	
	@Column(name = "F_7")
	@JsonProperty("f7")
	private String f7;
	
	@Column(name = "F_8")
	@JsonProperty("f8")
	private String f8;
	
	@Column(name = "F_9")
	@JsonProperty("f9")
	private String f9;
	
	@Column(name = "F_10")
	@JsonProperty("f10")
	private String f10;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCAN_IMAGE_ID")
	ScanImageDomain scanImage;
	
	
	@Column(name = "CipharText")
	@JsonProperty("CipharText")
	private String cipharText;
	

}
