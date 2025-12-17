package com.NFR_RECON.Service;

import com.NFR_RECON.Entity.classForMongo;
import com.NFR_RECON.Entity.classForMySQL;
import com.NFR_RECON.Helper.findDuplicateAndMissingHelper;
import com.NFR_RECON.Repository.findDuplicateAndMissingDataRepoMongo;
import com.NFR_RECON.Repository.findDuplicateAndMissingDataRepoMySQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.*;

@Service
public class findDuplicateAndMissingDataService {

    @Autowired
    findDuplicateAndMissingHelper helper;

    @Autowired
    findDuplicateAndMissingDataRepoMySQL mysql_repo;

    @Autowired
    findDuplicateAndMissingDataRepoMongo mongo_repo;

    public findDuplicateAndMissingDataRepoMySQL getMysql_repo() {
        return mysql_repo;
    }

    public findDuplicateAndMissingDataRepoMongo getMongo_repo() {
        return mongo_repo;
    }

    public Optional<Map<String, String>> findDuplicateAndMissingData(@RequestHeader String gstin, @RequestBody String txnid) {

        List<classForMySQL> mysql_data = mysql_repo.getAll(gstin, txnid);
        List<classForMongo> mongo_data = mongo_repo.findByGstinAndTxnId(gstin, txnid);

        // --- Finding missing data in mysql db ---
        Optional<Map<String, String>> mysql_missing_data = helper.getMissingData_mysql(mysql_data, mongo_data);


        // --- Finding missing data in mongo db ---
        Optional<Map<String, String>> mongo_missing_data = helper.getMissingData_mongo(mysql_data, mongo_data);


        // --- Finding duplicate data in mysql db ---
        Optional<Map<String, String>> mysql_duplicate_data = helper.getDuplicateData_mysql(gstin, txnid);


        // --- Finding duplicate data in mongo db ---
        Optional<Map<String, String>> mongo_duplicate_data = helper.getDuplicateData_mongo(gstin, txnid);


        return mysql_missing_data;
    }


}
