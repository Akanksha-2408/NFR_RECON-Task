package com.NFR_RECON.Handler;

import com.NFR_RECON.Exception.GSPException;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface IRestHandler {

    // update username by Einvoice Id
    ResponseEntity<Map<String, Object>> updateUserName(String gstin, String userName) throws GSPException;

    // save invoices
    ResponseEntity<Map<String, Object>> saveInvoices(Object object) throws GSPException;

    // update subscription details
    ResponseEntity<Map<String, Object>> updateSubsDetails(String gstin, String productName, String feature,
                                                          String endDate) throws GSPException;
}
