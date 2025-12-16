package com.NFR_RECON.Service;

import com.NFR_RECON.Repository.findDuplicateAndMissingDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.Map;

@Service
public class findDuplicateAndMissingDataService {

    @Autowired
    findDuplicateAndMissingDataRepo repo;

    public Map<String, String> findDuplicateAndMissingData(@RequestHeader String gstin, @RequestBody String txn) {
        for( : repo.findAll()) {

        }
        return null;
    }
}
