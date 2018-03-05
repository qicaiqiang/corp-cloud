package com.escloud.corp.core.advice;
import com.escloud.corp.core.annotaion.Converter;
import com.escloud.corp.core.common.properties.Settings;
import com.escloud.corp.core.common.vo.ErrorResponseData;
import com.escloud.corp.core.common.vo.Response;
import com.escloud.corp.core.common.vo.SuccessResponse;
import com.escloud.corp.core.common.vo.SuccessResponseData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@ControllerAdvice
public class JsonResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    protected final Log logger = LogFactory.getLog(JsonResponseBodyAdvice.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private Settings settings;

    public Object beforeBodyWrite(Object object,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class clazz,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        String uri = request.getURI().toString();
        if (simpleMatch(settings.getIgnoreJsonResponseUrls(), uri)) {
            return object;
        }

        if (object instanceof Response) {
            return object;
        }
        if (object == null) {
            return SuccessResponse.newInstance();
        }

        Converter converterAnnotation = methodParameter.getMethodAnnotation(Converter.class);
        if (converterAnnotation != null) {
            com.escloud.corp.core.converter.Converter newInstance = null;
            Class<? extends com.escloud.corp.core.converter.Converter> converter = converterAnnotation
                    .value();
            try {
                newInstance = converter.newInstance();
                object = newInstance.converter(object);
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Data conversion errors", e);
                ErrorResponseData rrrorResponse = new ErrorResponseData();
                messageSource.getMessage("data_converter_error", null,
                        LocaleContextHolder.getLocale());
                return rrrorResponse;
            }
        }

        SuccessResponseData data = new SuccessResponseData(object);
        return data;
    }

    @SuppressWarnings({"unchecked"})
    public boolean supports(MethodParameter methodParameter, Class clazz) {
        if (clazz.isAssignableFrom(MappingJackson2HttpMessageConverter.class)) {
            return true;
        }
        return false;
    }

    private boolean simpleMatch(List<String> patterns, String str) {
        if (patterns != null) {
            for (String pattern : patterns) {
                if (PatternMatchUtils.simpleMatch("*" + pattern, str)) {
                    return true;
                }
            }
        }
        return false;
    }

}
