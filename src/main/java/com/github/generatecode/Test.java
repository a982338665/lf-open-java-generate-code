package com.github.generatecode;

import com.github.generatecode.outbean.JdbcProperties;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/3/18 11:14
 * @Description :
 */
public class Test {
    public static void main(String[] args) {
        JdbcProperties jdbcProperties = JdbcProperties.getInstance();
        jdbcProperties.setUrl("jdbc:mysql://172.30.10.41:3306/yeahmobi_kunlun");
        jdbcProperties.setUserName("root");
        jdbcProperties.setPwd("it-172-30-10-41");



    }
}
