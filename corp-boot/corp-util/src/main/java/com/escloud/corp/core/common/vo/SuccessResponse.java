package com.escloud.corp.core.common.vo;

public class SuccessResponse extends Response {

    public SuccessResponse() {
        super(true);
    }

    public static SuccessResponse newInstance() {
        return new SuccessResponse();
    }

}
