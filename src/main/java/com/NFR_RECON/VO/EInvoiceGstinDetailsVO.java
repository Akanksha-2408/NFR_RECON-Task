package com.NFR_RECON.VO;

import com.NFR_RECON.Constants.DbTables;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = DbTables.TBL_EINV_GSTIN_DETAILS)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert()
@DynamicUpdate()
public class EInvoiceGstinDetailsVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5832058592512936173L;

    /** The id. */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /** The username. */
    @Column(name = "USER_NAME")
    private String username;

    /** The password. */
    @Column(name = "PASSWORD")
    private String password;

    /** The auth token time out. */
    @Column(name = "AUTH_TOKEN_TIMEOUT")
    private long authTokenTimeOut;

    /** The auth token created at. */
    @Column(name = "AUTH_TOKEN_CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date authTokenCreatedAt;

    /** The auth token. */
    @Column(name = "AUTH_TOKEN")
    private String authToken;

    /** The encryption key. */
    @Column(name = "ENCRYPTION_KEY")
    private String encryptionKey;

    /** The authenticated. */
    @Column(name = "IS_AUTHENTICATED", columnDefinition = "BIT", length = 1, nullable = false)
    private boolean authenticated;

    /** The created at. */
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /** The updated at. */
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "GSTIN")
    private String gstin;

    @Column(name = "ERP_ID")
    private String erpId;

    @Column(name = "UUID")
    private String uuid;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAuthTokenTimeOut() {
        return authTokenTimeOut;
    }

    public void setAuthTokenTimeOut(long authTokenTimeOut) {
        this.authTokenTimeOut = authTokenTimeOut;
    }

    public Date getAuthTokenCreatedAt() {
        return authTokenCreatedAt;
    }

    public void setAuthTokenCreatedAt(Date authTokenCreatedAt) {
        this.authTokenCreatedAt = authTokenCreatedAt;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
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

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getErpId() {
        return erpId;
    }

    public void setErpId(String erpId) {
        this.erpId = erpId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Instantiates a new ewb user details VO.
     */
    public EInvoiceGstinDetailsVO() {
        super();
    }

}
