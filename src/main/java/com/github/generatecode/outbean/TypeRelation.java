package com.github.generatecode.outbean;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/3/18 15:28
 * @Description : 默认的映射关系 -  但是要求可以从外部更改
 */
public class TypeRelation {

    /**
     * 允许对外开放的类型对应关系
     */
    private static Map<String, String> defaultTypeMap = new HashMap<>();

    public static Map<String, String> getInstance() {
        return defaultTypeMap;
    }

    private static final String TYPE_STRING = "java.lang.String";
    private static final String TYPE_BIGDECIMAL = "java.math.BigDecimal";
    private static final String TYPE_INTEGER = "java.lang.Integer";
    private static final String TYPE_LONG = "java.lang.Long";
    private static final String TYPE_DATE = "java.util.Date";
    private static final String TYPE_BOOLEAN = "java.lang.Boolean";

    static {
        defaultTypeMap.put("varchar(\\(\\d+\\))?", TYPE_STRING);
        defaultTypeMap.put("char(\\(\\d+\\))?", TYPE_STRING);
        defaultTypeMap.put("text", TYPE_STRING);
        defaultTypeMap.put("decimal(\\(\\d+\\))?", TYPE_BIGDECIMAL);
        defaultTypeMap.put("decimal(\\(\\d+,\\d+\\))?", TYPE_BIGDECIMAL);
        defaultTypeMap.put("integer", TYPE_INTEGER);
        defaultTypeMap.put("int(\\(\\d+\\))?", TYPE_INTEGER);
        defaultTypeMap.put("int4", TYPE_INTEGER);
        defaultTypeMap.put("int8", TYPE_LONG);
        defaultTypeMap.put("bigint(\\(\\d+\\))?", TYPE_LONG);
        defaultTypeMap.put("datetime", TYPE_DATE);
        defaultTypeMap.put("timestamp", TYPE_DATE);
        defaultTypeMap.put("datetime(\\(\\d+\\))?", TYPE_DATE);
        defaultTypeMap.put("timestamp(\\(\\d+\\))?", TYPE_DATE);
        defaultTypeMap.put("boolean", TYPE_BOOLEAN);

    }

}


class sss {
    public static void main(String[] args) {
        //修改测试
        Map<String, String> instance = TypeRelation.getInstance();
        instance.put("int(\\(\\d+\\))?", "java.lang.Long");
        instance.entrySet() // 获取entrySet
                .stream() // 将其转化成流
                .forEach(p -> {
                    String key = p.getKey();
                    String value = p.getValue();
                    //截取最后一个.后的值
                    int i = value.lastIndexOf(".");
                    String lastVal = value.substring(i+1, value.length());
                    System.err.println(key + "=========" + value + "==============" + lastVal);

                });
        //匹配测试
        String reg = "varchar(\\(\\d+\\))?";
        String test1 = "varchar(1000)";
        String test2 = "varchar(10001)";
        String test3 = "varchar(10)";
        boolean isMatch = Pattern.matches(reg, test1);
        boolean isMatch2 = Pattern.matches(reg, test2);
        boolean isMatch3 = Pattern.matches(reg, test3);
        System.err.println(isMatch);
        System.err.println(isMatch2);
        System.err.println(isMatch3);
    }
}
