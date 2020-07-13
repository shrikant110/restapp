package com.sbs.vc.datapro.auth.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "SUBCRIPTION_MASTER")
@Scope("session")
public class SubscriptionMaster {
	@JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUBSCRIPTION_ID", updatable = false, nullable = false)
	long subscriptionId;

	@JsonProperty("SubscriptionType")
	@Column(name = "SUBSCRIPTION_TYPE")
	String type;
	
	@JsonProperty("Fee")
	@Column(name = "SUBSCRIPTION_FEE")
	double fee;
	
	@JsonProperty("Duration")
	@Column(name = "SUBSCRIPTION_DURATION")
	int duration;
	
	@JsonProperty("Description")
	@Column(name = "SUBSCRIPTION_DESC")
	String description;
	
	
	@JsonProperty("Currency")
	@Column(name = "currency")
	String currency;
	
	@JsonProperty("SaveAmount")
	@Column(name = "save_amount")
	int saveAmount;
	
	@JsonProperty("DurationInMonth")
	@Column(name = "duration_in_month")
	String durationInMonth;
	
	@Transient
	@JsonProperty("TransactionId")
	String transactionId;
	
	@Transient
	@JsonProperty("LastAmount")
	double lastAmount;
	
	@JsonIgnore
	@Column(name = "USER_CREATED")
	private String userCreated;
	@JsonIgnore
	@CreationTimestamp
	@Column(name = "DATE_CREATED")
	private Timestamp dateCreated;
	@JsonIgnore
	@Column(name = "USER_MODIFIED")
	private String userModified;
	@JsonIgnore
	@Column(name = "DATE_MODIFIED")
	@UpdateTimestamp
	private Timestamp dateModifiled;

}
