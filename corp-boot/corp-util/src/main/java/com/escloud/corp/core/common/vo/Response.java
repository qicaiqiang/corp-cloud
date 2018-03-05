package com.escloud.corp.core.common.vo;


public class Response {
    protected Boolean success;

    public Response() {

    }

    public Response(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
