package com.cardreader.config.util;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "Response")
@JsonRootName(value = "Response")
@JsonInclude(Include.NON_NULL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({HashMap.class})
public class ResponseMessageDTO {
	
	
	@XmlElement(name = "AuthToken")
	@JsonProperty(value = "AuthToken")
	private String authToken;

	
	@XmlElement(name = "ResponseCode")
	@JsonProperty(value = "ResponseCode")
	private int responseCode;
	
	@XmlElement(name = "ResponseMessage")
	@JsonProperty(value = "ResponseMessage")
	private String responseMessage;
	
	@XmlElement(name = "ErrorDiscription")
	@JsonProperty(value = "ErrorDiscription")
	private String errorDiscription;
	
	@XmlElement(name = "Data")
	@JsonProperty(value = "Data")
	private Object data;
}
