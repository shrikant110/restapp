package com.sbs.vc.scan.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SCAN_IMAGE")
@Scope("session")
public class ScanImageDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scanimage_id_seq")
	@SequenceGenerator(name = "scanimage_id_seq", sequenceName = "scanimage_id_seq", allocationSize = 1)
	@Column(name = "SCAN_IMAGE_ID")
	@JsonIgnore
	private Long scanImageId;
	
	@Column(name = "name")
	private String name;
	    
	@Column(name = "type")
	private String type;
		
	@Lob
	@Type(type = "org.hibernate.type.ImageType")
	@Column(name="pic")
	private byte[] pic;
	
}
