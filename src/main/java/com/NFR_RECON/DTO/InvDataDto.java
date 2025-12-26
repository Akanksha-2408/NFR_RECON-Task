package com.NFR_RECON.DTO;

import lombok.Data;
import java.util.Set;

@Data
public class InvDataDto {
    String gstin;
    Set<InvDetailDataDto> missingInMysql;
    Set<InvDetailDataDto> duplicateInMongo;
    Set<InvDetailDataDto> duplicateInMysql;
}
