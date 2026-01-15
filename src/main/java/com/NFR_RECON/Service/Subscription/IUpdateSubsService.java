package com.NFR_RECON.Service.Subscription;


public interface IUpdateSubsService {

    boolean checkExistance(String gstin, String feature);

    String getLatestSubscriptionId(String gstin, String productName);

    boolean updateAll(String endDate, String subscriptionId, String updatedAt);

    String verifyRequest(String gstin, String productName, String feature, String endDate);
}
