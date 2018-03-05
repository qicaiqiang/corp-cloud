package com.escloud.corp.core.advice;

import com.escloud.corp.core.common.vo.ErrorResponseData;
import com.escloud.corp.core.common.vo.ResponseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    protected final Log logger = LogFactory.getLog(ExceptionHandlerAdvice.class);

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ResponseException.class)
    @ResponseBody
    ErrorResponseData handleBadRequest(HttpServletRequest req, ResponseException ce) {
        logger.info("ResponseException：" + ce.getMessage());
        ErrorResponseData data = new ErrorResponseData();
        data.setMsg(ce.getMessage());
        return data;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    ErrorResponseData handleBadRequest(HttpServletRequest req, Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        System.out.println(ex.getMessage());
        logger.error("后台程序异常：", ex);

        ErrorResponseData data = new ErrorResponseData();
        String msg = "后台程序异常！";
        logger.info("ResponseException：" + msg);
        data.setMsg(msg);
        return data;
    }
}
