package com.github.generatecode.template;

import com.github.generatecode.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/7 15:45
 * @Description : 类型转换模板类 - 可修改，可添加
 */
public class TypeCovert {


    private static final Map<String, String> typeMapping = new HashMap<>();

    private TypeCovert() {

    };

    static {
        typeMapping.put("varchar(\\(\\d+\\))?","java.lang.String");
        typeMapping.put("char(\\(\\d+\\))?","java.lang.String");
        typeMapping.put("text","java.lang.String");
        typeMapping.put("decimal(\\(\\d+\\))?","java.math.BigDecimal");
        typeMapping.put("decimal(\\(\\d+,\\d+\\))?","java.math.BigDecimal");
        typeMapping.put("integer","java.lang.Integer");
        typeMapping.put("int(\\(\\d+\\))?","java.lang.Integer");
        typeMapping.put("int4","java.lang.Integer");
        typeMapping.put("int8","java.lang.Long");
        typeMapping.put("bigint(\\(\\d+\\))?","java.lang.Long");
        typeMapping.put("datetime","java.util.Date");
        typeMapping.put("timestamp","java.util.Date");
        typeMapping.put("boolean","java.lang.Boolean");
    }


    public static Map<String, String> getInstance() {
        return typeMapping;
    }

    /**
     * 类型转换必须要做，否则需要返回null，通过单例模式设置转换关系
     * @param type
     * @return
     */
    public static String getClassType(String type){
        for(Map.Entry<String, String> entry : typeMapping.entrySet()){
            String pattern = entry.getKey();
            String value = entry.getValue();
            if(Pattern.matches(pattern, type)) {
                return value;
            }
        }
        return null;
    }
    /**
     * 类型转换必须要做，否则需要返回null，通过单例模式设置转换关系
     * 返回最后一行
     * @param type
     * @return
     */
    public static String getClassTypeShort(String type){
        for(Map.Entry<String, String> entry : typeMapping.entrySet()){
            String pattern = entry.getKey();
            String value = entry.getValue();
            if(Pattern.matches(pattern, type)) {
                return StringUtils.getLastVal(value);
            }
        }
        return null;
    }

}
