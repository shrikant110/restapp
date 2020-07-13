package com.cardreader.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLES")
@Scope("session")
@Getter
@Setter
public class Roles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
	@SequenceGenerator(name = "role_id_seq", sequenceName = "role_id_seq", allocationSize = 50)
	@Column(name = "ROLE_ID")
	private Long roleId;
	
	@Column(name = "DESCRIPTION")
	private String  description;

}
