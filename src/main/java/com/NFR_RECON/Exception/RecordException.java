package com.NFR_RECON.Exception;

import lombok.Data;

@Data
public class RecordException extends RuntimeException {

    ErrorCode errorCode;

    public RecordException(ErrorCode message) {
        super(message.getMessage());
        this.errorCode = message;
    }

}
