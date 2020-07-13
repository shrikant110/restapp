package com.sbs.vc.datapro.auth.constant;
/**
 * 
 * @author shrikant.kushwaha
 * Use to snyn with the Database status
 *
 */
public enum UserStatus {
	
	ACTIVE(1),
	INACTIVE(2),
	SUSPENDED(3);
	
	private final long statusID;

	UserStatus(long statusID) {
        this.statusID = statusID;
    }
    
    public long getStatusID() {
        return this.statusID;
    }

}
