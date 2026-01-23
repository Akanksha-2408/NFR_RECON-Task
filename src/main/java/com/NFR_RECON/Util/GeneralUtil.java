package com.NFR_RECON.Util;

import com.NFR_RECON.Constants.ResponseCode;
import com.NFR_RECON.Constants.ResponseMessage;
import com.NFR_RECON.Exception.GSPException;

import java.util.HashSet;
import java.util.Set;

public class GeneralUtil {

    public static long unMaskOnId(long primaryId) throws GSPException {
        long unMaskId;
        int beginIndex = 4;
        if (primaryId != 0 && ("" + primaryId).length() > 4) {
            primaryId = primaryId >> 2;

            String inputAsString = "" + primaryId;
            long randomRemove = Long.parseLong((inputAsString.substring(0, inputAsString.length() - beginIndex)));
            unMaskId = randomRemove >> 2;
        } else {
            throw new GSPException(ResponseCode.INVALID_DATA, ResponseMessage.INVALID_DATA);
        }
        return unMaskId;
    }



    public static String getNextRetPeriod(String returnPeriod) {
        String year = returnPeriod.substring(2);
        int yearInNumber = Integer.parseInt(year);
        String month = returnPeriod.substring(0, 2);
        int monthInNumber = Integer.parseInt(month);
        if (monthInNumber == 12) {
            monthInNumber = 1;
            yearInNumber++;
        } else if (monthInNumber < 12) {
            monthInNumber++;
        }
        if (monthInNumber < 10) {
            month = "0" + monthInNumber;
        } else {
            month = String.valueOf(monthInNumber);
        }
        return month + yearInNumber;
    }



    public static Set<String> getReturnPeriodList(String fromReturnPeriodPR, String toReturnPeriodPR) {
        Set<String> gstr2BRetPeriodList = new HashSet<>();
        if (DateFormatUtil.getDateFromReturnPeriod(fromReturnPeriodPR).before(DateFormatUtil.getDateFromReturnPeriod(toReturnPeriodPR))) {
            while (!fromReturnPeriodPR.equals(toReturnPeriodPR)) {
                gstr2BRetPeriodList.add(fromReturnPeriodPR);
                fromReturnPeriodPR = getNextRetPeriod(fromReturnPeriodPR);
            }
        } else {
            gstr2BRetPeriodList.add(fromReturnPeriodPR);
            gstr2BRetPeriodList.add(toReturnPeriodPR);
        }

        gstr2BRetPeriodList.add(toReturnPeriodPR);
        return gstr2BRetPeriodList;
    }

}
