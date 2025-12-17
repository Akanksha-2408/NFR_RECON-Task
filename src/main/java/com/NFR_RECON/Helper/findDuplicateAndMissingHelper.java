package com.NFR_RECON.Helper;

import com.NFR_RECON.DTO.classDTOMongo;
import com.NFR_RECON.DTO.classDTOMySQL;
import com.NFR_RECON.Entity.classForMongo;
import com.NFR_RECON.Entity.classForMySQL;
import com.NFR_RECON.Repository.findDuplicateAndMissingDataRepoMongo;
import com.NFR_RECON.Repository.findDuplicateAndMissingDataRepoMySQL;
import com.NFR_RECON.Service.findDuplicateAndMissingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class findDuplicateAndMissingHelper {
    private findDuplicateAndMissingDataService service;

    public static List<classForMongo> mongoData = new ArrayList<>();
    public static List<classForMySQL> mysqlData = new ArrayList<>();


    public Optional<Map<String, String>> getMissingData_mysql(List<classForMySQL> mysql_data, List<classForMongo> mongo_data) {

        Map<String, String> missing_in_mysql = new HashMap<>();
        if(mongo_data.size() != mysql_data.size()) {
            for (classForMongo record : mongo_data) {
                if (!mysql_data.contains(record)) {
                    missing_in_mysql.put(record.getGstin(), record.getRetPeriod());
                } else {
                    mongoData.add(record);
                }
            }
        }
        return Optional.of(missing_in_mysql);
    }

    public Optional<Map<String, String>> getMissingData_mongo(List<classForMySQL> mysql_data, List<classForMongo> mongo_data) {

        Map<String, String> missing_in_mongo = new HashMap<>();
        if(mongo_data.size() != mysql_data.size()) {
            for (classForMySQL record : mysql_data) {
                if (!mongo_data.contains(record)) {
                    missing_in_mongo.put(record.getGstin(), record.getReturn_period());
                } else {
                    mysqlData.add(record);
                }
            }
        }
        return Optional.of(missing_in_mongo);
    }

    public Optional<Map<String, String>> getDuplicateData_mysql(String gstin, String txnid) {

        List<classForMySQL> mysql_dataList = new ArrayList<>();
        Set<classForMySQL> mysql_dataSet = new HashSet<>();
        Map<String, String> duplicateDataMap_mysql = new HashMap<>();

        for(classForMySQL record : mysqlData) {
            if(record.getGstin().equals(gstin) && record.getTxn_id().equals(txnid)) {
                mysql_dataList.add(record);
                mysql_dataSet.add(record);
            }
        }

        if(mysql_dataList.size() != mysql_dataSet.size()) {
            for(classForMySQL record : mysql_dataList) {
                int count = 0;
                for(classForMySQL data : mysql_dataList) {
                    if(record.equals(data)) {
                        count++;
                    }
                }
                if(count > 1) {
                    duplicateDataMap_mysql.put(record.getGstin(), record.getReturn_period());
                }
            }
        }
        return Optional.of(duplicateDataMap_mysql);
    }

    public Optional<Map<String, String>> getDuplicateData_mongo(String gstin, String txnid) {

        List<classForMongo> mongo_dataList = new ArrayList<>();
        Set<classForMongo> mongo_dataSet = new HashSet<>();
        Map<String, String> duplicateDataMap_mongo = new HashMap<>();

        for(classForMongo record : mongoData) {
            if(record.getGstin().equals(gstin) && record.getTxnId().equals(txnid) && record.getMigrationStatus().equalsIgnoreCase("migrated")) {
                mongo_dataList.add(record);
                mongo_dataSet.add(record);
            }
        }

        if(mongo_dataList.size() != mongo_dataSet.size()) {
            for(classForMongo record : mongo_dataList) {
                int count = 0;
                for(classForMongo data : mongo_dataList) {
                    if(record.equals(data)) {
                        count++;
                    }
                }
                if(count > 1) {
                    duplicateDataMap_mongo.put(record.getGstin(), record.getRetPeriod());
                }
            }
        }
        return Optional.of(duplicateDataMap_mongo);
    }

}
