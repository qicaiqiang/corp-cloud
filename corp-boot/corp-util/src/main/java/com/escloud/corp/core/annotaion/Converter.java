package com.escloud.corp.core.annotaion;

import java.lang.annotation.*;


/**
 * @Description
 * @Author qicaizhao
 * @Date 2017/3/30
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Converter {
    Class<? extends com.escloud.corp.core.converter.Converter> value();
}
