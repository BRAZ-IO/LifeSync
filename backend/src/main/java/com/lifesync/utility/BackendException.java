package com.lifesync.utility;

public class BackendException extends RuntimeException {
    
    private String errorCode;
    
    public BackendException(String message) {
        super(message);
    }
    
    public BackendException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BackendException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public BackendException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
