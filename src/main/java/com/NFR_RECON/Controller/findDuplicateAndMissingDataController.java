package com.NFR_RECON.Controller;

import com.NFR_RECON.Service.findDuplicateAndMissingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.Optional;

@RestController
public class findDuplicateAndMissingDataController {

    @Autowired
    findDuplicateAndMissingDataService service;

    @PostMapping("/getDuplicateAndMissingData")
    // TODO: txnid - list - one txnid in list
    public Optional<Map<String, String>> getDuplicateAndMissingData(@RequestHeader String gstin, @RequestBody String txnId) {
        return service.findDuplicateAndMissingData(gstin, txnId);
    }
}
