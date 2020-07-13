package com.sbs.vc.datapro.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user_profile_image")
public class ImageModel {
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_image_id_seq")
	@SequenceGenerator(name = "profile_image_id_seq", sequenceName = "profile_image_id_seq", allocationSize = 50)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "username")
	private String username;
	
    @Column(name = "name")
	private String name;
    
    @Column(name = "type")
	private String type;
	
	@Lob
	@Type(type = "org.hibernate.type.ImageType")
    @Column(name="pic")
    private byte[] pic;
	
	public ImageModel(){}
	
	public ImageModel(long id, String name, String type, byte[] pic){
		this.id = id;
		this.name = name;
		this.type = type;
		this.pic = pic;
	}
	
	
}
