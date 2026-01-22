package com.NFR_RECON.VO;

import com.NFR_RECON.Constants.DbTables;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = DbTables.TBL_TRANSACTION_MANAGER)
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert()
@DynamicUpdate()
public class TransactionManagerVO implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -7664620215936548651L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TRANS_ID")
    private String transId;

    @Column(name = "SAVE_TXN_ID")
    private String saveTxnId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "REF_ID")
    private String refId;

    @Column(name = "REF_STATUS")
    private String refStatus;

    @Column(name = "REF_DATA", columnDefinition = "MEDIUMTEXT")
    private String refData;

    @Column(name = "RETURN_TYPE")
    private String returnType;

    @Column(name = "RETURN_PERIOD")
    private String returnPeriod;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GSTIN_ID")
    private GstinVO gstin;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private UserVO user;

    @Column(name = "TXN_SUB_TYPE")
    private String txnSubType;

    @Column(name = "DRAFT_DATA", columnDefinition = "MEDIUMTEXT")
    private String draftData;

    @Column(name = "FLOW")
    private int flow;

    public TransactionManagerVO() {
        super();
    }

    public TransactionManagerVO(long id, String transId, String status, String returnType, String returnPeriod,
                                GstinVO gstin, UserVO user, String txnSubType, String draftData) {
        super();
        this.id = id;
        this.transId = transId;
        this.status = status;
        this.returnType = returnType;
        this.returnPeriod = returnPeriod;
        this.gstin = gstin;
        this.user = user;
        this.txnSubType = txnSubType;
        this.draftData = draftData;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getRefStatus() {
        return refStatus;
    }

    public void setRefStatus(String refStatus) {
        this.refStatus = refStatus;
    }

    public String getRefData() {
        return refData;
    }

    public void setRefData(String refData) {
        this.refData = refData;
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

    public GstinVO getGstin() {
        return gstin;
    }

    public void setGstin(GstinVO gstin) {
        this.gstin = gstin;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnPeriod() {
        return returnPeriod;
    }

    public void setReturnPeriod(String returnPeriod) {
        this.returnPeriod = returnPeriod;
    }

    public String getTxnSubType() {
        return txnSubType;
    }

    public void setTxnSubType(String txnSubType) {
        this.txnSubType = txnSubType;
    }

    public String getDraftData() {
        return draftData;
    }

    public void setDraftData(String draftData) {
        this.draftData = draftData;
    }

    public String getSaveTxnId() {
        return saveTxnId;
    }

    public void setSaveTxnId(String saveTxnId) {
        this.saveTxnId = saveTxnId;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }
}

