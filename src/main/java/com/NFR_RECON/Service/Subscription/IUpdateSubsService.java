package com.NFR_RECON.Service.Subscription;


public interface IUpdateSubsService {

    boolean checkExistance(String gstin, String feature);

    String getLatestSubscriptionId(String gstin, String productName);

    boolean updateAll(Long Id, String endDate, String subscriptionId, String updatedAt);

    // TODO: Handle key empty case
    String verifyRequest(String gstin, String feature, String productName, String endDate);
}
