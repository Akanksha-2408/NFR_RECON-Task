package com.NFR_RECON.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateSubscriptionRequest {
    String gstin_number;
    String end_date;
    String feature;
    String product_name;
}
