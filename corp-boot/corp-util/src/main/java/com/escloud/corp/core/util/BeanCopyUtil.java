package com.escloud.corp.core.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description
 * @Author qicaizhao
 * @Date 2017/4/7 19:37
 */
public class BeanCopyUtil {

    /**
     * 利用反射实现对象之间相同属性复制
     *
     * @param source 要复制的
     * @param target     复制给
     */
    public static void copyProperties(Object source, Object target) throws Exception {

        copyPropertiesExclude(source, target, null);
    }

    /**
     * 复制对象属性
     *
     * @param from
     * @param to
     * @param excludsArray
     *            排除属性列表
     * @throws Exception
     */
    public static void copyPropertiesExclude(Object from, Object to,
                                             String[] excludsArray) throws Exception {

        List<String> excludesList = null;
        if (excludsArray != null && excludsArray.length > 0) {
            excludesList = Arrays.asList(excludsArray); // 构造列表对象
        }

        Method[] fromMethods = from.getClass().getDeclaredMethods();
        Method[] toMethods = to.getClass().getDeclaredMethods();
        Method fromMethod = null, toMethod = null;
        String fromMethodName = null, toMethodName = null;

        for (int i = 0; i < fromMethods.length; i++) {

            if (!fromMethodName.contains("get"))
                continue;
            // 排除列表检测
            fromMethod = fromMethods[i];
            fromMethodName = fromMethod.getName();

            if (excludesList != null
                    && excludesList.contains(fromMethodName.substring(3)
                    .toLowerCase())) {

                continue;
            }
            toMethodName = "set" + fromMethodName.substring(3);
            toMethod = findMethodByName(toMethods, toMethodName);

            if (toMethod == null)
                continue;
            Object value = fromMethod.invoke(from, new Object[0]);

            if (value == null)
                continue;
            // 集合类判空处理
            if (value instanceof Collection) {

                Collection<?> newValue = (Collection<?>) value;

                if (newValue.size() <= 0)
                    continue;
            }

            toMethod.invoke(to, new Object[] { value });
        }
    }

    /**
     * 对象属性值复制，仅复制指定名称的属性值
     *
     * @param from
     * @param to
     * @param includsArray
     * @throws Exception
     */
    public static void copyPropertiesInclude(Object from, Object to,
                                             String[] includsArray) throws Exception {

        List<String> includesList = null;

        if (includsArray != null && includsArray.length > 0) {

            includesList = Arrays.asList(includsArray);

        } else {

            return;
        }
        Method[] fromMethods = from.getClass().getDeclaredMethods();
        Method[] toMethods = to.getClass().getDeclaredMethods();
        Method fromMethod = null, toMethod = null;
        String fromMethodName = null, toMethodName = null;

        for (int i = 0; i < fromMethods.length; i++) {

            fromMethod = fromMethods[i];
            fromMethodName = fromMethod.getName();

            if (!fromMethodName.contains("get"))
                continue;

            // 排除列表检测
            String str = fromMethodName.substring(3);

            if (!includesList.contains(str.substring(0, 1).toLowerCase()
                    + str.substring(1))) {
                continue;
            }

            toMethodName = "set" + fromMethodName.substring(3);
            toMethod = findMethodByName(toMethods, toMethodName);

            if (toMethod == null)
                continue;

            Object value = fromMethod.invoke(from, new Object[0]);

            if (value == null)
                continue;

            // 集合类判空处理
            if (value instanceof Collection) {

                Collection<?> newValue = (Collection<?>) value;

                if (newValue.size() <= 0)
                    continue;
            }

            toMethod.invoke(to, new Object[] { value });
        }
    }

    /**
     * 从方法数组中获取指定名称的方法
     *
     * @param methods
     * @param name
     * @return
     */
    public static Method findMethodByName(Method[] methods, String name) {

        for (int j = 0; j < methods.length; j++) {

            if (methods[j].getName().equals(name)) {

                return methods[j];
            }

        }
        return null;
    }

    /**
     * 复制对象属性，忽略空值
     * @param src 要复制的
     * @param target 复制给
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target){
        final BeanWrapper beanWrapper = new BeanWrapperImpl(src);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(PropertyDescriptor pd : pds) {
            Object srcValue = beanWrapper.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        String [] ignoreProperties =emptyNames.toArray(result);
        BeanUtils.copyProperties(src, target, ignoreProperties);
    }


    /**
     * qicaizhao
     * 动态拼update语句
     * @param obj 需要拼update的实体类。必须存在ID
     */
    public static String getUpdateSql(Object obj) {
        Class<?> clazz = obj.getClass();
        String table =  obj.getClass().getName();
        String[] tableNames = table.split("\\.");
        StringBuilder set = new StringBuilder("UPDATE ");
        StringBuilder where = new StringBuilder(" WHERE ");
        set.append(tableNames[tableNames.length-1]);
        set.append(" SET ");
        Map<String, String> mapSet = new HashMap<String, String>();
        for (Class<?> cls : wrapperSuperclass(new ArrayList<Class<?>>(), clazz)) {
            for (PropertyDescriptor descriptor : BeanUtils.getPropertyDescriptors(cls)) {
                String value = getMethod(obj,descriptor.getName());
                if (value != null && !descriptor.getName().equals("id") && !descriptor.getName().equals("operator"))
                    mapSet.put(descriptor.getName(), value);

            }
        }
        for (Map.Entry<String, String> entry : mapSet.entrySet()) {
            if (entry.getKey().equals("updateDate")){
                SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
                String date = formate.format(new Date());
                java.sql.Date date1 = new java.sql.Date(new Date().getTime());
                set.append(entry.getKey());
                set.append(" = ");
                set.append("'"+date1+"'");
                set.append(",");

            }else {
                set.append(entry.getKey());
                set.append(" = ");
                set.append("'" + entry.getValue() + "'");
                set.append(",");
            }
        }

        where.append(" ");
        where.append("id");
        String value = getMethod(obj,"id");
        where.append(" = ");
        where.append("'"+value+"'");
        where.append(" AND");


        return set.deleteCharAt(set.length() - 1).append(where.delete(where.length() - 3, where.length())).toString();
    }


    private static List<Class<?>> wrapperSuperclass(List<Class<?>> clazzs, Class<?> clazz) {
        clazzs.add(clazz);
        Class<?> cls = clazz.getSuperclass();
        if (null != cls)
            wrapperSuperclass(clazzs, cls);
        return clazzs;
    }

    public static String getMethod(Object obj,String filed){
        try {
            Class clazz = obj.getClass();
            PropertyDescriptor pd = new PropertyDescriptor(filed,clazz);
            Method getMothod = pd.getReadMethod();
            if (pd != null){
                Object o = getMothod.invoke(obj);
                if (o != null) {
                    if (o instanceof Collection || o instanceof  Map)
                        return null;
                    else
                        return o.toString();
                }else
                    return null;
            }
            return null;
        }catch (Exception e){

            return null;
        }

    }


    public static boolean setMethod(Object obj,String filed,String value){
        try {
            Class clazz = obj.getClass();
            PropertyDescriptor pd = new PropertyDescriptor(filed,clazz);
            Method setMothod = pd.getWriteMethod();
            if (pd != null) {
                Object o = setMothod.invoke(obj, value);
                return true;
            }
            return false;
        }catch (Exception e){

            return false;
        }

    }







}
