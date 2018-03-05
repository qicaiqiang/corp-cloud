package com.escloud.corp.core.util;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author qicaizhao
 * @Date 2017/4/5 19:52
 */
public class CorpUtil {

    /**
     *  String 转 List
     * @param id
     * @param token 分隔符
     * @return
     */
    public static List<String> cutByToken(String id,String token){
        List<String> list = null;
        if (StringUtils.isNotEmpty(id)) {
            String[] ids = id.split(",");
             list = new ArrayList<String>();

            if (ids != null) {
                for (String idd : ids) {
                    list.add(idd);
                }
            }
        }else{
            return null;
        }
        return list;
    }

    public static Boolean isNotEmpty(List<String> list){
        if(list != null && list.size() > 0)
            return true;
        else
            return false;
    }
}
