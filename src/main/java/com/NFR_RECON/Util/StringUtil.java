package com.NFR_RECON.Util;

public class StringUtil {

    /**
     * This method checks whether value is null or empty.
     *
     * @param value
     * @return true : if the value == null/length=0, false : if the value !=null
     *         and length > 0
     */
    public static boolean isBlank(String value) {
        boolean isBlank = false;
        if (value == null || value.trim().length() == 0 || value.trim().equalsIgnoreCase("null")) {
            isBlank = true;
        }
        return isBlank;
    }

    /**
     * This method checks whether value is not null and not empty.
     *
     * @param value
     * @return true : if the value != null /length>0, false : if the
     *         value==null/length == 0
     */
    public static boolean isNotBlank(String value) {
        return !(isBlank(value));
    }

    /**
     *
     * @param obj
     * @return string representation - if the value != null, null - if the
     *         value==null
     */
    public static String toString(Object obj) {
        String value = null;
        if (obj != null) {
            value = obj.toString();
        }
        return value;
    }

}

