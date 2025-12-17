package com.NFR_RECON.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.bson.BsonInt32;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import java.sql.Date;
import java.util.ArrayList;

@Document(collection = "GSTR_INVOICE_DETAILS_INWARD")
@Data
public class classForMongo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId id;
    private Long usrId;
    private BsonInt32 gstinId;
    private String gstin;
    private String retPeriod;
    private String retType;
    private String txnId;
    private String fileType;
    private String invKey;
    private String status;
    private String migrationStatus;
    private Date createdAt;
    private Date updatedAt;
    private ArrayList<String> headerKeys;

    // --- Invoice level fields ---

    private String extDocType;
    private Date invDate;
    private int pos;
    private String cusName;
    private double invVal;
    private double taxAmnt;
    private double taxVal;
    private double cgst;
    private double sgst;
    private double igst;
    private double cess;
    private String rchrg;
    private String splyType;
    private boolean multiValue;

    // --- V2.0 fields ---

    private String doctyp;
    private String ctin;
    private String subTxnId;
    private String sec7act;
    private String clmrfnd;
    private String rfndelg;
    private String onum;
    private String odt;
    private String gstnStatus;
    private String ewbStatus;
    private String tabType;
    private String gstr1Status;
    private String anx1Status;
    private String usrName;
    private String anxStatus;
    private String custom1;
    private String custom2;
    private String custom3;
    private String custom4;
    private String custom5;
    private String custom6;
    private String custom7;
    private String invType;

    // Getters and Setters

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public BsonInt32 getGstinId() {
        return gstinId;
    }

    public void setGstinId(BsonInt32 gstinId) {
        this.gstinId = gstinId;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getRetPeriod() {
        return retPeriod;
    }

    public void setRetPeriod(String retPeriod) {
        this.retPeriod = retPeriod;
    }

    public String getRetType() {
        return retType;
    }

    public void setRetType(String retType) {
        this.retType = retType;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getInvKey() {
        return invKey;
    }

    public void setInvKey(String invKey) {
        this.invKey = invKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public ArrayList<String> getHeaderKeys() {
        return headerKeys;
    }

    public void setHeaderKeys(ArrayList<String> headerKeys) {
        this.headerKeys = headerKeys;
    }

    public String getExtDocType() {
        return extDocType;
    }

    public void setExtDocType(String extDocType) {
        this.extDocType = extDocType;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public double getInvVal() {
        return invVal;
    }

    public void setInvVal(double invVal) {
        this.invVal = invVal;
    }

    public double getTaxAmnt() {
        return taxAmnt;
    }

    public void setTaxAmnt(double taxAmnt) {
        this.taxAmnt = taxAmnt;
    }

    public double getTaxVal() {
        return taxVal;
    }

    public void setTaxVal(double taxVal) {
        this.taxVal = taxVal;
    }

    public double getCgst() {
        return cgst;
    }

    public void setCgst(double cgst) {
        this.cgst = cgst;
    }

    public double getSgst() {
        return sgst;
    }

    public void setSgst(double sgst) {
        this.sgst = sgst;
    }

    public double getIgst() {
        return igst;
    }

    public void setIgst(double igst) {
        this.igst = igst;
    }

    public double getCess() {
        return cess;
    }

    public void setCess(double cess) {
        this.cess = cess;
    }

    public String getRchrg() {
        return rchrg;
    }

    public void setRchrg(String rchrg) {
        this.rchrg = rchrg;
    }

    public String getSplyType() {
        return splyType;
    }

    public void setSplyType(String splyType) {
        this.splyType = splyType;
    }

    public boolean isMultiValue() {
        return multiValue;
    }

    public void setMultiValue(boolean multiValue) {
        this.multiValue = multiValue;
    }

    public String getDoctyp() {
        return doctyp;
    }

    public void setDoctyp(String doctyp) {
        this.doctyp = doctyp;
    }

    public String getCtin() {
        return ctin;
    }

    public void setCtin(String ctin) {
        this.ctin = ctin;
    }

    public String getSubTxnId() {
        return subTxnId;
    }

    public void setSubTxnId(String subTxnId) {
        this.subTxnId = subTxnId;
    }

    public String getSec7act() {
        return sec7act;
    }

    public void setSec7act(String sec7act) {
        this.sec7act = sec7act;
    }

    public String getClmrfnd() {
        return clmrfnd;
    }

    public void setClmrfnd(String clmrfnd) {
        this.clmrfnd = clmrfnd;
    }

    public String getRfndelg() {
        return rfndelg;
    }

    public void setRfndelg(String rfndelg) {
        this.rfndelg = rfndelg;
    }

    public String getOnum() {
        return onum;
    }

    public void setOnum(String onum) {
        this.onum = onum;
    }

    public String getOdt() {
        return odt;
    }

    public void setOdt(String odt) {
        this.odt = odt;
    }

    public String getGstnStatus() {
        return gstnStatus;
    }

    public void setGstnStatus(String gstnStatus) {
        this.gstnStatus = gstnStatus;
    }

    public String getEwbStatus() {
        return ewbStatus;
    }

    public void setEwbStatus(String ewbStatus) {
        this.ewbStatus = ewbStatus;
    }

    public String getTabType() {
        return tabType;
    }

    public void setTabType(String tabType) {
        this.tabType = tabType;
    }

    public String getGstr1Status() {
        return gstr1Status;
    }

    public void setGstr1Status(String gstr1Status) {
        this.gstr1Status = gstr1Status;
    }

    public String getAnx1Status() {
        return anx1Status;
    }

    public void setAnx1Status(String anx1Status) {
        this.anx1Status = anx1Status;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getAnxStatus() {
        return anxStatus;
    }

    public void setAnxStatus(String anxStatus) {
        this.anxStatus = anxStatus;
    }

    public String getCustom1() {
        return custom1;
    }

    public void setCustom1(String custom1) {
        this.custom1 = custom1;
    }

    public String getCustom2() {
        return custom2;
    }

    public void setCustom2(String custom2) {
        this.custom2 = custom2;
    }

    public String getCustom3() {
        return custom3;
    }

    public void setCustom3(String custom3) {
        this.custom3 = custom3;
    }

    public String getCustom4() {
        return custom4;
    }

    public void setCustom4(String custom4) {
        this.custom4 = custom4;
    }

    public String getCustom5() {
        return custom5;
    }

    public void setCustom5(String custom5) {
        this.custom5 = custom5;
    }

    public String getCustom6() {
        return custom6;
    }

    public void setCustom6(String custom6) {
        this.custom6 = custom6;
    }

    public String getCustom7() {
        return custom7;
    }

    public void setCustom7(String custom7) {
        this.custom7 = custom7;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }
}
