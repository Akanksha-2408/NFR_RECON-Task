package com.NFR_RECON.ServiceImpl.Subscription;

import com.NFR_RECON.Constants.DBQueries;
import com.NFR_RECON.Constants.Enum.Products;
import com.NFR_RECON.DAO.Dao;
import com.NFR_RECON.Entity.KeyValue;
import com.NFR_RECON.Exception.GSPException;
import com.NFR_RECON.Service.Subscription.DeleteSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeleteSubscriptionServiceImpl implements DeleteSubscriptionService {

    @Autowired
    private Dao dao;

    @Override
    public Long getGstinId(String gstin) throws GSPException {

        List<KeyValue> conditions = new ArrayList<>();
        conditions.add(new KeyValue("gstin", gstin));
        List<Object[]> data = dao.executeSqlQuery(DBQueries.GET_GSTIN_ID_FROM_GSTIN_NUMBER, conditions);
        Object list = data.get(0);
        Long gstinId = Long.parseLong(list.toString());
        conditions.clear();
        return gstinId;

    }

    @Override
    public boolean checkPayStatus(List<Object[]> getStatus) throws GSPException {
        boolean flag = false;
        if(!getStatus.isEmpty()) {
            for(Object data : getStatus) {
                String status = data.toString();
                if(status.equalsIgnoreCase("GROUP_SUBSCRIPTION")) {
                    flag = true;
                    return flag;
                }
            }
        }
        return flag;
    }


    @Override
    public String getTableName(Long gstinId, String product) throws GSPException {

        List<KeyValue> conditions = new ArrayList<>();
        List<Object[]> getStatus = null;
        boolean flag = false;
        conditions.add(new KeyValue("gstinId", gstinId));

        if(product.equalsIgnoreCase(Products.GSTR.getDescription())) {
            // get status -> if group_subscription, delete it from both tables
            getStatus = dao.executeSqlQuery(DBQueries.GET_GSTR_PAYMENT_STATUS_BY_GSTIN, conditions);
            if(checkPayStatus(getStatus)) {

            }

        } else if(product.equalsIgnoreCase(Products.EWAY_BILL.getDescription())) {
            getStatus = dao.executeSqlQuery(DBQueries.GET_EWB_PAYMENT_STATUS_BY_GSTIN, conditions);
            if(checkPayStatus(getStatus)) {

            }

        } else if(product.equalsIgnoreCase(Products.E_INVOICE.getDescription())) {
            getStatus = dao.executeSqlQuery(DBQueries.GET_EINVOICE_PAYMENT_STATUS_BY_GSTIN, conditions);
            if(checkPayStatus(getStatus)) {

            }
        }
        return "";
    }
}
