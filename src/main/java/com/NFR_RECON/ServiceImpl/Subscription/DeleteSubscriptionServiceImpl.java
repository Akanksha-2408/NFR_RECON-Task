package com.NFR_RECON.ServiceImpl.Subscription;

import com.NFR_RECON.Constants.DBQueries;
import com.NFR_RECON.Constants.DbTables;
import com.NFR_RECON.Constants.Enum.Products;
import com.NFR_RECON.Constants.ResponseMessage;
import com.NFR_RECON.DAO.Dao;
import com.NFR_RECON.Entity.KeyValue;
import com.NFR_RECON.Exception.ErrorCode;
import com.NFR_RECON.Exception.GSPException;
import com.NFR_RECON.Service.Subscription.DeleteSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class DeleteSubscriptionServiceImpl implements DeleteSubscriptionService {

    @Autowired
    private Dao dao;

    public static final Logger LOGGER = Logger.getLogger(DeleteSubscriptionServiceImpl.class.getName());
    public static List<KeyValue> conditions = new ArrayList<>();

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
    public boolean checkPaymentStatus(Long gstinId, String product) throws GSPException {

        boolean isGroupSubscripion = false;
        List<Object[]> getStatus = new ArrayList<>();
        List<KeyValue> conditions = new ArrayList<>();
        conditions.add(new KeyValue("gstinId", gstinId));

        if (product.equalsIgnoreCase(Products.GSTR.getDescription())) {
            getStatus = dao.executeSqlQuery(DBQueries.GET_GSTR_PAYMENT_STATUS_BY_GSTIN_ID, conditions);
        } else if (product.equalsIgnoreCase(Products.EWAY_BILL.getDescription())) {
            getStatus = dao.executeSqlQuery(DBQueries.GET_EWAY_BILL_PAYMENT_STATUS_BY_GSTIN_ID, conditions);
        } else if (product.equalsIgnoreCase(Products.E_INVOICE.getDescription())) {
            getStatus = dao.executeSqlQuery(DBQueries.GET_E_INVOICE_PAYMENT_STATUS_BY_GSTIN_ID, conditions);
        }
        if (!getStatus.isEmpty()) {
            for (Object data : getStatus) {
                String status = data.toString();
                if (status.equalsIgnoreCase("GROUP_SUBSCRIPTION")) {
                    isGroupSubscripion = true;
                    return isGroupSubscripion;
                }
            }
        }
        return isGroupSubscripion;
    }

    @Override
    public String getTableName(String product) throws GSPException {

        String tableName = null;
        if(product.equalsIgnoreCase(Products.GSTR.getDescription())) {
            tableName = DbTables.TBL_SUBSCRIPTION_DETAILS.toUpperCase();
        } else if(product.equalsIgnoreCase(Products.EWAY_BILL.getDescription())) {
            tableName = DbTables.TBL_EWB_SUBSCRIPTION_DETAILS.toUpperCase();
        } else if(product.equalsIgnoreCase(Products.E_INVOICE.getDescription())) {
            tableName = "EINV_SUBSCRIPTION_DETAILS";
        }
        return tableName;

    }

    @Override
    public int deleteRecFromProductSpecificTable(Long gstinId, String subsId, String query, String errorMsg,
                                              String tableName) {

        int deletedRecord1;
        List<KeyValue> conditions = new ArrayList<>();
        conditions.add(new KeyValue("gstinId", gstinId));
        conditions.add(new KeyValue("subsId", subsId));

        try {
            deletedRecord1 = dao.updateBySqlQuery(query, conditions);
            if(deletedRecord1 == 0) {
                return 0;
            }
        } catch(GSPException ex) {
            throw new GSPException(errorMsg + tableName + gstinId.toString());
        }
        return 1;

    }

    @Override
    public int deleteRecFromMainTable(Long gstinId, String subsId, String product, String query, String errorMsg,
                                                 String tableName) {

        int deletedRecord2;
        List<KeyValue> conditions = new ArrayList<>();
        conditions.add(new KeyValue("gstinId", gstinId));
        conditions.add(new KeyValue("subsId", subsId));
        conditions.add(new KeyValue("product", product));

        try {
            deletedRecord2 = dao.updateBySqlQuery(
                    DBQueries.DELETE_FROM_GROUP_SUBSCRIPTION_MAPPING_BY_GSTIN_ID_AND_SUBS_ID_AND_PRODUCT_NAME,
                    conditions);
            if(deletedRecord2 == 0) {
                return 0;
            }
        } catch (GSPException ex) {
            throw new GSPException(ErrorCode.FAILED_TO_DELETE_DATA_FROM_GROUP_SUBSCRIPTION_MAPPING + gstinId.toString());
        }
        return 1;
    }

    @Override
    public String getQuery(String tableName) {
        return "DELETE FROM " + tableName + " WHERE GSTIN_ID = :gstinId AND SUBSCRIPTION_ID = :subsId";
    }

    @Override
    public String deleteRecords(Long gstinId, String subsId, String product) throws GSPException {

        String tableName = getTableName(product);
        String query = getQuery(tableName);
        String errorMsg = "ErrorCode.FAILED_TO_DELETE_DATA_FROM_" + tableName;

        int rec1 = deleteRecFromProductSpecificTable(gstinId, subsId, query,  errorMsg, tableName);
        if(rec1 == 0) {
            return ResponseMessage.RECORDS_NOT_FOUND + tableName;
        }
        int rec2 = deleteRecFromMainTable(gstinId, subsId, product, query,  errorMsg, tableName);
        if(rec2 == 0) {
            return ResponseMessage.RECORDS_NOT_FOUND + "GROUP_SUBSCRIPTION_MAPPING";
        }
        return null;
    }
}