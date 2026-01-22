package com.NFR_RECON.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    public static Date getDateFromReturnPeriod(String returnPeriod) {
        Date date = new Date();
        boolean isValid = true;
        try {
            SimpleDateFormat simpleDateObj = new SimpleDateFormat("MMyyyy");
            date = simpleDateObj.parse(returnPeriod);
        } catch (ParseException e) {
            isValid = false;
            e.printStackTrace();
        }
        // if return period format is not valid then check with string name return period format
        if(!isValid){
            try {
                SimpleDateFormat simpleDateObj = new SimpleDateFormat("MMM-yyyy");
                date = simpleDateObj.parse(returnPeriod);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }


}
