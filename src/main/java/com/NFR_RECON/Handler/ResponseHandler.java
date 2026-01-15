package com.NFR_RECON.Handler;

import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

        public static final String ORG_REGISTER_SUCCESS = "Organization created and confirmation link sent to specified email address.";
        public static final String USER_LOGIN_FAILURE = "Username or password is incorrect.";

        public static final String STATUS_SUCCESS = "SUCCESS";
        public static final String STATUS_ERROR = "ERROR";

        public static Map<String, Object> success(Object data) {
            Map<String, Object> map = new HashMap<>();
            map.put("statusCode", HttpStatus.OK.value());
            map.put("status", STATUS_SUCCESS);
            map.put("data", data);
            return map;
        }

        public static Map<String, Object> success(Object data, String successMessage) {
            Map<String, Object> map = new HashMap<>();
            map.put("statusCode", HttpStatus.OK.value());
            map.put("status", STATUS_SUCCESS);
            map.put("data", data);
            map.put("msg", successMessage);
            return map;
        }

        public static Map<String, Object> success(String successMessage) {
            Map<String, Object> map = new HashMap<>();
            map.put("statusCode", HttpStatus.OK.value());
            map.put("status", STATUS_SUCCESS);
            map.put("data", successMessage);
            map.put("msg", successMessage);
            return map;
        }

        public static Map<String, Object> error(Object data) {
            Map<String, Object> map = new HashMap<>();
            map.put("status", STATUS_ERROR);
            map.put("data", data);
            return map;
        }

        public static Map<String, Object> error(String errCode, String errMsg) {
            Map<String, Object> map = new HashMap<>();
            map.put("statusCode", errCode);
            map.put("status", STATUS_ERROR);
            map.put("data", errMsg);
            return map;
        }

        public static Map<String, Object> error(Object data, String errMsg) {
            Map<String, Object> map = new HashMap<>();
            map.put("status", STATUS_ERROR);
            map.put("data", data);
            map.put("msg", errMsg);
            return map;
        }

        public static Map<String, Object> customError(String errMsg) {
            Map<String, Object> map = new HashMap<>();
            map.put("status", STATUS_ERROR);
            map.put("message", errMsg);
            map.put("data", errMsg);
            return map;
        }
}
