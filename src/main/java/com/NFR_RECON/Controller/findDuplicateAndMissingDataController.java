package com.NFR_RECON.Controller;

import com.NFR_RECON.Service.findDuplicateAndMissingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@Controller
public class findDuplicateAndMissingDataController {

    @Autowired
    findDuplicateAndMissingDataService service;

    @PostMapping("/getDuplicateAndMissingData")
    public Map<String, String> getDuplicateAndMissingData(@RequestHeader String gstin, @RequestBody String txnId) {
        return service.findDuplicateAndMissingData(gstin, txnId);
    }
}
