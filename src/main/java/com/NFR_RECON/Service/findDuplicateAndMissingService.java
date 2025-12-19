package com.NFR_RECON.Service;

import com.NFR_RECON.DTO.InvDataDto;
import com.NFR_RECON.DTO.InvDetailDataDto;
import com.NFR_RECON.Entity.classForMongo;
import com.NFR_RECON.Entity.classForMysql;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public interface findDuplicateAndMissingService {

    Map<String, Object> findDuplicateAndMissingData(String gstin, List<String> txnid);

    Set<InvDetailDataDto> getMissingData_mysql(List<classForMongo> mongo_data, List<classForMysql> mysql_data, InvDataDto invDataDto, String txnid);

    Set<InvDetailDataDto> getDuplicateData_mysql(List<classForMysql> mysql_data, String gstin, String txnid, InvDataDto invDataDto);

    Set<InvDetailDataDto> getDuplicateData_mongo(List<classForMongo> mongo_data, String gstin, String txnid, InvDataDto invDataDto);

}
