package com.NFR_RECON.Service.Einvoice;

import com.NFR_RECON.Entity.KeyValue;
import com.NFR_RECON.Entity.QueryBean;
import com.NFR_RECON.Exception.GSPException;
import java.util.List;

public interface IService {

    public int updateBySqlQuery(String dbQuery, List<KeyValue> conditions) throws GSPException;
    public List<Object[]> executeSQLQuery(QueryBean dbQuery, List<KeyValue> conditions) throws GSPException;
}
