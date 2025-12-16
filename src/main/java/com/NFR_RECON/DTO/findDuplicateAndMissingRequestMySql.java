package com.NFR_RECON.DTO;

import lombok.Data;

@Data
public class findDuplicateAndMissingRequestMySql {
    private String gstin;
    private String ctin;
    private String inum;
    private String txn_id;
}
