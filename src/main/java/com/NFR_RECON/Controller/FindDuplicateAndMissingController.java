package com.NFR_RECON.Controller;

import com.NFR_RECON.Exception.RecordException;
import com.NFR_RECON.Service.FindDuplicateAndMissingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.NFR_RECON.Constants.RecordConstants.DATA;
import static com.NFR_RECON.Constants.RecordConstants.NO_MISSING_OR_DUPLICATE_RECORD_FOUND;

@Log4j2
@RestController

//TODO : Rename the class. Use refer standard naming convensions.
public class FindDuplicateAndMissingController {


    @Autowired
    private FindDuplicateAndMissingService service;

//    TODO: Add method level comments also add start and end loggers in require format.

    /**
     * Returns Duplicate and Missing data map
     * @param gstin gstin
     * @param txnIdList txnIdList
     * @return Map<String, Object>
     */
    @PostMapping("/getDuplicateAndMissingData")
    public Map<String, Object> getDuplicateAndMissingData(@RequestHeader String gstin, @RequestBody List<String> txnIdList) {

        Map<String, Object> resultMap = new HashMap<>();

        log.info("START : CLASS >> FindDuplicateAndMissingController >> METHOD >> getDuplicateAndMissingData");
        try {
            resultMap = service.findDuplicateAndMissingData(gstin, txnIdList);
        } catch(RecordException e) {
            resultMap.put(DATA.getValue(), NO_MISSING_OR_DUPLICATE_RECORD_FOUND.getValue() + " gstin: " + gstin);
            log.error("ERROR : CLASS >> FindDuplicateAndMissingController >> METHOD >> getDuplicateAndMissingData -> " +
                        NO_MISSING_OR_DUPLICATE_RECORD_FOUND.getValue() + " gstin:" + gstin);
        }
        log.info("END : CLASS >> FindDuplicateAndMissingController >> METHOD >> getDuplicateAndMissingData");
        return resultMap;

    }

}
