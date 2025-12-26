package com.NFR_RECON.Constants;

public enum RecordConstants {

    MIGRATION_STATUS("MIGRATED"),
    DATA("Data"),
    NO_RECORDS_FOUND("No records found with gstin = "),
    FETCH_SUCCESS("Successfully fetched records with "),
    ADD_ALL_SUCCESS("Successfully added all the duplicate, missing records data with "),
    FETCH_DUPLICATE_SUCCESS("Successfully fetched all the duplicate records with "),
    FETCH_MISSING_SUCCESS("Successfully fetched all the missing records with "),
    FAILED_TO_ADD_DUPLICATE_RECORD_IN_LIST("Failed to add duplicate data records in list"),
    FAILED_TO_ADD_MISSING_RECORD_IN_LIST("Failed to add missing data records in list"),
    NO_MONGO_RECORDS_FOUND("No mongo records found with "),
    BOTH_MONGO_AND_MYSQL_RECORDS_ARE_PRESENT("Both mongo and MySQL records are present with either same or " +
            "different number of records having "),
    NO_MISSING_OR_DUPLICATE_RECORD_FOUND("No missing or duplicate records found with "),
    FAILED_TO_FETCH_MISSING_DATA_FROM_MYSQL_DB("Failed to fetch missing data records in MySql database with "),
    FAILED_TO_FETCH_MISSING_DATA_FROM_MONGO_DB("Failed to fetch missing data records in Mongo database with "),
    FAILED_TO_FETCH_DUPLICATE_DATA_FROM_MYSQL_DB("Failed to fetch duplicate data records from Mysql database with "),
    FAILED_TO_FETCH_DUPLICATE_DATA_FROM_MONGO_DB("Failed to fetch duplicate data records from Mongo database with "),;

    private String value;
    RecordConstants(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
