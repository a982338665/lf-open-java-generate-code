package com.github.generatecode.test;

import com.github.generatecode.model.MatchKeywordStartToEnd;
import com.github.generatecode.util.RegexMatches;
import com.github.generatecode.util.StringUtils;
import jdk.nashorn.internal.ir.LiteralNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/15 23:41
 * @Description : 从某个文件中匹配clone地址出来
 */
public class TestCloneUrl {

    public static void main(String[] args) {
        //1.获取github用户下的所有仓库信息
        String exec = exec("curl \"https://api.github.com/users/a982338665/repos?per_page=100\" >xxx2.txt");
        //2.通过正则匹配获取到clone地址并打印
        printGitCloneCMD(exec);
        //3.打开cmd窗口在指定的文件夹下 执行以上clone命令
    }
    /**
     * 打印git clone命令
     */
    public static void printGitCloneCMD(String text) {
        StringBuilder listsre = new StringBuilder();
        List<MatchKeywordStartToEnd> list = matchKeywordStartToEnd("\"clone_url\": \"", "\"", text);
        list.forEach(e -> {
            listsre.append("git clone --depth=1 " + e.getKeyword() + "\n");
        });
        System.err.println(listsre.toString());
    }

    public static String exec(String cmd) {
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "GBK");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder stringBuilder = new StringBuilder();
            String content = br.readLine();
            while (content != null) {
//                System.out.println(content);
                stringBuilder.append(content);
                content = br.readLine();
            }
            return stringBuilder.toString();
//            System.err.println(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<MatchKeywordStartToEnd> matchKeywordStartToEnd(String start, String end, String text) {
        List<MatchKeywordStartToEnd> list = new ArrayList<>();
        String regex = getRegex(start, end);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text); // 获取 matcher 对象
        while (m.find()) {
            String str = text.substring(m.start(), m.end());
            //(?<scope>.*?)是用于标识scope的方式，表示夹在中间的字符串都叫scope，可以通过matcher.group("scope")提取。
            //[\\s\\S]*? 是用来匹配任意字符，表示在前后有界定的情况下（例如夹在<xxx></xxx>中间的字符串），可以忽略那些不规则字符串的匹配。
            list.add(new MatchKeywordStartToEnd(m.start(), m.end(), m.group("scope"), str));
        }
        return list;
    }

    public static String getRegex(String start, String end) {
//        return StringUtils.concat("\\",start,"(?<scope>.*?)", end);           //不支持跨行匹配
        return StringUtils.concat("\\", start, "(?<scope>[\\s\\S]*?)", end);      //支持跨行匹配
    }
}
