package com.NFR_RECON.ServiceImpl.Subscription;

import com.NFR_RECON.Constants.DBQueries;
import com.NFR_RECON.Constants.ResponseMessage;
import com.NFR_RECON.DAO.Dao;
import com.NFR_RECON.Entity.KeyValue;
import com.NFR_RECON.Handler.ResponseHandler;
import com.NFR_RECON.Service.Subscription.IUpdateSubsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateSubsServiceImpl implements IUpdateSubsService {

    @Autowired
    private Dao dao;

    @Override
    public boolean checkExistance(String gstin, String feature) {
        List<KeyValue> conditions = new ArrayList<>();
        conditions.add(new KeyValue("gstin", gstin));
        conditions.add(new KeyValue("feature", feature));
        boolean data = dao.executeSqlQuery(DBQueries.GET_SUBSCRIPTION_DETAILS_BY_GSTIN_AND_FEATURE, conditions).isEmpty();
        return data;
    }

    @Override
    public String getLatestSubscriptionId(String gstin, String productName) {
        List<KeyValue> conditions = new ArrayList<>();
        List<Object[]> subIdList;
        Long gstinId;
        String result;

        conditions.add(new KeyValue("gstin", gstin));
        List<Object[]> data = dao.executeSqlQuery(DBQueries.GET_GSTIN_ID_FROM_GSTIN_NUMBER, conditions);
        Object list = data.get(0);
        gstinId = Long.parseLong(list.toString());
        conditions.clear();
        conditions.add(new KeyValue("gstinId", gstinId));

        if(productName == "GSTR") {
            subIdList = dao.executeSqlQuery(DBQueries.GET_GSTR_SUBSCRIPTION_ID, conditions);
        } else if(productName == "EWAYBILL") {
            subIdList = dao.executeSqlQuery(DBQueries.GET_EWB_SUBSCRIPTION_ID, conditions);
        } else {
            subIdList = dao.executeSqlQuery(DBQueries.GET_PRODUCT_SUBSCRIPTION_ID, conditions);
        }

        Object subId = subIdList.get(0);
        result = subId.toString();
        return result;
    }

    @Override
    public boolean updateAll(String endDate, String subscriptionId, String updatedAt) {
        List<KeyValue> conditions = new ArrayList<>();
        conditions.add(new KeyValue("endDate", endDate));
        conditions.add(new KeyValue("subscriptionId", subscriptionId));
        conditions.add(new KeyValue("updatedAt", updatedAt));
        int result = dao.updateBySqlQuery(DBQueries.UPDATE_SUBSCRIPTION_DETAILS, conditions);
        return result == 1;
    }

    @Override
    public String verifyRequest(String gstin, String productName, String feature, String endDate) {

        List<String> nullValues = new ArrayList<>();

        if(gstin == null) {
            nullValues.add("GSTIN");
        }
        if(productName == null) {
            nullValues.add("Product Name");
        }
        if(feature == null) {
            nullValues.add("Feature");
        }
        if(endDate == null) {
            nullValues.add("End Date");
        }

        return nullValues.toString();
    }
}
