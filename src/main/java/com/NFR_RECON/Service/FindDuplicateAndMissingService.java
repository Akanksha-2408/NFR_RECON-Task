package com.NFR_RECON.Service;

import com.NFR_RECON.DTO.InvDataDto;
import com.NFR_RECON.DTO.InvDetailDataDto;
import com.NFR_RECON.Entity.Entity_Mongo;
import com.NFR_RECON.Entity.Entity_Mysql;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface FindDuplicateAndMissingService {

    Map<String, Object> findDuplicateAndMissingData(String gstin, List<String> txnid);

    Set<InvDetailDataDto> getMissingData_mysql(List<Entity_Mongo> mongo_data, List<Entity_Mysql> mysql_data, InvDataDto invDataDto, String txnid);

    Set<InvDetailDataDto> getMissingData_mongo(List<Entity_Mongo> mongo_data, List<Entity_Mysql> mysql_data, InvDataDto invDataDto, String txnid);

    Set<InvDetailDataDto> getDuplicateData_mysql(List<Entity_Mysql> mysql_data, String gstin, String txnid, InvDataDto invDataDto);

    Set<InvDetailDataDto> getDuplicateData_mongo(List<Entity_Mongo> mongo_data, String gstin, String txnid, InvDataDto invDataDto);

}
