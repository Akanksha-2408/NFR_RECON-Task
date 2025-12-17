package com.NFR_RECON.Entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "gstr2b_regular_recon_upload")
public class classForMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double adTax;
    private double camt;
    private String cname;
    private Date created_at;
    private double csamt;
    private String ctin;
    private String custom_column_json;
    private double diffprcnt;
    private String elg;
    private String flag;
    private String gstin;
    private Long gstr2b_report_metadata_id;
    private double iamt;
    private Date idt;
    private String inum;
    private String inv_json_data;
    private String inv_typ;
    private String itcRev;
    private String itcavl;
    private String item_level_json_data;
    private Date oidt;
    private String oinum;
    private String onttyp;
    private String pos;
    private String rchrg;
    private String return_period;
    private String rsn;
    private double samt;
    private String suptyp;
    private double txamt;
    private String txn_id;
    private double txval;
    private String typ;
    private Date updated_at;
    private double val;
    private String is_edited;
    private String sub_recon_status;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAdTax() {
        return adTax;
    }

    public void setAdTax(double adTax) {
        this.adTax = adTax;
    }

    public double getCamt() {
        return camt;
    }

    public void setCamt(double camt) {
        this.camt = camt;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public double getCsamt() {
        return csamt;
    }

    public void setCsamt(double csamt) {
        this.csamt = csamt;
    }

    public String getCtin() {
        return ctin;
    }

    public void setCtin(String ctin) {
        this.ctin = ctin;
    }

    public String getCustom_column_json() {
        return custom_column_json;
    }

    public void setCustom_column_json(String custom_column_json) {
        this.custom_column_json = custom_column_json;
    }

    public double getDiffprcnt() {
        return diffprcnt;
    }

    public void setDiffprcnt(double diffprcnt) {
        this.diffprcnt = diffprcnt;
    }

    public String getElg() {
        return elg;
    }

    public void setElg(String elg) {
        this.elg = elg;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public Long getGstr2b_report_metadata_id() {
        return gstr2b_report_metadata_id;
    }

    public void setGstr2b_report_metadata_id(Long gstr2b_report_metadata_id) {
        this.gstr2b_report_metadata_id = gstr2b_report_metadata_id;
    }

    public double getIamt() {
        return iamt;
    }

    public void setIamt(double iamt) {
        this.iamt = iamt;
    }

    public Date getIdt() {
        return idt;
    }

    public void setIdt(Date idt) {
        this.idt = idt;
    }

    public String getInum() {
        return inum;
    }

    public void setInum(String inum) {
        this.inum = inum;
    }

    public String getInv_json_data() {
        return inv_json_data;
    }

    public void setInv_json_data(String inv_json_data) {
        this.inv_json_data = inv_json_data;
    }

    public String getInv_typ() {
        return inv_typ;
    }

    public void setInv_typ(String inv_typ) {
        this.inv_typ = inv_typ;
    }

    public String getItcRev() {
        return itcRev;
    }

    public void setItcRev(String itcRev) {
        this.itcRev = itcRev;
    }

    public String getItcavl() {
        return itcavl;
    }

    public void setItcavl(String itcavl) {
        this.itcavl = itcavl;
    }

    public String getItem_level_json_data() {
        return item_level_json_data;
    }

    public void setItem_level_json_data(String item_level_json_data) {
        this.item_level_json_data = item_level_json_data;
    }

    public Date getOidt() {
        return oidt;
    }

    public void setOidt(Date oidt) {
        this.oidt = oidt;
    }

    public String getOinum() {
        return oinum;
    }

    public void setOinum(String oinum) {
        this.oinum = oinum;
    }

    public String getOnttyp() {
        return onttyp;
    }

    public void setOnttyp(String onttyp) {
        this.onttyp = onttyp;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getRchrg() {
        return rchrg;
    }

    public void setRchrg(String rchrg) {
        this.rchrg = rchrg;
    }

    public String getReturn_period() {
        return return_period;
    }

    public void setReturn_period(String return_period) {
        this.return_period = return_period;
    }

    public String getRsn() {
        return rsn;
    }

    public void setRsn(String rsn) {
        this.rsn = rsn;
    }

    public double getSamt() {
        return samt;
    }

    public void setSamt(double samt) {
        this.samt = samt;
    }

    public String getSuptyp() {
        return suptyp;
    }

    public void setSuptyp(String suptyp) {
        this.suptyp = suptyp;
    }

    public double getTxamt() {
        return txamt;
    }

    public void setTxamt(double txamt) {
        this.txamt = txamt;
    }

    public String getTxn_id() {
        return txn_id;
    }

    public void setTxn_id(String txn_id) {
        this.txn_id = txn_id;
    }

    public double getTxval() {
        return txval;
    }

    public void setTxval(double txval) {
        this.txval = txval;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public String getIs_edited() {
        return is_edited;
    }

    public void setIs_edited(String is_edited) {
        this.is_edited = is_edited;
    }

    public String getSub_recon_status() {
        return sub_recon_status;
    }

    public void setSub_recon_status(String sub_recon_status) {
        this.sub_recon_status = sub_recon_status;
    }
}
