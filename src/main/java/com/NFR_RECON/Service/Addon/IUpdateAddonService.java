package com.NFR_RECON.Service.Addon;


public interface IUpdateAddonService {

    boolean checkExistance(String gstin, String feature);

    String getLatestSubscriptionId(String gstin, String productName);

    boolean updateAll(Long Id, String endDate, String subscriptionId);

    String verifyRequest(String gstin, String feature, String productName, String endDate);
}
