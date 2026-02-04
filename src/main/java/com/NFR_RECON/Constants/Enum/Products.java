package com.NFR_RECON.Constants.Enum;

public enum Products {

    GSTR("GSTR"),
    EWAY_BILL("EWAY_BILL"),
    ITC_RECON("ITC_RECON"),
    LMS("LMS"),
    THIRD_EYE("THIRD_EYE"),
    SAFE_SIGN("SAFE_SIGN"),
    E_INVOICE("E_INVOICE"),;

    private final String description;

    Products(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
