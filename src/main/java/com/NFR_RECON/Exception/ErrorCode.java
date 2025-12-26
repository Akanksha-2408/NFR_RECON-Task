package com.NFR_RECON.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    UNKNOWN_ERROR("UE", "Record Not Found" , HttpStatus.CONFLICT),
    NO_DATA_IS_DUPLICATE_OR_MISSING("NDIDOM", "No duplicate or missing data found" , HttpStatus.NOT_FOUND),
    FAILED_TO_FETCH_MISSING_DATA_IN_MYSQL("FTFMDIMS", "Failed to fetch missing records in MySQL", HttpStatus.INTERNAL_SERVER_ERROR),
    FAILED_TO_FETCH_MISSING_DATA_IN_MONGO("FTFMDIM", "Failed to fetch missing records in Mongo", HttpStatus.INTERNAL_SERVER_ERROR),
    FAILED_TO_FETCH_DUPLICATE_DATA_FROM_MYSQL_DB("FTFDDIMS", "Failed to fetch duplicate records in MySQL", HttpStatus.INTERNAL_SERVER_ERROR),
    FAILED_TO_FETCH_DUPLICATE_DATA_FROM_MONGO_DB("FTFDDIM", "Failed to fetch duplicate records in Mongo", HttpStatus.INTERNAL_SERVER_ERROR),
    FAILED_TO_ADD_DUPLICATE_RECORD_IN_LIST("FTADRIL", "Failed to add duplicate data records in list", HttpStatus.INTERNAL_SERVER_ERROR),
    FAILED_TO_ADD_MISSING_RECORD_IN_LIST("FTAMRIL", "Failed to add missing data records in list", HttpStatus.INTERNAL_SERVER_ERROR);


    private final String code;
    private final String message;
    private final HttpStatus status;


    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
