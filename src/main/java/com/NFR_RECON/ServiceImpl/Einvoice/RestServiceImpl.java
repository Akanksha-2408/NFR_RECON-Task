package com.NFR_RECON.ServiceImpl.Einvoice;

import com.NFR_RECON.Constants.DBQueries;
import com.NFR_RECON.DAO.Dao;
import com.NFR_RECON.Entity.KeyValue;
import com.NFR_RECON.Service.Einvoice.IRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestServiceImpl implements IRestService {

    @Autowired
    private Dao dao;

    @Override
    public long getEinvGstinDetailsIdByGstinAndUserName(String gstin) {
        List<KeyValue> conditions = new ArrayList<>();
        conditions.add(new KeyValue("gstin", gstin));
        List<Object[]> idList = dao.executeSqlQuery(DBQueries.GET_EINV_GSTIN_DETAILS_ID_BY_GSTIN, conditions);
        Object list = idList.get(0);
        return Long.parseLong(list.toString());
    }

    @Override
    public boolean updateUsername(Long id, String username) {
        List<KeyValue> conditions = new ArrayList<>();
        conditions.add(new KeyValue("einvGstinDetailsId", id));
        conditions.add(new KeyValue("username", username));
        int data = dao.updateBySqlQuery(DBQueries.UPDATE_USERNAME_BY_ID, conditions);
        return data != 0;
    }
}
