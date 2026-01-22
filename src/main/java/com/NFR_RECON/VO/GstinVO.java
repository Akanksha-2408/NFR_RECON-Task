package com.NFR_RECON.VO;

/**
 * @author Prasanna Wagh
 */
import com.NFR_RECON.Constants.DbTables;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = DbTables.TBL_GSTIN)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert()
@DynamicUpdate()
public class GstinVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORG_NAME", length = 50)
    private String orgName;

    @Column(name = "ZOHO_CUST_ID", length = 50)
    private String zohoCustomerId;

    @Column(name = "ORG_UNIT_NAME", length = 50)
    private String orgUnitName;

    @Column(name = "BUSINESS_PAN_NUMBER", length = 50)
    private String businessPANNumber;

    @Column(name = "ORG_CITY", length = 50)
    private String orgCity;

    @Column(name = "PIN_CODE", length = 50)
    private String pincode;

    @Column(name = "ORG_ADDRESS", columnDefinition = "TEXT")
    private String orgAddress;

    @Column(name = "ORG_COUNTRY", length = 50)
    private String orgCountry;

    @Column(name = "STATUS", length = 30)
    private String status;

    @Column(name = "UUID", length = 32)
    private String uuid;

    @Column(name = "IS_DELETED", columnDefinition = "BIT", length = 1)
    private boolean isDeleted;

    @Column(name = "CREATEDAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATEDAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "ACTIVATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GSTIN_DETAILS_ID")
    private GstinDetailsVO gstinDetails;

    @Column(name = "IS_PRACTICE", columnDefinition = "BIT", length = 1)
    private boolean isPractice;

    @Column(name = "MAPPING_TEMPLATE_GSTR1")
    private Integer mappingTemplateForGSTR1;

    @Column(name = "MAPPING_TEMPLATE_GSTR2")
    private Integer mappingTemplateForGSTR2;

    @Column(name = "MAPPING_TEMPLATE_EWB")
    private Integer mappingTemplateForEwb;

    @Column(name = "DELTA_VAL", nullable = false, columnDefinition = "decimal(5,2) default 0")
    private double deltaVal;

    @Column(name = "DELTA_TX_VAL", nullable = false, columnDefinition = "decimal(5,2) default 0")
    private double deltaTaxVal;

    @Column(name = "DELTA_TX_AMT", nullable = false, columnDefinition = "decimal(5,2) default 0")
    private double deltaTxAmt;

    @Column(name = "PAYER_ID")
    private Long payerId;

    @Column(name = "IGNORE_PRE_ZEROS", columnDefinition = "BIT", length = 1, nullable = false)
    private boolean ignorePreZeros;

    @Column(name = "IGNORE_SPECIAL_CHARS", columnDefinition = "BIT", length = 1, nullable = false)
    private boolean ignoreSpecialChars;

    @Column(name = "IGNORE_DT_DIFF", columnDefinition = "BIT", length = 1, nullable = false)
    private boolean ignoreDtDiff;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Column(name = "MAPPING_TEMPLATE_EINV")
    private Integer mappingTemplateForEinv;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SUBSCRIPTION_DETAILS_ID")
    private SubscriptionDetailsVO subscriptionDetails;

    @Column(name = "MAPPING_TEMPLATE_OUTWARD")
    private Integer mappingTemplateForOutward;

    @Column(name = "MAPPING_TEMPLATE_INWARD")
    private Integer mappingTemplateForInward;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getZohoCustomerId() {
        return zohoCustomerId;
    }

    public void setZohoCustomerId(String zohoCustomerId) {
        this.zohoCustomerId = zohoCustomerId;
    }

    public String getOrgUnitName() {
        return orgUnitName;
    }

    public void setOrgUnitName(String orgUnitName) {
        this.orgUnitName = orgUnitName;
    }

    public String getBusinessPANNumber() {
        return businessPANNumber;
    }

    public void setBusinessPANNumber(String businessPANNumber) {
        this.businessPANNumber = businessPANNumber;
    }

    public String getOrgCity() {
        return orgCity;
    }

    public void setOrgCity(String orgCity) {
        this.orgCity = orgCity;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgCountry() {
        return orgCountry;
    }

    public void setOrgCountry(String orgCountry) {
        this.orgCountry = orgCountry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(Date activatedAt) {
        this.activatedAt = activatedAt;
    }

    public boolean isPractice() {
        return isPractice;
    }

    public void setPractice(boolean practice) {
        isPractice = practice;
    }

    public Integer getMappingTemplateForGSTR1() {
        return mappingTemplateForGSTR1;
    }

    public void setMappingTemplateForGSTR1(Integer mappingTemplateForGSTR1) {
        this.mappingTemplateForGSTR1 = mappingTemplateForGSTR1;
    }

    public Integer getMappingTemplateForGSTR2() {
        return mappingTemplateForGSTR2;
    }

    public void setMappingTemplateForGSTR2(Integer mappingTemplateForGSTR2) {
        this.mappingTemplateForGSTR2 = mappingTemplateForGSTR2;
    }

    public Integer getMappingTemplateForEwb() {
        return mappingTemplateForEwb;
    }

    public void setMappingTemplateForEwb(Integer mappingTemplateForEwb) {
        this.mappingTemplateForEwb = mappingTemplateForEwb;
    }

    public double getDeltaVal() {
        return deltaVal;
    }

    public void setDeltaVal(double deltaVal) {
        this.deltaVal = deltaVal;
    }

    public double getDeltaTaxVal() {
        return deltaTaxVal;
    }

    public void setDeltaTaxVal(double deltaTaxVal) {
        this.deltaTaxVal = deltaTaxVal;
    }

    public double getDeltaTxAmt() {
        return deltaTxAmt;
    }

    public void setDeltaTxAmt(double deltaTxAmt) {
        this.deltaTxAmt = deltaTxAmt;
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public boolean isIgnorePreZeros() {
        return ignorePreZeros;
    }

    public void setIgnorePreZeros(boolean ignorePreZeros) {
        this.ignorePreZeros = ignorePreZeros;
    }

    public boolean isIgnoreSpecialChars() {
        return ignoreSpecialChars;
    }

    public void setIgnoreSpecialChars(boolean ignoreSpecialChars) {
        this.ignoreSpecialChars = ignoreSpecialChars;
    }

    public boolean isIgnoreDtDiff() {
        return ignoreDtDiff;
    }

    public void setIgnoreDtDiff(boolean ignoreDtDiff) {
        this.ignoreDtDiff = ignoreDtDiff;
    }

    public Integer getMappingTemplateForEinv() {
        return mappingTemplateForEinv;
    }

    public void setMappingTemplateForEinv(Integer mappingTemplateForEinv) {
        this.mappingTemplateForEinv = mappingTemplateForEinv;
    }

    public Integer getMappingTemplateForOutward() {
        return mappingTemplateForOutward;
    }

    public void setMappingTemplateForOutward(Integer mappingTemplateForOutward) {
        this.mappingTemplateForOutward = mappingTemplateForOutward;
    }

    public Integer getMappingTemplateForInward() {
        return mappingTemplateForInward;
    }

    public void setMappingTemplateForInward(Integer mappingTemplateForInward) {
        this.mappingTemplateForInward = mappingTemplateForInward;
    }

    public GstinDetailsVO getGstinDetails() {
        return gstinDetails;
    }

    public void setGstinDetails(GstinDetailsVO gstinDetails) {
        this.gstinDetails = gstinDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SubscriptionDetailsVO getSubscriptionDetails() {
        if (subscriptionDetails == null) {
            subscriptionDetails = new SubscriptionDetailsVO();
        }
        return subscriptionDetails;
    }
}

