package com.NFR_RECON.ServiceImpl.Addon;

import com.NFR_RECON.Constants.DBQueries;
import com.NFR_RECON.DAO.Dao;
import com.NFR_RECON.Entity.KeyValue;
import com.NFR_RECON.Exception.ErrorCode;
import com.NFR_RECON.Exception.GSPException;
import com.NFR_RECON.Handler.HandlerImpl.RestHandlerImpl;
import com.NFR_RECON.Service.Addon.IUpdateAddonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import static com.NFR_RECON.Constants.Enum.Products.EWAY_BILL;
import static com.NFR_RECON.Constants.Enum.Products.GSTR;

@Service
public class UpdateAddonImpl implements IUpdateAddonService {

    @Autowired
    private Dao dao;

    /**
     * @author Akanksha Senad
     * @since 14/01/2026
     * @Description Checks if any entry exists with provided gstin and feature
     * @param gstin gstin
     * @param feature feature
     * @return boolean value
     */
    @Override
    public boolean checkExistance(String gstin, String feature) {
        List<KeyValue> conditions = new ArrayList<>();
        conditions.add(new KeyValue("gstin", gstin));
        conditions.add(new KeyValue("feature", feature));
        List<Object[]> values = dao.executeSqlQuery(DBQueries.GET_SUBSCRIPTION_DETAILS_BY_GSTIN_AND_FEATURE, conditions);
        if(!values.isEmpty()) {
            Object list = values.get(0);
            RestHandlerImpl.serviceSubsId = Long.parseLong(list.toString());
            return true;

        } else {
            return false;
        }
    }

    /**
     * @author Akanksha Senad
     * @since 15/01/2026
     * @Description Returns subscriptionId by GstinId (from Gstin) and productName
     * @param gstin gstin_number
     * @param productName productName
     * @return String Subscription_ID
     */
    @Override
    public String getLatestSubscriptionId(String gstin, String productName) {
        List<KeyValue> conditions = new ArrayList<>();
        List<Object[]> subIdList;
        Long gstinId;
        String result;

        conditions.add(new KeyValue("gstin", gstin));
        List<Object[]> data = dao.executeSqlQuery(DBQueries.GET_GSTIN_ID_FROM_GSTIN_NUMBER, conditions);
        if(data.isEmpty()) {
            throw new GSPException(ErrorCode.GSTIN_ID_NOT_FOUND + gstin);
        }
        Object list = data.get(0);
        gstinId = Long.parseLong(list.toString());
        conditions.clear();

        if(Objects.equals(productName, GSTR.name())) {
            conditions.add(new KeyValue("gstinId", gstinId));
            subIdList = dao.executeSqlQuery(DBQueries.GET_GSTR_SUBSCRIPTION_ID, conditions);
            conditions.clear();
        } else if(Objects.equals(productName, EWAY_BILL.name())) {
            conditions.add(new KeyValue("gstinId", gstinId));
            subIdList = dao.executeSqlQuery(DBQueries.GET_EWB_SUBSCRIPTION_ID, conditions);
            conditions.clear();
        } else {
            conditions.add(new KeyValue("gstinId", gstinId));
            conditions.add(new KeyValue("productName", productName));
            subIdList = dao.executeSqlQuery(DBQueries.GET_PRODUCT_SUBSCRIPTION_ID, conditions);
        }

        Object subId = subIdList.get(0);
        if(subId == null) {
            throw new GSPException(ErrorCode.SUBSCRIPTION_ID_NOT_FOUND + gstin);
        }
        result = subId.toString();
        return result;
    }

    /**
     * @author Akanksha Senad
     * @since 15/01/2026
     * @Description Updates end_date, subscriptionId and updatedAt
     * @param Id Primary key of Value_Added_Service_Subscription table
     * @param endDate endDate
     * @param subscriptionId subscriptionId
     * @return boolean value
     */
    @Override
    public boolean updateAll(Long Id, String endDate, String subscriptionId) {
        List<KeyValue> conditions = new ArrayList<>();
        conditions.add(new KeyValue("Id", Id));
        conditions.add(new KeyValue("endDate", endDate));
        conditions.add(new KeyValue("subscriptionId", subscriptionId));
        conditions.add(new KeyValue("updatedAt", new Date()));
        int result = dao.updateBySqlQuery(DBQueries.UPDATE_SUBSCRIPTION_DETAILS, conditions);
        return result != 0;
    }

    /**
     * @author Akanksha Senad
     * @since 15/01/2026
     * @Description Verifies if all the fields in request body are not null/ empty
     * @param gstin gstin_number
     * @param feature feature
     * @param product_name product_name
     * @param end_date end_date
     * @return all fields that are null/ empty in request body
     */
    @Override
    public String verifyRequest(String gstin, String feature, String product_name, String end_date) {

        List<String> emptyValues = new ArrayList<>();
        List<String> nullValues = new ArrayList<>();
        List<List<String>> nullAndEmptyValues = new ArrayList<>();

        if(gstin == null) {
            nullValues.add("GSTIN");
        } else if(gstin.isEmpty()) {
            emptyValues.add("GSTIN");
        }

        if(feature == null) {
            nullValues.add("FEATURE");
        } else if(feature.isEmpty()) {
            emptyValues.add("FEATURE");
        }

        if(product_name == null) {
            nullValues.add("PRODUCT_NAME");
        } else if(product_name.isEmpty()) {
            emptyValues.add("PRODUCT_NAME");
        }

        if(end_date == null) {
            nullValues.add("END_DATE");
        } else if(end_date.isEmpty()) {
            emptyValues.add("END_DATE");
        }

        nullAndEmptyValues.add(nullValues);
        nullAndEmptyValues.add(emptyValues);
        return nullAndEmptyValues.toString();
    }
}
