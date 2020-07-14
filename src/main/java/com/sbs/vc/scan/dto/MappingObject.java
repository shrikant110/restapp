package com.sbs.vc.scan.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MappingObject {
	
	
	
	public MappingObject(String stageField, String targetField, String value) {
		super();
		this.stageField = stageField;
		this.targetField = targetField;
		this.value = value;
	}

	String stageField;
	
	String targetField;
	
	String value;
	
}
