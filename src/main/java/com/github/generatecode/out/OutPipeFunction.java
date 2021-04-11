package com.github.generatecode.out;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/11 20:56
 * @Description : 对外开放的管道方法申明Map
 */
public class OutPipeFunction {

    public static final Map<String, Function> PIPE_MAP = new HashMap<>();

    //内置的一些管道方法
    static {
        Function<String, String> upper = (String e) -> {
            StringBuilder stringBuilder = new StringBuilder();
            //其余的首字母大写
            stringBuilder.append(e.substring(0, 1).toUpperCase());
            //剩余的不变
            stringBuilder.append(e.substring(1));
            return stringBuilder.toString();
        };
        Function<String, String> upperAll = (String e) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(e.toUpperCase());
            //剩余的不变
            return stringBuilder.toString();
        };
        PIPE_MAP.put("upper", upper);
        PIPE_MAP.put("upperAll", upperAll);
    }
}
