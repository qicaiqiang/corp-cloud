package com.escloud.corp.core.web.dispatch;


import com.escloud.corp.core.common.properties.Settings;
import com.escloud.corp.core.common.vo.ErrorResponseData;
import com.escloud.corp.core.util.JsonUtil;
import com.escloud.corp.core.util.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@SuppressWarnings("serial")
public class DefaultDispatchServlet extends DispatcherServlet {

    Map<String, Locale> locales = new HashMap<String, Locale>();

    @Autowired
    private Settings settings;

    public DefaultDispatchServlet() {
        Locale[] lcl = Locale.getAvailableLocales();
        if (lcl != null) {
            for (Locale locale : lcl) {
                String language = locale.getLanguage();
                String country = locale.getCountry();
                String lang = language + "_" + country;
                locales.put(lang, locale);
            }
        }
    }

    protected ModelAndView processHandlerException(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   Object handler,
                                                   Exception ex) throws Exception {
        if (RequestUtil.isJson(request) && ex instanceof ServletException) {
            ErrorResponseData error = new ErrorResponseData();
            error.setMsg(ex.getMessage());
            String errorJson = JsonUtil.json(error);
            response.getWriter().print(errorJson);
            return null;
        } else {
            return super.processHandlerException(request, response, handler, ex);
        }
    }

    @Override
    protected LocaleContext buildLocaleContext(final HttpServletRequest request) {
        return new LocaleContext() {
            @Override
            public Locale getLocale() {
                Locale locale = null;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        String name = cookie.getName();
                        if ("LANG".equals(name)) {
                            String value = cookie.getValue();
                            if (StringUtils.isNotEmpty(value)) {
                                List<String> supportLocales = settings.getSupportLocales();
                                if (supportLocales.contains(value)) {
                                    locale = locales.get(value);
                                    break;
                                }
                            }
                        }
                    }
                }
                if (locale != null) {
                    return locale;
                }
                String defaultLocale = settings.getDefaultLocale();

                if (StringUtils.isNotEmpty(defaultLocale)) {
                    locale = locales.get(defaultLocale);
                }
                if (locale == null) {
                    locale = Locale.getDefault();
                }
                return locale;
            }
        };
    }

}
