package com.social.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.social.entities.model.Gender;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_CREDENTIAL_DETAILS")
@Getter
@Setter
public class UserProfileDetails implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credentials_id_seq")
	@SequenceGenerator(name = "credentials_id_seq", sequenceName = "credentials_id_seq", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Size(max = 40,min =6)
	private String  password;
	
	@Column(name = "phone_number")
    @Size(max = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dateOfBirth;

    @Size(max = 100)
    private String address1;

    @Size(max = 100)
    private String address2;

    @Size(max = 100)
    private String street;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String state;

    @Size(max = 100)
    private String country;

    @Column(name = "zip_code")
    @Size(max = 32)
    private String zipCode;
    
    @Column(name = "DATE_MODIFIED")
	@UpdateTimestamp
	private Timestamp dateModifiled;

   
   /* @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userInfo;*/
    

}
