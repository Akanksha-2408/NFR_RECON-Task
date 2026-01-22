package com.NFR_RECON.VO;

import com.NFR_RECON.Constants.DbTables;
import com.NFR_RECON.Constants.GSPConstant;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = DbTables.TBL_GSTIN_DETAILS)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert()
@DynamicUpdate()
public class GstinDetailsVO implements Serializable {

    private static final long serialVersionUID = 4396136154559251636L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "GSTIN_NUMBER")
    private String gstinNumber;

    @Column(name = "GSTIN_PORTAL_ID")
    private String gstinPortalId;

    @Column(name = "AUTH_TOKEN_TIMEOUT")
    private long authTokenTimeOut;

    @Column(name = "AUTH_TOKEN_CREATEDAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date authTokenCreatedAt;

    @Column(name = "AUTH_TOKEN")
    private String authToken;

    @Column(name = "ENCRYPTION_KEY")
    private String encryptionKey;

    @Column(name = "IS_AUTHENTICATED", columnDefinition = "BIT", length = 1, nullable = false)
    private boolean authenticated;

    @Column(name = "TAX_PAYER_TYPE", length = 50)
    private String taxPayerType;

    @Column(name = "CONSTITUTION_OF_BUSINESS", length = 100)
    private String constitutionOfBusiness;

    @Column(name = "GSTIN_PORTAL_PASSWORD")
    private String portalGstinPassword;

    @Column(name = "AUTHORIZED_SIGNATORY_NAME", length = 50)
    private String authorizedSignatoryName;

    @Column(name = "AUTHORIZED_SIGNATORY_PAN", length = 50)
    private String authorizedSignatoryPan;

    @Column(name = "FILING_PREFERENCE", length = 25)
    private String filingPreference;

    @Column(name = "PERE_GSP_AUTH_TOKEN")
    private String pereGSPAuthToken;

    @Column(name = "PERE_GSP_AUTH_TOKEN_CREATEDAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pereGSPAuthTokenCreatedAt;

    @Column(name = "PRIMARY_GSP", length = 50)
    private String primaryGSP = "PERENNIAL";

    @Column(name = "PERE_GSP_ENCRYPTION_KEY")
    private String pereGspEncryptionKey;

    @Column(name = "NEW_RETURN_FILING_PREFERENCE")
    private String newReturnFilingPreference;

    public String getGstinPortalPassword() {
        return portalGstinPassword;
    }

    public void setPortalPassword(String portalPassword) {
        portalGstinPassword = portalPassword;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGstinPortalId() {
        return gstinPortalId;
    }

    public void setGstinPortalId(String gstinPortalId) {
        this.gstinPortalId = gstinPortalId;
    }

    public String getAuthToken() {
        if (this.getPrimaryGSP().equalsIgnoreCase(GSPConstant.PERENNIAL_GSP)) {
            return getPereGSPAuthToken();
        }
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getEncryptionKey() {
        if (this.getPrimaryGSP().equalsIgnoreCase(GSPConstant.PERENNIAL_GSP)) {
            return getPereGspEncryptionKey();
        }
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public long getAuthTokenTimeOut() {
        return authTokenTimeOut;
    }

    public void setAuthTokenTimeOut(long authTokenTimeOut) {
        this.authTokenTimeOut = authTokenTimeOut;
    }

    public Date getAuthTokenCreatedAt() {
        if (this.getPrimaryGSP().equalsIgnoreCase(GSPConstant.PERENNIAL_GSP)) {
            return getPereGSPAuthTokenCreatedAt();
        }
        return authTokenCreatedAt;
    }

    public void setAuthTokenCreatedAt(Date authTokenCreatedAt) {
        this.authTokenCreatedAt = authTokenCreatedAt;
    }

    public String getGstinNumber() {
        return gstinNumber;
    }

    public void setGstinNumber(String gstinNumber) {
        this.gstinNumber = gstinNumber;
    }

    public String getTaxPayerType() {
        return taxPayerType;
    }

    public void setTaxPayerType(String taxPayerType) {
        this.taxPayerType = taxPayerType;
    }

    public String getConstitutionOfBusiness() {
        return constitutionOfBusiness;
    }

    public void setConstitutionOfBusiness(String constitutionOfBusiness) {
        this.constitutionOfBusiness = constitutionOfBusiness;
    }

    public String getAuthorizedSignatoryName() {
        return authorizedSignatoryName;
    }

    public void setAuthorizedSignatoryName(String authorizedSignatoryName) {
        this.authorizedSignatoryName = authorizedSignatoryName;
    }

    public String getAuthorizedSignatoryPan() {
        return authorizedSignatoryPan;
    }

    public void setAuthorizedSignatoryPan(String authorizedSignatoryPan) {
        this.authorizedSignatoryPan = authorizedSignatoryPan;
    }

    public String getFilingPreference() {
        return filingPreference;
    }

    public void setFilingPreference(String filingPreference) {
        this.filingPreference = filingPreference;
    }

    public String getPereGSPAuthToken() {
        return pereGSPAuthToken;
    }

    public void setPereGSPAuthToken(String pereGSPAuthToken) {
        this.pereGSPAuthToken = pereGSPAuthToken;
    }

    public Date getPereGSPAuthTokenCreatedAt() {
        return pereGSPAuthTokenCreatedAt;
    }

    public void setPereGSPAuthTokenCreatedAt(Date pereGSPAuthTokenCreatedAt) {
        this.pereGSPAuthTokenCreatedAt = pereGSPAuthTokenCreatedAt;
    }

    public String getPrimaryGSP() {
        return primaryGSP;
    }

    public void setPrimaryGSP(String primaryGSP) {
        this.primaryGSP = primaryGSP;
    }

    public String getPereGspEncryptionKey() {
        return pereGspEncryptionKey;
    }

    public void setPereGspEncryptionKey(String pereGspEncryptionKey) {
        this.pereGspEncryptionKey = pereGspEncryptionKey;
    }

    public String getNewReturnFilingPreference() {
        return newReturnFilingPreference;
    }

    public void setNewReturnFilingPreference(String newReturnFilingPreference) {
        this.newReturnFilingPreference = newReturnFilingPreference;
    }
}

