package com.sbs.vc.datapro.auth.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "user_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        })
})
@Getter
@Setter
public class User  {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
	@JsonProperty("FirstName")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	@JsonProperty("LastName")
	private String lastName;
	
	@Transient
	@JsonProperty("PassCode")
	private String passCode;

    @NotBlank
    @Size(max = 100)
    @JsonProperty("UserName")
    private String username;

    @Size(max = 40)
    @JsonIgnore
    private String email;

    @NotBlank
    @Size(max = 100)
    @JsonIgnore
    private String password;
    
    @JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STATUS_ID")
    private Status status;
    
    @JsonProperty("Roles")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {

    }
    
    @Transient
    @JsonProperty("OldPassword")
	private String oldPassword;
	
	@Transient
	@JsonProperty("NewPassword")
	private String newPassword;
	

	@JsonProperty("lang")
	@Column(name = "prefered_language")
    @Size(max = 50)
    private String preferedLanguage;
	
	@JsonIgnore
	@Column(name = "ACCOUNT_ACTIVATION_TOKEN")
	private String accActivationToken;
	
	@Column(name = "MOBILE_NO")
	@JsonProperty("MobileNumber")
	private String mobileNumber;
	
	
	
	@JsonIgnore
	@Getter
	@Setter
	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	
	@JsonIgnore
	@Getter
	@Setter
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Timestamp updatedAt;

	@Override
	public String toString() {
		return "User [username=" + username + ", createdAt=" + createdAt + "]";
	}
	
	   
}