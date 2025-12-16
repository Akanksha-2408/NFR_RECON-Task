package com.NFR_RECON.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.bson.BsonInt32;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import java.sql.Date;
import java.util.ArrayList;

@Document(collection = "GSTR_INVOICE_DETAILS_INWARD")
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
    private Date createdAt;
    private Date updatedAt;

    private String anxStatus;
    private String clmrfnd;
    private String ctin;
    private String doctyp;
    private String extDocType;
    private String fy;
    private String gstnStatus;
    private ArrayList<String> headerKeys;
    private Date invDate;
    private int pos;

    // null values
    private String cusName;
    private double invVal;
    private double taxAmnt;
    private double taxVal;
    private double cgst;
    private double sgst;
    private double igst;
    private double cess;
    private boolean multiValue;

    private String rchrg;
    private String splyType;
    private String subTxnId;
    private String sec7act;
    private String rfndelg;
    private String onum;
    private String odt;
    private String ewbStatus;
    private String tabType;
    private String gstr1Status;
    private String anx1Status;
    private String usrName;
    private String invType;

}
