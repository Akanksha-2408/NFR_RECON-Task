package com.NFR_RECON.Controller;

import com.NFR_RECON.Service.findDuplicateAndMissingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class findDuplicateAndMissingController {

    @Qualifier("findDuplicateAndMissingServiceImpl")
    @Autowired
    findDuplicateAndMissingService service;

    @PostMapping("/getDuplicateAndMissingData")
    public Map<String, Object> getDuplicateAndMissingData(@RequestHeader String gstin, @RequestBody List<String> txnIdList) throws Exception {
        return service.findDuplicateAndMissingData(gstin, txnIdList);
    }

}
