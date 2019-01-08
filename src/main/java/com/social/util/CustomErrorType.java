package com.social.util;
/**
 * 
 * @author shrikant.kushwaha
 * This class is used for handle the error message & send back for the client
 *
 */
public class CustomErrorType {

    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
