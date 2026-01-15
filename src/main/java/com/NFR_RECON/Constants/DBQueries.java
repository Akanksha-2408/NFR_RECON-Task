package com.NFR_RECON.Constants;

import com.NFR_RECON.Entity.QueryBean;

public class DBQueries {

    public static final QueryBean GET_EINV_GSTIN_DETAILS_ID_BY_GSTIN = new QueryBean("SELECT ID FROM " + DbTables.TBL_EINV_GSTIN_DETAILS + " WHERE GSTIN = :gstin");
    public static final String UPDATE_USERNAME_BY_ID = "UPDATE " + DbTables.TBL_EINV_GSTIN_DETAILS+ " SET user_name = :username WHERE id = :einvGstinDetailsId";

    public static final QueryBean GET_SUBSCRIPTION_DETAILS_BY_GSTIN_AND_FEATURE = new QueryBean("Select * FROM " + DbTables.TBL_VALUE_ADDED_SERVICE_SUB + " WHERE GSTIN_NUMBER = :gstin AND FEATURE = :feature");
    public static final QueryBean GET_GSTIN_ID_FROM_GSTIN_NUMBER = new QueryBean("Select g.GSTIN_DETAILS_ID FROM " + DbTables.TBL_GSTIN + " g JOIN " + DbTables.TBL_GSTIN_DETAILS + " gd ON g.GSTIN_DETAILS_ID = gd.ID where gd.GSTIN_NUMBER = :gstin");
    public static final QueryBean GET_GSTR_SUBSCRIPTION_ID = new QueryBean("Select subscription_id FROM " + DbTables.TBL_SUBSCRIPTION_DETAILS + " WHERE gstin_id = :gstinId ORDER BY CREATED_AT DESC LIMIT 1");
    public static final QueryBean GET_EWB_SUBSCRIPTION_ID = new QueryBean("Select subscription_id FROM " + DbTables.TBL_EWB_SUBSCRIPTION_DETAILS + " WHERE gstin_id = :gstinId ORDER BY CREATED_AT DESC LIMIT 1");
    public static final QueryBean GET_PRODUCT_SUBSCRIPTION_ID = new QueryBean("Select subscription_id FROM " + DbTables.TBL_COMMON_SUBSCRIPTION + " WHERE gstin_id = :gstinId ORDER BY CREATED_AT DESC LIMIT 1");
    public static final String UPDATE_SUBSCRIPTION_DETAILS = "UPDATE " + DbTables.TBL_VALUE_ADDED_SERVICE_SUB + " SET end_date = :endDate, subscription_id = :subscriptionId, updated_at = :updatedAt WHERE gstin_number = :gstin";

}
