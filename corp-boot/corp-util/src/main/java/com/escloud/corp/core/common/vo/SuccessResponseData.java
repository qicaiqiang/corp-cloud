package com.escloud.corp.core.common.vo;

public class SuccessResponseData extends SuccessResponse {

    private Object data;

    public SuccessResponseData() {
        super();
    }

    public static SuccessResponseData newInstance(Object data) {
        return new SuccessResponseData(data);
    }

    public SuccessResponseData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
