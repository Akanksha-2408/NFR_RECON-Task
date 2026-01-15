package com.NFR_RECON.Util;

import java.security.SecureRandom;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

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

    /**
     * @param value
     * @return true if value is numeric else false
     */
    public static boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public static String randon5DigitNumber() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        String formatted = String.format("%05d", num);
        System.out.println(formatted);
        return formatted;
    }

    public static String randon9DigitNumber() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(1000000000);
        String formatted = String.format("%09d", num);
        System.out.println(formatted);
        return formatted;
    }
}

