package com.social.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STATUS")
@Scope("session")
@Getter
@Setter
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_id_seq")
	@SequenceGenerator(name = "status_id_seq", sequenceName = "status_id_seq", allocationSize = 50)
	@Column(name = "STATUS_ID")
	private Long statusId;
	
	@Column(name = "DESCRIPTION")
	private String  description;
	
}
