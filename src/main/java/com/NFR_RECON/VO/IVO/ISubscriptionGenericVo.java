package com.NFR_RECON.VO.IVO;

import java.util.Date;

public interface ISubscriptionGenericVo {

    String getInvoiceId();

    void setInvoiceId(String invoiceId);

    int getFreeTrialPeriod();

    void setFreeTrialPeriod(int freeTrialPeriod);

    String getSubscription_id();

    void setSubscription_id(String subscription_id);

    String getPaymentStatus();

    void setPaymentStatus(String paymentStatus);

    Date getStartDate();

    void setStartDate(Date startDate);

    Date getEndDate();

    void setEndDate(Date endDate);

    long getGstin_id();

    void setGstin_id(long gstin_id);

    String getPlanCode();

    void setPlanCode(String planCode);

    String getPaymentMode();

    void setPaymentMode(String paymentMode);

    long getPartnerId();

    void setPartnerId(long partnerId);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    Date getUpdatedAt();

    void setUpdatedAt(Date updatedAt);
}

