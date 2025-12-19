package com.NFR_RECON.Service;

import com.NFR_RECON.DTO.InvDataDto;
import com.NFR_RECON.DTO.InvDetailDataDto;
import com.NFR_RECON.Entity.classForMongo;
import com.NFR_RECON.Entity.classForMysql;
import com.NFR_RECON.ErrorCode.ErrorCode;
import com.NFR_RECON.ErrorCode.RecordException;
import com.NFR_RECON.Repository.findDuplicateAndMissingRepoMongo;
import com.NFR_RECON.Repository.findDuplicateAndMissingRepoMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class findDuplicateAndMissingServiceImpl implements findDuplicateAndMissingService {

    private static final Logger LOGGER = Logger.getLogger(findDuplicateAndMissingServiceImpl.class.getName());

    @Autowired
    findDuplicateAndMissingRepoMysql mysql_repo;

    @Autowired
    findDuplicateAndMissingRepoMongo mongo_repo;


    /**
     * @author Akanksha Senad
     * @Date 19-12-2025
     * @Description This method calculates and returns missing data in mysql database and duplicate data in mysql and mongo
     * database
     * @param gstin String gstin
     * @param txnidList List<String> txnidList registered under specific gstin
     * @return Map<String, Object> where Object consists of all the duplicate and missing data fields
     */
    @Override
    public Map<String, Object> findDuplicateAndMissingData(String gstin, List<String> txnidList) {

        Map<String, Object> invMap = new HashMap<>();
        InvDataDto invDataDto = new InvDataDto();
        invDataDto.setGstin(gstin);

        LOGGER.log(Level.INFO, "Successfully initialized InvDataDto object with GSTIN: " + gstin);

        List<classForMysql> mysql_data;
        List<classForMongo> mongo_data;

        for(String txnId : txnidList) {

            mysql_data = mysql_repo.getAll(gstin, txnId);
            mongo_data = mongo_repo.findByGstinAndTxnIdIn(gstin, txnId);

            LOGGER.log(Level.INFO, "Successfully fetched the mysql and mongo records with gstin: " + gstin + "and txnId: " + txnId);

            if (mongo_data.isEmpty() && mysql_data.isEmpty()) {
                invMap.put("data", "No records found with this gstin " + gstin);

            } else {

                try {
                    // --- Finding missing data in mysql db ---
                    Set<InvDetailDataDto> mysql_missing_data = getMissingData_mysql(mongo_data, mysql_data, invDataDto, txnId);
                    invDataDto.setMissingInMysql(mysql_missing_data);
                    LOGGER.log(Level.INFO, "Successfully added the missing mysql records with gstin: " + gstin + "and txnId: " + txnId + " to invDataDto object.");

                    // --- Finding duplicate data in mysql db ---
                    Set<InvDetailDataDto> mysql_duplicate_data = getDuplicateData_mysql(mysql_data, gstin, txnId, invDataDto);
                    invDataDto.setDuplicateInMysql(mysql_duplicate_data);
                    LOGGER.log(Level.INFO, "Successfully added the duplicate mysql records with gstin: " + gstin + "and txnId: " + txnId + " to  invDataDto object.");

                    // --- Finding duplicate data in mongo db ---
                    Set<InvDetailDataDto> mongo_duplicate_data = getDuplicateData_mongo(mongo_data, gstin, txnId, invDataDto);
                    invDataDto.setDuplicateInMongo(mongo_duplicate_data);
                    LOGGER.log(Level.INFO, "Successfully added the duplicate mongo records with gstin: " + gstin + "and txnId: " + txnId + " to  invDataDto object.");

                    invMap.put("data", invDataDto);
                    LOGGER.log(Level.INFO, "Successfully added all the duplicate, missing records data with gstin: " + gstin + "and txnId: " + txnId + " into final map");

                } catch (RecordException re) {
                    LOGGER.log(Level.SEVERE, "findDuplicateAndMissingServiceImpl :: findDuplicateAndMissingData :: Failed to get duplicate and missing data from mongo and mysql database");
                    throw new RecordException(ErrorCode.UNKNOWN_ERROR);
                }
            }
        }
        return invMap;
    }

    /**
     * @author Akanksha Senad
     * @Date 19-12-2025
     * @Description This method returns a list of duplicate records present in mysql database
     * @param mysql_data List of mysql records containing specific gstin, List of txnid
     * @param gstin gstin
     * @param txnid txnid
     * @param invDataDto InvDataDto object consisting of all required fields to be returned
     * @return List<InvDetailDataDto> duplicate records present in mysql database
     */
    @Override
    public Set<InvDetailDataDto> getDuplicateData_mysql(List<classForMysql> mysql_data, String gstin, String txnid,
                                                         InvDataDto invDataDto) {

        Set<String> uniqueInvKeys_mysql = new HashSet<>();
        Set<InvDetailDataDto> duplicateData_mysql = new HashSet<>();

        try {
            for (classForMysql record : mysql_data) {
                String invKey = record.getCtin() + record.getInum();
                if (!uniqueInvKeys_mysql.add(invKey)) {
                    InvDetailDataDto duplicates_mysql = new InvDetailDataDto();
                    duplicates_mysql.setDocType(record.getTyp());
                    duplicates_mysql.setInum(record.getInum());
                    duplicates_mysql.setIdt(record.getIdt());
                    duplicates_mysql.setCtin(record.getCtin());
                    duplicates_mysql.setTxnId(txnid);
                    duplicateData_mysql.add(duplicates_mysql);
                }
            }
            LOGGER.log(Level.INFO, "Successfully fetched all the duplicate records data in mysql with gstin: " + gstin + "and txnId: " + txnid);

        } catch (RecordException re) {
            LOGGER.log(Level.SEVERE, "findDuplicateAndMissingServiceImpl :: getDuplicateData_mysql :: Failed to fetch the duplicate mysql data records with gstin: " + gstin + " and txnId: " + txnid);
            throw new RecordException(ErrorCode.UNKNOWN_ERROR);
        }
        return duplicateData_mysql;
    }

    /**
     * @author Akanksha Senad
     * @Date 19-12-2025
     * @Description This method returns a list of duplicate records present in mongo database
     * @param mongo_data List of mongo records containing specific gstin, List of txnid
     * @param gstin gstin
     * @param txnid txnid
     * @param invDataDto InvDataDto object consisting of all required fields to be returned
     * @return List<InvDetailDataDto> duplicate records present in mongo database
     */
    @Override
    public Set<InvDetailDataDto> getDuplicateData_mongo(List<classForMongo> mongo_data, String gstin, String txnid,
                                                         InvDataDto invDataDto) {

        Set<String> uniqueInvKeys_mongo = new HashSet<>();
        Set<InvDetailDataDto> duplicateData_mongo = new HashSet<>();

        try {
            for (classForMongo record : mongo_data) {
                if (record.getMigrationStatus().equalsIgnoreCase("MIGRATED")) {
                    String invKey = record.getInvKey();
                    if (!uniqueInvKeys_mongo.add(invKey)) {
                        InvDetailDataDto duplicates_mongo = new InvDetailDataDto();
                        duplicates_mongo.setDocType(record.getDoctyp());
                        duplicates_mongo.setInum(record.getInvKey());
                        duplicates_mongo.setIdt(record.getInvDate());
                        duplicates_mongo.setCtin(record.getCtin());
                        duplicates_mongo.setTxnId(txnid);
                        duplicateData_mongo.add(duplicates_mongo);
                    }
                }
            }
            LOGGER.log(Level.INFO, "Successfully fetched all the duplicate records data in mongodb with gstin: " + gstin + "and txnId: " + txnid);

        } catch (RecordException re) {
            LOGGER.log(Level.SEVERE, "findDuplicateAndMissingServiceImpl :: getDuplicateData-mongo :: Failed to fetch the duplicate mongo data records with gstin: " + gstin + " and txnId: " + txnid);
            throw new RecordException(ErrorCode.UNKNOWN_ERROR);
        }
        return duplicateData_mongo;
    }

    /**
     * @author Akanksha Senad
     * @Date 19-12-2025
     * @Description This method returns a list of missing records present in mysql database. Since all data is first
     * stored in MongoDB, any missing data would be only in MySQL, not in MongoDB.
     * @param mongo_data List of mongo records containing specific gstin, List of txnid
     * @param mysql_data List of mysql records containing specific gstin, List of txnid
     * @param invDataDto InvDataDto object consisting of all required fields to be returned
     * @param txnid txnid
     * @return List<InvDetailDataDto> mongodb records missing in mysql
     */
    @Override
    public Set<InvDetailDataDto> getMissingData_mysql(List<classForMongo> mongo_data, List<classForMysql> mysql_data,
                                                       InvDataDto invDataDto, String txnid) {

        Set<String> invKeyList_mysql = new HashSet<>();
        Set<InvDetailDataDto> missingData_mysql = new HashSet<>();
        String gstin = invDataDto.getGstin();

        try {
            if (!mongo_data.isEmpty() && mysql_data.isEmpty()) {
                for (classForMongo record : mongo_data) {
                    if (record.getMigrationStatus().equalsIgnoreCase("MIGRATED")) {
                        InvDetailDataDto missing_mysql = new InvDetailDataDto();
                        missing_mysql.setDocType(record.getDoctyp());
                        missing_mysql.setInum(record.getInvKey());
                        missing_mysql.setIdt(record.getInvDate());
                        missing_mysql.setCtin(record.getCtin());
                        missing_mysql.setTxnId(txnid);
                        missingData_mysql.add(missing_mysql);
                    }
                }

            } else {

                for (classForMysql record : mysql_data) {
                    String invKey = record.getCtin() + record.getInum();
                    invKeyList_mysql.add(invKey);
                }

                if (mongo_data.size() != mysql_data.size()) {
                    for (classForMongo record : mongo_data) {
                        if (record.getMigrationStatus().equalsIgnoreCase("MIGRATED")) {
                            if (!invKeyList_mysql.contains(record.getInvKey())) {
                                InvDetailDataDto missing_mysql = new InvDetailDataDto();
                                missing_mysql.setDocType(record.getDoctyp());
                                missing_mysql.setInum(record.getInvKey());
                                missing_mysql.setIdt(record.getInvDate());
                                missing_mysql.setCtin(record.getCtin());
                                missing_mysql.setTxnId(txnid);
                                missingData_mysql.add(missing_mysql);
                            }
                        }
                    }
                }
            }
            LOGGER.log(Level.INFO, "Successfully fetched all the data records present in mongodb but missing in mysql with gstin: " + gstin + "and txnId: " + txnid);
        } catch(RecordException re) {
            LOGGER.log(Level.SEVERE, "findDuplicateAndMissingServiceImpl :: getMissingData-mysql :: Failed to fetch the data records present in mongodb but missing in mysql with gstin: " + gstin + "and txnId: " + txnid);
        }
        return missingData_mysql;
    }

}