package com.NFR_RECON.DTO;

import lombok.Data;
import java.util.Date;

@Data
public class InvDetailDataDto {
    String docType;
    String inum;
    Date idt;
    String ctin;
    String txnId;
}
