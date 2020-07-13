package com.cardreader.mail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="EMail_DETAILS")
public class Mail {
	
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mail_id_seq")
		@SequenceGenerator(name = "mail_id_seq", sequenceName = "mail_id_seq", allocationSize = 10000)
		@Column(name = "Mail_ID")
		private Long mailId;
		
		@Column(name="MAIL_FROM")
		private String from;
		@Column(name="MAIL_TO")
	    private String to;
	    private String bcc;
	    private String subject;
	    private String content;
	    private String location;
	    private String filename;
	    private Boolean isAttachment;
	    private Boolean isTempate;
	    private String templateName;
	    
	    public Mail() {
	    }

	    public Mail(String from, String to, String bcc,String subject, String content) {
	        this.from = from;
	        this.to = to;
	        this.bcc=bcc;
	        this.subject = subject;
	        this.content = content;
	    }

	    
}
