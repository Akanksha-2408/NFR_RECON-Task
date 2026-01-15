package com.NFR_RECON.Controller.Invoices;

import com.NFR_RECON.Exception.GSPException;
import com.NFR_RECON.Handler.IRestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/ERP")
@RequiredArgsConstructor
public class SaveInvoiceDetailsInDbController {

    private final IRestHandler restHandler;
    public static final Logger LOGGER = Logger.getLogger(SaveInvoiceDetailsInDbController.class.getName());

    @PostMapping("/invoice")
    public ResponseEntity<Map<String, Object>> saveInvoice(@RequestBody Object object) throws GSPException {

        LOGGER.log(Level.INFO, "START >> CLASS: InvoiceController >> METHOD: saveInvoiceDetailsInDb ");
        ResponseEntity<Map<String, Object>> result = restHandler.saveInvoices(object);
        LOGGER.log(Level.INFO, "END >> CLASS: InvoiceController >> METHOD: saveInvoiceDetailsInDb ");

        return result;
    }

}
