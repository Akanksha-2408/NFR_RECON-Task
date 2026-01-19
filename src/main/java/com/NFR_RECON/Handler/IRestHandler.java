package com.NFR_RECON.Handler;

import com.NFR_RECON.DTO.AddonDateExtensionRequest;
import com.NFR_RECON.Exception.GSPException;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface IRestHandler {


    // ----- Update-Einvoice-Username API -----
    ResponseEntity<Map<String, Object>> updateUserName(String gstin, String userName) throws GSPException;


    // ----- Save-Invoices API -----
    ResponseEntity<Map<String, Object>> saveInvoices(Object object) throws GSPException;


    // ----- Update-Subscription-Details API -----
    ResponseEntity<Map<String, Object>> updateAddonDate(AddonDateExtensionRequest request) throws GSPException;

}
