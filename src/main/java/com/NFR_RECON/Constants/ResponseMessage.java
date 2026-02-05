package com.NFR_RECON.Constants;

public interface ResponseMessage {

    String USERNAME_UPDATED_SUCCESSFULLY = "Successfully updated username for gstin: ";
    String FAILED_TO_UPDATE_USERNAME = "Failed to update username for gstin: ";
    String GSTIN_NOT_FOUND = "No Einvoice record found with gstin: ";

    String FAILED_TO_SAVE_INVOICES = "Failed to save invoices";
    String INVOICES_SAVED_SUCCESSFULLY = "Successfully saved invoices";

    String NULL_SUBSCRIPTION_ID = "Subscription ID is null";
    String INVALID_REQUEST = "Invalid request! Missing/ Null required fields: ";
    String INVALID_REQUEST_MISSING_FIELDS = " Invalid Request! Missing required fields: ";
    String INVALID_REQUEST_NULL_FIELDS = " Invalid Request! Null required fields: ";
    String INVALID_ENTRY = "No entry found ";
    String UPDATE_SUCCESSFUL = "Successfully updated End Date, Subscription Id and Updated At fields";
    String DELETE_SUCCESSFUL = "Successfully deleted records with payment status: 'GROUP_SUBSCRIPTION'";
    String STATUS_UPDATED_SUCCESSFULLY = "Successfully updated Reconcilation Status";
    String NO_RECORDS_FOUND = "No records to update !";
    String UPDATE_FAIL = "Failed to update End Date, Subscription Id and Updated At fields";
    String INVALID_DATA = "Inappropriate data found";

    String RECORDS_NOT_FOUND = "No records found in the table ";
    String NO_RECORDS = "No records found with payment status: 'GROUP_SUBSCRIPTION'";

}
