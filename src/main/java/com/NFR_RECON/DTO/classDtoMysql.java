package com.NFR_RECON.DTO;

import lombok.Data;
import java.sql.Date;

@Data
public class classDtoMysql {

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

}
