package com.NFR_RECON.Entity.VO;

import java.util.Date;

import com.NFR_RECON.Constants.DbTables;
import com.NFR_RECON.Util.StringUtil;
import com.NFR_RECON.Entity.VO.IVO.ISubscriptionGenericVo;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = DbTables.TBL_SUBSCRIPTION_DETAILS)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert()
@DynamicUpdate()
public class SubscriptionDetailsVO implements ISubscriptionGenericVo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "GSTIN_ID")
    private long gstin_id;

    @Column(name = "PARTNER_ID")
    private long partnerId;

    @Column(name = "SUBSCRIPTION_ID", length = 50)
    private String subscription_id;

    @Column(name = "PAYMENT_STATUS", length = 50)
    private String paymentStatus;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "PLAN_CODE", length = 30)
    private String planCode;

    @Column(name = "PAYMENT_MODE", length = 30)
    private String paymentMode;

    @Column(name = "EXTENDED_MONTHS")
    private int extendedMonths;

    @Column(name = "STATUS", length = 30)
    private String status;

    @Column(name = "CREATEDAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATEDAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name="RET_ACCESSED_FROM")
    private Date returnAccessedFrom;

    @Column(name="FREE_TRIAL_PERIOD")
    private int freeTrialPeriod;

    @Column(name="INVOICE_ID")
    private String invoiceId;

    @Column(name = "PAYER_ID")
    private long payerId;

    @Column(name = "IS_PRACTICE", columnDefinition = "BIT", length = 1)
    private boolean isPractice;

    @Column(name="IS_MERGED_VIEW",columnDefinition = "BIT", length = 1)
    private boolean isMergedView;

    @Override
    public String getInvoiceId() {
        return invoiceId;
    }

    @Override
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public int getFreeTrialPeriod() {
        return freeTrialPeriod;
    }

    @Override
    public void setFreeTrialPeriod(int freeTrialPeriod) {
        this.freeTrialPeriod = freeTrialPeriod;
    }

    public Date getReturnAccessedFrom() {
        return returnAccessedFrom;
    }

    public void setReturnAccessedFrom(Date returnAccessedFrom) {
        this.returnAccessedFrom = returnAccessedFrom;
    }

    public SubscriptionDetailsVO() {
        this.setCreatedAt(new Date());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getSubscription_id() {
        return subscription_id;
    }

    @Override
    public void setSubscription_id(String subscription_id) {
        this.subscription_id = subscription_id;
    }

    @Override
    public String getPaymentStatus() {
        return StringUtil.isBlank(paymentStatus) ? "" : paymentStatus;
    }

    @Override
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getExtendedMonths() {
        return extendedMonths;
    }

    public void setExtendedMonths(int extendedMonths) {
        this.extendedMonths = extendedMonths;
    }

    @Override
    public long getGstin_id() {
        return gstin_id;
    }

    @Override
    public void setGstin_id(long gstin_id) {
        this.gstin_id = gstin_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getPlanCode() {
        return planCode;
    }

    @Override
    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    @Override
    public String getPaymentMode() {
        return paymentMode;
    }

    @Override
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public long getPartnerId() {
        return partnerId;
    }

    @Override
    public void setPartnerId(long partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getPayerId() {
        return payerId;
    }

    public void setPayerId(long payerId) {
        this.payerId = payerId;
    }

    public boolean isPractice() {
        return isPractice;
    }

    public void setPractice(boolean isPractice) {
        this.isPractice = isPractice;
    }
    public boolean isMergedView() {
        return isMergedView;
    }

    public void setIsMergedView(boolean isMergedView) {
        this.isMergedView = isMergedView;
    }

}
