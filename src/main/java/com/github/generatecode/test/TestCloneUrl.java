package com.github.generatecode.test;

import com.github.generatecode.model.MatchKeywordStartToEnd;
import com.github.generatecode.util.RegexMatches;
import jdk.nashorn.internal.ir.LiteralNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/15 23:41
 * @Description : 从某个文件中匹配clone地址出来
 */
public class TestCloneUrl {

    public static void main(String[] args) {
        //$ curl "https://api.github.com/users/a982338665/repos?per_page=100" >xxx2.txt^  获取该用户下所有的仓库信息
        StringBuilder listsre = new StringBuilder();
//        exec("curl \"https://api.github.com/users/a982338665/repos?per_page=100\" >xxx2.txt");
        List<MatchKeywordStartToEnd> list = RegexMatches.matchKeywordStartToEnd("\"clone_url\": \"", "\"", TestClone.xxx.toString());
        list.forEach(e -> {
//            exec("git clone --depth=1 " + e.getKeyword());
            listsre.append("git clone --depth=1 " + e.getKeyword() + "\n");
        });
        List<MatchKeywordStartToEnd> list2 = RegexMatches.matchKeywordStartToEnd("\"clone_url\": \"", "\"", TestClone.xxx2.toString());
        list2.forEach(e -> {
            listsre.append("git clone --depth=1 " + e.getKeyword() + "\n");

//            exec("git clone --depth=1 " + e.getKeyword());
        });
        List<MatchKeywordStartToEnd> list3 = RegexMatches.matchKeywordStartToEnd("\"clone_url\": \"", "\"", TestClone.xxx3.toString());
        list3.forEach(e -> {
            listsre.append("git clone --depth=1 " + e.getKeyword() + "\n");

//            exec("git clone --depth=1 " + e.getKeyword());
        });
//        System.err.println(list.size() + list2.size() + list3.size());
//        exec();
        System.err.println(listsre.toString());
    }

    public static void exec(String cmd) {
        // Java调用 dos命令
//        String cmd = "ping www.baidu.com";
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "GBK");
            BufferedReader br = new BufferedReader(isr);
            String content = br.readLine();
            while (content != null) {
                System.out.println(content);
                content = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
