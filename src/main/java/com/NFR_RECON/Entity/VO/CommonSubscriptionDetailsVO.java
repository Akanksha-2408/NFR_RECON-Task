package com.NFR_RECON.Entity.VO;

import java.util.Date;
import java.util.Set;

import com.NFR_RECON.Constants.DbTables;
import com.NFR_RECON.Entity.VO.IVO.ISubscriptionGenericVo;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = DbTables.TBL_COMMON_SUBSCRIPTION)
@DynamicInsert()
@DynamicUpdate()
public class CommonSubscriptionDetailsVO implements ISubscriptionGenericVo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "plan_code", length = 50)
    private String planCode;

    @Column(name = "product_name", length = 50)
    private String productName;

    @Column(name = "payment_status", length = 50)
    private String paymentStatus;

    @Column(name = "payment_mode", length = 50)
    private String paymentMode;

    @Column(name = "gstin_id")
    private long gstinId;

    @Column(name = "partner_id")
    private long partnerId;

    @Column(name = "subscription_id", length = 50)
    private String subscriptionId;

    @Column(name = "invoice_id", length = 50)
    private String invoiceId;

    @Column(name="free_trial_period", columnDefinition = "SMALLINT")
    private int freeTrialPeriod;

    @Column(name = "other_details", length = 2000)
    private String otherDetails;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(mappedBy = "commonSubscriptionDetailsVO", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PurchasedCommonSubAddOnVO> purchasedAddons;

    @Column(name = "payerId")
    private long payer;

    @Column(name = "zoho_customer_id")
    private String zohoCustId;

    public CommonSubscriptionDetailsVO() {
        this.setCreatedAt(new Date());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String getPaymentStatus() {
        return paymentStatus;
    }

    @Override
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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
    public long getGstin_id() {
        return gstinId;
    }

    @Override
    public void setGstin_id(long gstinId) {
        this.gstinId = gstinId;
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
    public String getSubscription_id() {
        return subscriptionId;
    }

    @Override
    public void setSubscription_id(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

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

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
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

    public Set<PurchasedCommonSubAddOnVO> getPurchasedAddons() {
        return purchasedAddons;
    }

    public void setPurchasedAddons(Set<PurchasedCommonSubAddOnVO> purchasedAddons) {
        this.purchasedAddons = purchasedAddons;
    }

    public long getPayerId() {
        return payer;
    }

    public void setPayer(long payer) {
        this.payer = payer;
    }

    public String getZohoCustId() {
        return zohoCustId;
    }

    public void setZohoCustId(String zohoCustId) {
        this.zohoCustId = zohoCustId;
    }
}
