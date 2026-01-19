package com.NFR_RECON.DTO;

import lombok.Data;

@Data
public class AddonDateExtensionRequest {
    String gstin_number;
    String end_date;
    String feature;
    String product_name;
}
