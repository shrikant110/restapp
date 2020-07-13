package com.sbs.vc.datapro.auth.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USER_CREDENTIAL_DETAILS")
@EntityListeners(AuditingEntityListener.class)
public class UserProfileDetails implements Serializable {
	
	private static final long serialVersionUID = -7032947935089896983L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credentials_id_seq")
	@SequenceGenerator(name = "credentials_id_seq", sequenceName = "credentials_id_seq", allocationSize = 1)
	@Column(name = "ID")
	@JsonIgnore
	private Long id;
	
	@Size(max = 255,min =6)
	@JsonIgnore
	private String  password;
	

    @JsonIgnore
    @Column(name = "DATE_MODIFIED")
	@UpdateTimestamp
	private Timestamp dateModifiled;
    
    @JsonIgnore
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private long createdDate;
 
 
    

}
