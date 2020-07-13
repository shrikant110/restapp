package com.sbs.vc.datapro.email.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="EMAIL_DETAILS")
public class MailDomain {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "MAIL_ID")
		private Long mailId;
		
		@Column(name="MAIL_FROM")
		private String from;
		
		@Column(name="MAIL_TO")
	    private String to;
		
	    private String bcc;
	    
	    private String subject;
	    
	    @Column(columnDefinition = "TEXT")
	    private String content;
	    private String location;
	    private String filename;
	    private Boolean isAttachment;
	    private Boolean isTempate;
	    private String templateName;
	    
	    public MailDomain() {
	    }

	    public MailDomain(String from, String to, String bcc,String subject, String content) {
	        this.from = from;
	        this.to = to;
	        this.bcc=bcc;
	        this.subject = subject;
	        this.content = content;
	    }

	    
}
