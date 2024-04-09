package com.github.generatecode.util;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/7 14:19
 * @Description :
 */
public class StringUtils {

    public static String convertPath(String str) {
        return concat("\\", str.replace(".", "\\"));
    }
    public static String convertPackage(String str) {
        String replace = str.replace("\\", ".");
        return replace.replaceAll("/", ".");
    }

    public static String concatSync(String... strs) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : strs
        ) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public static String concat(String... strs) {
        StringBuilder stringBuffer = new StringBuilder();
        for (String str : strs
        ) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public static StringBuilder concatBuilder(String... strs) {
        StringBuilder stringBuffer = new StringBuilder();
        for (String str : strs
        ) {
            stringBuffer.append(str);
        }
        return stringBuffer;
    }

    /**
     * 获取java.lang.String 的String
     *
     * @param val
     * @return
     */
    public static String getLastVal(String val) {
        int i = val.lastIndexOf(".");
        return val.substring(i + 1);
    }

    /**
     * 字段转驼峰
     *
     * @param name          需要转换的字段名
     * @param isFirstletter 首字符是否大写：用来区分是类名还是字段名 true为类名 false为字段名
     * @return
     */
    public static String getCamelCase(String name, boolean isFirstletter) {
        String[] s = name.trim().split("_");
        StringBuilder stringBuilder = new StringBuilder();
        if (s.length >= 2) {
            for (int i = 0; i < s.length; i++) {
                String s1 = s[i];
                if (i == 0) {
                    if (isFirstletter) {
                        if (s1.length() == 1) {
                            stringBuilder.append(s1.toUpperCase());
                        } else {
                            //其余的首字母大写
                            stringBuilder.append(s1.substring(0, 1).toUpperCase());
                            //剩余的不变
                            stringBuilder.append(s1.substring(1));
                        }
                    } else {
                        //首字母不变 - 数据库命名不规范的需要调整
                        stringBuilder.append(s1);
                    }
                } else {
                    //其余的首字母大写
                    stringBuilder.append(s1.substring(0, 1).toUpperCase());
                    //剩余的不变
                    stringBuilder.append(s1.substring(1));
                }
            }
            return stringBuilder.toString();
        } else {
            //如果是类的话，首字母要大写
            if (isFirstletter) {
                //其余的首字母大写
                stringBuilder.append(name.substring(0, 1).toUpperCase());
                //剩余的不变
                stringBuilder.append(name.substring(1));
                return stringBuilder.toString();
            } else {
                return name;
            }
        }
    }

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    public static void main(String[] args) {
//        System.err.println(convertPath("com.name.ggg"));
//        System.err.println(new File("./").getAbsolutePath());
//        System.err.println(getLastVal("java.lang.String"));
//        System.err.println(getCamelCase("t_s_user", true));
//        System.err.println(getCamelCase("t_s_user", false));
    }
}
