package com.escloud.corp.core.common.vo;

/**
 * @Description 响应异常
 * @Author qicaizhao
 * @Date 2017/4/1 13:46
 */
public class ResponseException extends RuntimeException{

    private static final long serialVersionUID = 8097668863729742849L;

    public ResponseException() {

    }

    public ResponseException(String s) {
        super(s);
    }

    public ResponseException(Throwable throwable) {
        super(throwable);
    }

    public ResponseException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
