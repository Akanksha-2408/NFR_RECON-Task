package com.NFR_RECON.VO;

import com.NFR_RECON.Constants.DbTables;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = DbTables.TBL_LATEST_GSTR2B_MULTIMONTH_RECON_DETAILS)
@DynamicInsert()
@DynamicUpdate()
public class Gstr2bMultimonthLatestDetailsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    @Column(name = "gstr2b_from_rtnprd")
    private String gstr2bFromReturnPeriod;

    @Column(name = "gstr2b_to_rtnprd")
    private String gstr2bToReturnPeriod;

    @Column(name = "pr_from_rtnprd")
    private String prFromReturnPeriod;

    @Column(name = "pr_to_rtnprd")
    private String prToReturnPeriod;

    @Column(name = "GSTIN_ID")
    private long gstinId;

    @Column(name = "gstin")
    private String gstin;

    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "status")
    private String status;

    @Column(name = "tot_sup_cnt")
    private int totSupCount;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getGstr2bFromReturnPeriod() {
        return gstr2bFromReturnPeriod;
    }

    public void setGstr2bFromReturnPeriod(String gstr2bFromReturnPeriod) {
        this.gstr2bFromReturnPeriod = gstr2bFromReturnPeriod;
    }

    public String getGstr2bToReturnPeriod() {
        return gstr2bToReturnPeriod;
    }

    public void setGstr2bToReturnPeriod(String gstr2bToReturnPeriod) {
        this.gstr2bToReturnPeriod = gstr2bToReturnPeriod;
    }

    public String getPrFromReturnPeriod() {
        return prFromReturnPeriod;
    }

    public void setPrFromReturnPeriod(String prFromReturnPeriod) {
        this.prFromReturnPeriod = prFromReturnPeriod;
    }

    public String getPrToReturnPeriod() {
        return prToReturnPeriod;
    }

    public void setPrToReturnPeriod(String prToReturnPeriod) {
        this.prToReturnPeriod = prToReturnPeriod;
    }

    public Long getGstinId() {
        return gstinId;
    }

    public void setGstinId(Long gstinId) {
        this.gstinId = gstinId;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    @Column(name = "IS_DIRTY", columnDefinition = "BIT", length = 1, nullable = false)
    private boolean isDirty;


    public Gstr2bMultimonthLatestDetailsVO(Date createdAt, Date updatedAt, String gstr2bFromReturnPeriod, String gstr2bToReturnPeriod, String prFromReturnPeriod, String prToReturnPeriod, GstinVO gstinId, String gstin) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.gstr2bFromReturnPeriod = gstr2bFromReturnPeriod;
        this.gstr2bToReturnPeriod = gstr2bToReturnPeriod;
        this.prFromReturnPeriod = prFromReturnPeriod;
        this.prToReturnPeriod = prToReturnPeriod;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotSupCount() {
        return totSupCount;
    }

    public void setTotSupCount(int totSupCount) {
        this.totSupCount = totSupCount;
    }

    public Gstr2bMultimonthLatestDetailsVO() {
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        isDirty = dirty;
    }
}
