package com.cardreader.dashboard.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Module")
@Scope("session")
public class Module {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ModuleID")
	@JsonProperty
	private Long id ;
	
	
	@Column(name="ModuleName")
	@JsonProperty
	private String moduleName ;

}
