package com.escloud.corp.core.util;


import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static boolean isJson(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept == null) {
            return false;
        }
        String[] arr = accept.split(",");
        if (arr == null) {
            return false;
        }
        for (String a : arr) {
            if ("application/json".equalsIgnoreCase(a)) {
                return true;
            }
        }
        return false;
    }
}
