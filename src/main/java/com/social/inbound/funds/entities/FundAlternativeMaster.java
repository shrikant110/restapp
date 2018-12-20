package com.social.inbound.funds.entities;

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

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="FUND_ALTERNATIVE")
@Scope("session")
public class FundAlternativeMaster {
	
		@Id
		@Getter
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fund_alternative_generator")
		@SequenceGenerator(name = "fund_alternative_generator", sequenceName = "fund_alternative_id_seq", allocationSize = 1)
		@Column(name = "id", updatable = false, nullable = false)
		private Long id;
		
		@Column(name="ANALYSIS_DATE")
		private Timestamp analysisDate ;
		
		@Getter
		@Setter
		@Column(name="ISIN")
		private String isin;
		
		@Getter
		@Setter
		@Column(name="ALTERNATIVE_ISIN")
		private String alternativeIsin;
		
		
		@Getter
		@Setter
		@Column(name = "USER_CREATED")
		private String userCreated;
		@Getter
		@Setter
		@CreationTimestamp
		@Column(name = "DATE_CREATED")
		private Timestamp dateCreated;
		@Getter
		@Setter
		@Column(name = "USER_MODIFIED")
		private String userModified;
		@Getter
		@Setter
		@Column(name = "DATE_MODIFIED")
		@UpdateTimestamp
		private Timestamp dateModifiled;


}
