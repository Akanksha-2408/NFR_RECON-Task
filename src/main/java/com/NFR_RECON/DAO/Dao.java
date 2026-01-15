package com.NFR_RECON.DAO;

import com.NFR_RECON.Entity.KeyValue;
import com.NFR_RECON.Entity.QueryBean;
import com.NFR_RECON.Exception.GSPException;
import java.util.List;

public interface Dao {

    List<Object[]> executeSqlQuery(QueryBean sQuery, List<KeyValue> conditions) throws GSPException;
    int updateBySqlQuery(String dbQuery, List<KeyValue> conditions1) throws GSPException;

}