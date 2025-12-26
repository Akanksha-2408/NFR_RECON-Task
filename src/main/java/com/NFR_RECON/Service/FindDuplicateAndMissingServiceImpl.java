package com.NFR_RECON.Service;

import com.NFR_RECON.DTO.InvDataDto;
import com.NFR_RECON.DTO.InvDetailDataDto;
import com.NFR_RECON.Entity.Entity_Mongo;
import com.NFR_RECON.Entity.Entity_Mysql;
import com.NFR_RECON.Exception.ErrorCode;
import com.NFR_RECON.Exception.RecordException;
import com.NFR_RECON.Repository.MongoDataRepo;
import com.NFR_RECON.Repository.MysqlDataRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import static com.NFR_RECON.Constants.RecordConstants.*;


@Log4j2
@Service
public class FindDuplicateAndMissingServiceImpl implements FindDuplicateAndMissingService {


    @Autowired
    MysqlDataRepo mysql_repo;

    @Autowired
    MongoDataRepo mongo_repo;


    /**
     * @author Akanksha Senad
     * @Date 19-12-2025
     * @Description This method calculates and returns missing data in mysql database and duplicate data in mysql and mongo
     * database
     * @param gstin String gstin
     * @param txnidList List<String> txnidList registered under specific gstin
     * @return Map<String, Object> where Object consists of all the duplicate and missing data fields
     */
//   TODO : Add start and end loggers in required format.
    @Override
    public Map<String, Object> findDuplicateAndMissingData(String gstin, List<String> txnidList) {

        log.info("START : CLASS >> FindDuplicateAndMissingDataServiceImpl >> METHOD >> findDuplicateAndMissingData");

        Map<String, Object> invMap = new HashMap<>();
        InvDataDto invDataDto = new InvDataDto();
        invDataDto.setGstin(gstin);

        List<Entity_Mysql> mysql_data;
        List<Entity_Mongo> mongo_data = new ArrayList<>();

        for(String txnId : txnidList) {
            mysql_data = mysql_repo.getAll(gstin, txnId);

//            TODO: Fetch the data whose status is migrated.
            List<Entity_Mongo> mongoData = mongo_repo.findByGstinAndTxnIdIn(gstin, txnId);
            for(Entity_Mongo data_mongo : mongoData) {
                if(data_mongo.getMigrationStatus().equalsIgnoreCase(MIGRATION_STATUS.getValue())) {
                    mongo_data.add(data_mongo);
                }
            }

            log.info("INTERMEDIATE : CLASS >> FindDuplicateAndMissingService >> METHOD >> findDuplicateAndMissingData -> " + FETCH_SUCCESS.getValue() + " gstin: " + gstin + " and TxnId: " + txnId);

//            TODO: Use constants.
            if (mongo_data.isEmpty() && mysql_data.isEmpty()) {
                invMap.put(DATA.getValue(), NO_RECORDS_FOUND.getValue() + gstin + " and txnId = " + txnId);

            } else {

                try {
                    // --- Finding missing data in mysql db ---
                    Set<InvDetailDataDto> mysql_missing_data = getMissingData_mysql(mongo_data, mysql_data, invDataDto, txnId);
                    if(!mysql_missing_data.isEmpty()) {
                        invDataDto.setMissingInMysql(mysql_missing_data);
                    }
                } catch (RecordException e) {
                    log.error("ERROR : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> findDuplicateAndMissingData -> "
                            + FAILED_TO_FETCH_MISSING_DATA_FROM_MYSQL_DB.getValue() + " gstin: " + gstin + " and TxnId: " + txnId);
                    invMap.put(DATA.getValue(), FAILED_TO_FETCH_MISSING_DATA_FROM_MYSQL_DB.getValue() + " gstin = " + gstin + " and txnId = " + txnId);
                }


                try {
                    // --- Finding duplicate data in mysql db ---
                    Set<InvDetailDataDto> mysql_duplicate_data = getDuplicateData_mysql(mysql_data, gstin, txnId, invDataDto);
                    invDataDto.setDuplicateInMysql(mysql_duplicate_data);
                } catch (RecordException e) {
                    log.error("ERROR : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> findDuplicateAndMissingData -> " + FAILED_TO_FETCH_DUPLICATE_DATA_FROM_MYSQL_DB.getValue() + " gstin: " + gstin + " and TxnId: " + txnId);
                    invMap.put(DATA.getValue(), FAILED_TO_FETCH_DUPLICATE_DATA_FROM_MYSQL_DB.getValue() + " gstin = " + gstin + " and txnId = " + txnId);
                }


                try {
                    // --- Finding duplicate data in mongo db ---
                    Set<InvDetailDataDto> mongo_duplicate_data = getDuplicateData_mongo(mongo_data, gstin, txnId, invDataDto);
                    invDataDto.setDuplicateInMongo(mongo_duplicate_data);
                } catch (RecordException e) {
                    log.error("ERROR : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> findDuplicateAndMissingData -> " + FAILED_TO_FETCH_DUPLICATE_DATA_FROM_MONGO_DB.getValue() + " gstin: " + gstin + " and TxnId: " + txnId);
                    invMap.put(DATA.getValue(), FAILED_TO_FETCH_DUPLICATE_DATA_FROM_MONGO_DB.getValue() + " gstin = " + gstin + " and txnId = " + txnId);
                }

                invMap.put(DATA.getValue(), invDataDto);
                log.info("END : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> findDuplicateAndMissingData -> " + ADD_ALL_SUCCESS.getValue() + " gstin: " + gstin + " and TxnId: " + txnId);
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
    public Set<InvDetailDataDto> getDuplicateData_mysql(List<Entity_Mysql> mysql_data, String gstin, String txnid,
                                                        InvDataDto invDataDto) {

        log.info("START : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> getDuplicateData_mysql");

        Set<String> uniqueInvKeys_mysql = new HashSet<>();
        Set<InvDetailDataDto> duplicateData_mysql = new HashSet<>();

        try {
            for (Entity_Mysql record : mysql_data) {
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
            log.info("END : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> getDuplicateData_mysql -> " + FETCH_DUPLICATE_SUCCESS.getValue() + " gstin: " + gstin + " and TxnId: " + txnid);

        } catch (RecordException re) {
            log.error("ERROR : FindDuplicateAndMissingServiceImpl >> METHOD >> getDuplicateData_mysql -> " + FAILED_TO_ADD_DUPLICATE_RECORD_IN_LIST);
            throw new RecordException(ErrorCode.FAILED_TO_ADD_DUPLICATE_RECORD_IN_LIST);
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
    public Set<InvDetailDataDto> getDuplicateData_mongo(List<Entity_Mongo> mongo_data, String gstin, String txnid,
                                                        InvDataDto invDataDto) {

        log.info("START : CLASS >> FindDuplicateAndMissingServiceImpl >> getDuplicateData_mongo");

        Set<String> uniqueInvKeys_mongo = new HashSet<>();
        Set<InvDetailDataDto> duplicateData_mongo = new HashSet<>();

        try {
            for (Entity_Mongo record : mongo_data) {
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

            log.info("END : CLASS >> FindDuplicateAndMissingServiceImpl >> getDuplicateData_mongo -> " + FETCH_DUPLICATE_SUCCESS.getValue() + " gstin: " + gstin + " and TxnId: " + txnid);

        } catch (RecordException re) {
            log.error("ERROR : FindDuplicateAndMissingServiceImpl >> METHOD >> getDuplicateData_mongo -> " + FAILED_TO_ADD_DUPLICATE_RECORD_IN_LIST);
            throw new RecordException(ErrorCode.FAILED_TO_ADD_DUPLICATE_RECORD_IN_LIST);
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
    public Set<InvDetailDataDto> getMissingData_mysql(List<Entity_Mongo> mongo_data, List<Entity_Mysql> mysql_data,
                                                      InvDataDto invDataDto, String txnid) {

        log.info("START : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> getMissingData_mysql");

        Set<String> invKeyList_mysql = new HashSet<>();
        Set<InvDetailDataDto> missingData_mysql = new HashSet<>();
        String gstin = invDataDto.getGstin();

        try {
            if (!mongo_data.isEmpty() && mysql_data.isEmpty()) {
                log.info("INTERMEDIATE : FindDuplicateAndMissingServiceImpl >> METHOD >> getMissingData_mysql -> " +
                        NO_MONGO_RECORDS_FOUND.getValue() + " gstin: " + gstin + " and TxnId: " + txnid);
                for (Entity_Mongo record : mongo_data) {
                    InvDetailDataDto missing_mysql = new InvDetailDataDto();
                    missing_mysql.setDocType(record.getDoctyp());
                    missing_mysql.setInum(record.getInvKey());
                    missing_mysql.setIdt(record.getInvDate());
                    missing_mysql.setCtin(record.getCtin());
                    missing_mysql.setTxnId(txnid);
                    missingData_mysql.add(missing_mysql);
                }

            } else {
                log.info("INTERMEDIATE : FindDuplicateAndMissingServiceImpl >> METHOD >> getMissingData_mysql -> " +
                        BOTH_MONGO_AND_MYSQL_RECORDS_ARE_PRESENT.getValue() + " gstin: " + gstin + " and TxnId: " + txnid);
                for (Entity_Mysql record : mysql_data) {
                    String invKey = record.getCtin() + record.getInum();
                    invKeyList_mysql.add(invKey);
                }

//              TODO: Check the condition where size is same but the invKey is different or (non matching)
                for (Entity_Mongo record : mongo_data) {
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

            log.info("END : CLASS >> FindDuplicateAndMissingServiceImpl >> getMissingData_mysql -> " +
                    FETCH_MISSING_SUCCESS.getValue() + " gstin: " + gstin + " and TxnId: " + txnid);

        } catch(RecordException re) {
            log.error("ERROR : CLASS >> FindDuplicateAndMissingServiceImpl >> getMissingData_mysql -> " +
                    FAILED_TO_ADD_MISSING_RECORD_IN_LIST.getValue());
            throw new RecordException(ErrorCode.FAILED_TO_ADD_MISSING_RECORD_IN_LIST);
        }
        return missingData_mysql;
    }

}