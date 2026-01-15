package com.NFR_RECON.ServiceImpl.Recon;

import com.NFR_RECON.DTO.InvDataDto;
import com.NFR_RECON.DTO.InvDetailDataDto;
import com.NFR_RECON.Entity.Entity_Mongo;
import com.NFR_RECON.Entity.Entity_Mysql;
import com.NFR_RECON.Exception.ErrorCode;
import com.NFR_RECON.Exception.RecordException;
import com.NFR_RECON.Repository.MongoDataRepo;
import com.NFR_RECON.Repository.MysqlDataRepo;
import com.NFR_RECON.Service.Recon.FindDuplicateAndMissingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.NFR_RECON.Constants.RecordConstants.*;

@Service
public class FindDuplicateAndMissingServiceImpl implements FindDuplicateAndMissingService {


    @Autowired
    MysqlDataRepo mysql_repo;

    @Autowired
    MongoDataRepo mongo_repo;

    public static final Logger LOGGER = Logger.getLogger(FindDuplicateAndMissingServiceImpl.class.getName());


    /**
     * @author Akanksha Senad
     * @Date 19-12-2025
     * @Description This method calculates and returns missing data in mysql database and duplicate data in mysql and
     * mongo database
     * @param gstin String gstin
     * @param txnidList List<String> txnidList registered under specific gstin
     * @return Map<String, Object> where Object consists of all the duplicate and missing data fields
     */

