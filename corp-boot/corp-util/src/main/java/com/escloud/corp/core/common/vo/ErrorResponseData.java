package com.escloud.corp.core.common.vo;

/**
 * @Description 后台异常处理返回json
 * @Author kangwang
 * @Date 2017/4/1 14:00
 */
public class ErrorResponseData extends Response {

    protected String msg;

    public ErrorResponseData() {
        super(false);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toJson() {
        StringBuilder buf = new StringBuilder();
        buf.append("{");
        buf.append("\"success\":false,");
        buf.append("\"msg\":").append("\"" + this.msg + "\"");
        buf.append("}");
        return buf.toString();
    }
}
