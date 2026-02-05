package com.NFR_RECON.Service.Subscription;

import com.NFR_RECON.Exception.GSPException;

public interface DeleteSubscriptionService {

    Long getGstinId(String gstinNumber) throws GSPException;

    boolean checkPaymentStatus(Long gstinId, String product) throws GSPException;

    String getTableName(String product) throws GSPException;

    int deleteRecFromProductSpecificTable(Long gstinId, String subsId, String query, String errorMsg,
                                          String tableName);

    int deleteRecFromMainTable(Long gstinId, String subsId, String product, String query, String errorMsg,
                               String tableName);

    String deleteRecords(Long gstinId, String subsId, String product) throws GSPException;

    String getQuery(String tableName) throws GSPException;
}
