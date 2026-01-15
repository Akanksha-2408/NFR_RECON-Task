package com.NFR_RECON.Exception;

public class GSPException extends RuntimeException {

    ErrorCode errorCode;

    public GSPException(ErrorCode message) {
        super(message.getMessage());
        this.errorCode = message;
    }

    public GSPException(String message) {
        super();
        this.errorCode = ErrorCode.valueOf(message);
    }

    public GSPException(String invalidData, String missingMandatoryHeader) {
        super();
        this.errorCode = ErrorCode.valueOf(invalidData);
    }
}