    @Override
    public Map<String, Object> findDuplicateAndMissingData(String gstin, List<String> txnidList) {

        LOGGER.log(Level.INFO, "START : CLASS >> FindDuplicateAndMissingDataServiceImpl >> METHOD >> " +
                "findDuplicateAndMissingData with GSTIN: " + gstin);

        Map<String, Object> invMap = new HashMap<>();
        InvDataDto invDataDto = new InvDataDto();
        invDataDto.setGstin(gstin);

        List<Entity_Mysql> mysql_data;
        List<Entity_Mongo> mongo_data = new ArrayList<>();

        for(String txnId : txnidList) {
            mysql_data = mysql_repo.getAll(gstin, txnId);

            List<Entity_Mongo> mongoData = mongo_repo.findByGstinAndTxnIdIn(gstin, txnId);
            for(Entity_Mongo data_mongo : mongoData) {
                if(data_mongo.getMigrationStatus().equalsIgnoreCase(MIGRATION_STATUS.getValue())) {
                    mongo_data.add(data_mongo);
                }
            }

            LOGGER.log(Level.INFO, "INTERMEDIATE : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> " +
                    "findDuplicateAndMissingData -> " + FETCH_SUCCESS.getValue() + " gstin: " + gstin + " and TxnId: "
                    + txnId);

            if (mongo_data.isEmpty() && mysql_data.isEmpty()) {
                invMap.put(DATA.getValue(), NO_RECORDS_FOUND.getValue() + gstin + " and txnId = " + txnId);

            } else {

                try {
                    // --- Finding missing data in mysql db ---
                    Set<InvDetailDataDto> mysql_missing_data = getMissingDataMysql(mongo_data, mysql_data, invDataDto,
                            txnId);
                    if(!mysql_missing_data.isEmpty()) {
                        invDataDto.setMissingInMysql(mysql_missing_data);
                    }
                } catch (RecordException e) {
                    LOGGER.log(Level.SEVERE, "ERROR : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >>" +
                            " findDuplicateAndMissingData -> " + FAILED_TO_FETCH_MISSING_DATA_FROM_MYSQL_DB.getValue() +
                            " gstin: " + gstin + " and TxnId: " + txnId + " >> ERROR: " + e);
                    invMap.put(DATA.getValue(), FAILED_TO_FETCH_MISSING_DATA_FROM_MYSQL_DB.getValue() + " gstin = " +
                            gstin + " and txnId = " + txnId);
                }


                try {
                    // --- Finding missing data in mongo db ---
                    Set<InvDetailDataDto> mongo_missing_data = getMissingDataMongo(mongo_data, mysql_data, invDataDto,
                            txnId);
                    if(!mongo_missing_data.isEmpty()) {
                        invDataDto.setMissingInMongo(mongo_missing_data);
                    }
                } catch (RecordException e) {
                    LOGGER.log(Level.SEVERE, "ERROR : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >>" +
                            " findDuplicateAndMissingData -> " + FAILED_TO_FETCH_MISSING_DATA_FROM_MONGO_DB.getValue() +
                            " gstin: " + gstin + " and TxnId: " + txnId +  " >> ERROR: " + e);
                    invMap.put(DATA.getValue(), FAILED_TO_FETCH_MISSING_DATA_FROM_MONGO_DB.getValue() + " gstin = " +
                            gstin + " and txnId = " + txnId);
                }


                try {
                    // --- Finding duplicate data in mysql db ---
                    Set<InvDetailDataDto> mysql_duplicate_data = getDuplicateDataMysql(mysql_data, gstin, txnId, invDataDto);
                    invDataDto.setDuplicateInMysql(mysql_duplicate_data);
                } catch (RecordException e) {
                    LOGGER.log(Level.SEVERE, "ERROR : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> " +
                            "findDuplicateAndMissingData -> " + FAILED_TO_FETCH_DUPLICATE_DATA_FROM_MYSQL_DB.getValue() +
                            " gstin: " + gstin + " and TxnId: " + txnId + " >> ERROR: " + e);
                    invMap.put(DATA.getValue(), FAILED_TO_FETCH_DUPLICATE_DATA_FROM_MYSQL_DB.getValue() + " gstin = " +
                            gstin + " and txnId = " + txnId);
                }


                try {
                    // --- Finding duplicate data in mongo db ---
                    Set<InvDetailDataDto> mongo_duplicate_data = getDuplicateDataMongo(mongo_data, gstin, txnId, invDataDto);
                    invDataDto.setDuplicateInMongo(mongo_duplicate_data);
                } catch (RecordException e) {
                    LOGGER.log(Level.SEVERE, "ERROR : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> " +
                            "findDuplicateAndMissingData -> " + FAILED_TO_FETCH_DUPLICATE_DATA_FROM_MONGO_DB.getValue() +
                            " gstin: " + gstin + " and TxnId: " + txnId +  " >> ERROR: " + e);
                    invMap.put(DATA.getValue(), FAILED_TO_FETCH_DUPLICATE_DATA_FROM_MONGO_DB.getValue() + " gstin = " +
                            gstin + " and txnId = " + txnId);
                }

                invMap.put(DATA.getValue(), invDataDto);
            }
        }
        LOGGER.log(Level.INFO, "END : CLASS >> FindDuplicateAndMissingDataServiceImpl >> METHOD >> " +
                "findDuplicateAndMissingData with GSTIN: " + gstin);
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
    public Set<InvDetailDataDto> getDuplicateDataMysql(List<Entity_Mysql> mysql_data, String gstin, String txnid,
                                                       InvDataDto invDataDto) {

        LOGGER.log(Level.INFO, "START : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> " +
                "getDuplicateDataMysql with GSTIN: " + gstin);

        Set<String> uniqueInvKeys_mysql = new HashSet<>();
        Set<InvDetailDataDto> duplicateData_mysql = new HashSet<>();

        try {
            for (Entity_Mysql record : mysql_data) {
                String invKey = record.getCtin() + "#" + record.getInum();
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

        } catch (RecordException re) {
            LOGGER.log(Level.SEVERE, "findDuplicateAndMissingServiceImpl :: getDuplicateDataMysql :: " +
                    FAILED_TO_ADD_DUPLICATE_RECORD_IN_LIST + " >> Error: " + re);
            throw new RecordException(ErrorCode.FAILED_TO_ADD_DUPLICATE_RECORD_IN_LIST);
        }

        LOGGER.log(Level.INFO, "END : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> " +
                "getDuplicateDataMysql with GSTIN: " + gstin);
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
    public Set<InvDetailDataDto> getDuplicateDataMongo(List<Entity_Mongo> mongo_data, String gstin, String txnid,
                                                       InvDataDto invDataDto) {

        LOGGER.log(Level.INFO, "START : CLASS >> FindDuplicateAndMissingServiceImpl >> getDuplicateDataMongo " +
                "with GSTIN: " + gstin);

        Set<String> uniqueInvKeys_mongo = new HashSet<>();
        Set<InvDetailDataDto> duplicateData_mongo = new HashSet<>();

        try {
            for (Entity_Mongo record : mongo_data) {
                String invKey = record.getInvKey();
                if (!uniqueInvKeys_mongo.add(invKey)) {
                    InvDetailDataDto duplicates_mongo = new InvDetailDataDto();
                    duplicates_mongo.setDocType(record.getDoctyp());
                    duplicates_mongo.setInum(invKey.split("#")[1]);
                    duplicates_mongo.setIdt(record.getInvDate());
                    duplicates_mongo.setCtin(record.getCtin());
                    duplicates_mongo.setTxnId(txnid);
                    duplicateData_mongo.add(duplicates_mongo);
                }
            }
        } catch (RecordException re) {
            LOGGER.log(Level.SEVERE, "ERROR : FindDuplicateAndMissingServiceImpl >> METHOD >> getDuplicateDataMongo -> "
                    + FAILED_TO_ADD_DUPLICATE_RECORD_IN_LIST + " >> ERROR: " + re);
            throw new RecordException(ErrorCode.FAILED_TO_ADD_DUPLICATE_RECORD_IN_LIST);
        }
        LOGGER.log(Level.INFO, "END : CLASS >> FindDuplicateAndMissingServiceImpl >> getDuplicateDataMongo " +
                "with GSTIN: " + gstin);
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
     * @return Set<InvDetailDataDto> mongodb records missing in mysql
     */
    @Override
    public Set<InvDetailDataDto> getMissingDataMysql(List<Entity_Mongo> mongo_data, List<Entity_Mysql> mysql_data,
                                                     InvDataDto invDataDto, String txnid) {

        Set<String> invKeyList_mysql = new HashSet<>();
        Set<InvDetailDataDto> missingData_mysql = new HashSet<>();
        String gstin = invDataDto.getGstin();

        LOGGER.log(Level.INFO,"START : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> " +
                "getMissingDataMysql with GSTIN: " + gstin);

        try {
            if (!mongo_data.isEmpty() && mysql_data.isEmpty()) {
                LOGGER.log(Level.INFO,"INTERMEDIATE : FindDuplicateAndMissingServiceImpl >> METHOD >> " +
                        "getMissingDataMysql -> " + NO_MONGO_RECORDS_FOUND.getValue() + " gstin: " + gstin + " and " +
                        "TxnId: " + txnid);
                for (Entity_Mongo record : mongo_data) {
                    InvDetailDataDto missing_mysql = new InvDetailDataDto();
                    missing_mysql.setDocType(record.getDoctyp());
                    missing_mysql.setInum(record.getInvKey().split("#")[1]);
                    missing_mysql.setIdt(record.getInvDate());
                    missing_mysql.setCtin(record.getCtin());
                    missing_mysql.setTxnId(txnid);
                    missingData_mysql.add(missing_mysql);
                }

            } else if (!mongo_data.isEmpty() && !mysql_data.isEmpty()){
                LOGGER.log(Level.INFO,"INTERMEDIATE : FindDuplicateAndMissingServiceImpl >> METHOD >> " +
                        "getMissingDataMysql -> " + BOTH_MONGO_AND_MYSQL_RECORDS_ARE_PRESENT.getValue() + " gstin: " +
                        gstin + " and TxnId: " + txnid);
                for (Entity_Mysql record : mysql_data) {
                    String invKey = record.getCtin() + "#" + record.getInum();
                    invKeyList_mysql.add(invKey);
                }

                for (Entity_Mongo record : mongo_data) {
                    if (!invKeyList_mysql.contains(record.getInvKey())) {
                        InvDetailDataDto missing_mysql = new InvDetailDataDto();
                        missing_mysql.setDocType(record.getDoctyp());
                        missing_mysql.setInum(record.getInvKey().split("#")[1]);
                        missing_mysql.setIdt(record.getInvDate());
                        missing_mysql.setCtin(record.getCtin());
                        missing_mysql.setTxnId(txnid);
                        missingData_mysql.add(missing_mysql);
                    }
                }
            }

        } catch(RecordException re) {
            LOGGER.log(Level.SEVERE,"ERROR : CLASS >> FindDuplicateAndMissingServiceImpl >> getMissingDataMysql "
                    + "-> " + FAILED_TO_ADD_MISSING_RECORD_IN_LIST.getValue() + " >> ERROR >> " + re);
            throw new RecordException(ErrorCode.FAILED_TO_ADD_MISSING_RECORD_IN_LIST);
        }

        LOGGER.log(Level.INFO,"END : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> " +
                "getMissingDataMysql with GSTIN: " + gstin);
        return missingData_mysql;
    }

    /**
     * @author Akanksha Senad
     * @Date 26-12-2025
     * @Description This method returns a list of missing records present in mongo database. (In Case)
     * @param mongo_data mongo_data
     * @param mysql_data mysql_data
     * @param invDataDto invDataDto
     * @param txnid txnid
     * @return Set<InvDetailDataDto> mysql records missing in mongo db
     */
    @Override
    public Set<InvDetailDataDto> getMissingDataMongo(List<Entity_Mongo> mongo_data, List<Entity_Mysql> mysql_data,
                                                     InvDataDto invDataDto, String txnid) {

        Set<String> invKeyList_mongo = new HashSet<>();
        Set<InvDetailDataDto> missingData_mongo = new HashSet<>();
        String gstin = invDataDto.getGstin();

        LOGGER.log(Level.INFO,"START : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> " +
                "getMissingDataMongo with GSTIN: " + gstin);

        try {
            if (mongo_data.isEmpty() && !mysql_data.isEmpty()) {
                LOGGER.log(Level.INFO,"INTERMEDIATE : FindDuplicateAndMissingServiceImpl >> METHOD >> " +
                        "getMissingDataMongo -> " + NO_MONGO_RECORDS_FOUND.getValue() + " gstin: " + gstin +
                        " and TxnId: " + txnid);
                for (Entity_Mysql record : mysql_data) {
                    InvDetailDataDto missing_mongo = new InvDetailDataDto();
                    missing_mongo.setDocType(record.getTyp());
                    missing_mongo.setInum(record.getInum());
                    missing_mongo.setIdt(record.getIdt());
                    missing_mongo.setCtin(record.getCtin());
                    missing_mongo.setTxnId(txnid);
                    missingData_mongo.add(missing_mongo);
                }

            } else if(!mongo_data.isEmpty() && !mysql_data.isEmpty()) {
                LOGGER.log(Level.INFO,"INTERMEDIATE : FindDuplicateAndMissingServiceImpl >> METHOD >> " +
                        "getMissingDataMongo -> " + BOTH_MONGO_AND_MYSQL_RECORDS_ARE_PRESENT.getValue() + " gstin: " +
                        gstin + " and TxnId: " + txnid);

                for (Entity_Mongo record : mongo_data) {
                    invKeyList_mongo.add(record.getInvKey());
                }

                for (Entity_Mysql record : mysql_data) {
                    if (!invKeyList_mongo.contains(record.getCtin() + "#" + record.getInum())) {
                        InvDetailDataDto missing_mongo = new InvDetailDataDto();
                        missing_mongo.setDocType(record.getTyp());
                        missing_mongo.setInum(record.getInum());
                        missing_mongo.setIdt(record.getIdt());
                        missing_mongo.setCtin(record.getCtin());
                        missing_mongo.setTxnId(txnid);
                        missingData_mongo.add(missing_mongo);

                    }
                }
            }

        } catch(RecordException re) {
            LOGGER.log(Level.SEVERE,"ERROR : CLASS >> FindDuplicateAndMissingServiceImpl >> getMissingDataMongo "
                    + "-> " + FAILED_TO_ADD_MISSING_RECORD_IN_LIST.getValue() + " >> ERROR >> " + re);
            throw new RecordException(ErrorCode.FAILED_TO_ADD_MISSING_RECORD_IN_LIST);
        }

        LOGGER.log(Level.INFO,"END : CLASS >> FindDuplicateAndMissingServiceImpl >> METHOD >> " +
                "getMissingDataMongo with GSTIN: " + gstin);
        return missingData_mongo;
    }

}