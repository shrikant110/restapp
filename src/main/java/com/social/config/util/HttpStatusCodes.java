package com.social.config.util;

/**
 * 
 * @author Saurabh.Agarwal
 *
 */
public enum HttpStatusCodes {
	SUCCESS("200"),
	ERROR("300"),
	SFTPERROR("500"),
	NODATAFOUND("8010"),
	AVAILABLE("3001"),
	UNAUTHORISED("400"),
	UNAUTHORISED1("401"),
	UNAUTHORISED2("4011"),
	REGISTERED("3002"),
	REGISTEREDNEW("3000"),
	FORGETSUCCESS("3003"),
	LOGINSUCCESS("3004"),
	ACCOUNTINACTIVE("9001"),
	LOGINSUCCESSWITHPWDEXPIRE("201"),
	LOGOUTSUCCESS("3005"),
	UNAUTHENTICATEDREQUEST("3006"),
	UNAUTHERISEDREQUEST("3007"),
	CHANGEPASSWORDSUCCESS("3008"),
	PROFILEUPDATESUCCESS("3009"),
	LOGINFAILED("3010"),
	LOGINSUCCESSCOMPANYPENDING("3011"),
	
	ACLSAVESUCESS("5001"),
	ACLSAVEERROR("5002"),
	
	LEICHARTDATAFILENOTFOUND("301"),
	
	;

	

	private String code;
	/**
	 * @param code
	 */
	private HttpStatusCodes(String code) {
		this.code=code;
	}
	/**
	 * This is used return status code
	 * @return code
	 */
	public String getCode() {
		return code;
	}
}
