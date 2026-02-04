package com.NFR_RECON.Service.Subscription;

import com.NFR_RECON.Exception.GSPException;

import java.util.List;

public interface DeleteSubscriptionService {

    Long getGstinId(String gstinNumber) throws GSPException;

    boolean checkPayStatus(List<Object[]> getStatus) throws GSPException;

    String getTableName(Long gstinId, String product) throws GSPException;

}
