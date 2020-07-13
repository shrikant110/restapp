package com.sbs.vc.datapro.auth.constant;

public enum TokenIssuePurpose {
	CHANGE_PASSWORD("CHANGE_PASSWORD"),
	;
	
	private final String purpose;

	TokenIssuePurpose(String statusID) {
        this.purpose = statusID;
    }
    
    public String getPurpose() {
        return this.purpose;
    }
}
