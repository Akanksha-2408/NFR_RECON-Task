package com.NFR_RECON.ServiceImpl.Einvoice;

import com.NFR_RECON.DAO.Dao;
import com.NFR_RECON.Entity.KeyValue;
import com.NFR_RECON.Entity.QueryBean;
import com.NFR_RECON.Exception.GSPException;
import com.NFR_RECON.Service.Einvoice.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceImpl implements IService {

    @Autowired
    private Dao dao;

    @Override
    public int updateBySqlQuery(String dbQuery, List<KeyValue> conditions) throws GSPException {
        return dao.updateBySqlQuery(dbQuery, conditions);
    }

    @Override
    public List<Object[]> executeSQLQuery(QueryBean dbQuery, List<KeyValue> conditions) throws GSPException {
        return dao.executeSqlQuery(dbQuery, conditions);
    }

}
