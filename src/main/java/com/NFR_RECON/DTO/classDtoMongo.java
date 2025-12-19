package com.NFR_RECON.DTO;

import lombok.Data;
import org.bson.BsonInt32;
import org.bson.types.ObjectId;
import java.sql.Date;
import java.util.ArrayList;

@Data
public class classDtoMongo {

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

}
