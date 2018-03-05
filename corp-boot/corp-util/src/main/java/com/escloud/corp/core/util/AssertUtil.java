package com.escloud.corp.core.util;


import com.escloud.corp.core.common.vo.ResponseException;

public class AssertUtil {

    public static void throwException() {
        throw new ResponseException();
    }

    public static void throwException(String msg) {
        throw new ResponseException(msg);
    }

}
