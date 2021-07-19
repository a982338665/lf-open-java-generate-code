package com.github.generatecode.util;

import com.github.generatecode.model.MatchKeywordStartToEnd;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/8 13:07
 * @Description :
 */
public class RegexMatches {

//    private static final String REGEX = "\\!\\{[a-zA-Z0-9]+}";
//    private static final String REGEX = "\\!\\{(?<scope>.*?)}";
//    private static final String INPUT =
//            "!{xxx},,,halou!{1222}";
//    private static final String INPUT2 =
//            "!##{xxx}##,,,halou!##{1222}##";
//
//    public static void main(String[] args) {
//
//        test();
//        List<MatchKeywordStartToEnd> list = matchKeywordStartToEnd(escapeExprSpecialWord("!{"), escapeExprSpecialWord("}"), INPUT);
//        List<MatchKeywordStartToEnd> list = matchKeywordStartToEnd("!\\{", "}", INPUT);
//        list.forEach(System.err::println);
//        List<MatchKeywordStartToEnd> list2 = matchKeywordStartToEnd(escapeExprSpecialWord("!##{"), escapeExprSpecialWord("}##"), INPUT2);
//        list2.forEach(System.err::println);
//
//        System.err.println(INPUT.substring(0,10));
//    }

//    private static void test() {
//        Pattern p = Pattern.compile(REGEX);
//        Matcher m = p.matcher(INPUT); // 获取 matcher 对象
//        int count = 0;
//
//        while (m.find()) {
//            count++;
//            System.out.println("Match number " + count);
//            System.out.println("start(): " + m.start());
//            System.out.println("end(): " + m.end());
//        }
//    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (!StringUtils.isEmpty(keyword)) {
            String[] fbsArr = {"\\", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
//            String[] fbsArr = { "\\", "$", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
//            String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
//        System.err.println("+++++++++++++++++"+keyword);
        return keyword;
    }
    public static String escapeExprSpecialWordSpecial(String keyword) {
        if (!StringUtils.isEmpty(keyword)) {
//            String[] fbsArr = {"\\", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
//            String[] fbsArr = { "\\", "$", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
            String[] fbsArr = { "\\", "#", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
//        System.err.println("+++++++++++++++++"+keyword);
        return keyword;
    }

    /**
     * 用来匹配开始-结束字符串中间的数据内容，并返回
     *
     * @param start
     * @param end
     * @param text
     * @return
     */
    public static List<MatchKeywordStartToEnd> matchKeywordStartToEnd(String start, String end, String text) {
        List<MatchKeywordStartToEnd> list = new ArrayList<>();
        String regex = getRegex(start, end);
//        System.err.println(REGEX);
//        System.err.println(regex);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text); // 获取 matcher 对象
        while (m.find()) {
//            System.out.println("start(): " + m.start());
//            System.out.println("end(): " + m.end());
            String str = text.substring(m.start(), m.end());
//            System.err.println(str);
//            String finalVal = str.substring(m.start()+1,m.end()-1);
//            str = str.replace(start, "");
//            str = str.replace(end, "");
            //(?<scope>.*?)是用于标识scope的方式，表示夹在中间的字符串都叫scope，可以通过matcher.group("scope")提取。
            //[\\s\\S]*? 是用来匹配任意字符，表示在前后有界定的情况下（例如夹在<xxx></xxx>中间的字符串），可以忽略那些不规则字符串的匹配。
            list.add(new MatchKeywordStartToEnd(m.start(), m.end(), m.group("scope"), str));
        }
        return list;
    }

    /**
     * 用来匹配开始-结束字符串中间的数据内容，并返回  发现一个返回一个
     *
     * @param start
     * @param end
     * @param text
     * @return
     */
    public static List<MatchKeywordStartToEnd> matchKeywordStartToEndFindone(String start, String end, String text) {
        List<MatchKeywordStartToEnd> list = new ArrayList<>();
        String regex = getRegex(start, end);
//        System.err.println(REGEX);
//        System.err.println(regex);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text); // 获取 matcher 对象
        while (m.find()) {
//            System.out.println("start(): " + m.start());
//            System.out.println("end(): " + m.end());
            String str = text.substring(m.start(), m.end());
//            System.err.println(str);
//            String finalVal = str.substring(m.start()+1,m.end()-1);
//            str = str.replace(start, "");
//            str = str.replace(end, "");
            //(?<scope>.*?)是用于标识scope的方式，表示夹在中间的字符串都叫scope，可以通过matcher.group("scope")提取。
            //[\\s\\S]*? 是用来匹配任意字符，表示在前后有界定的情况下（例如夹在<xxx></xxx>中间的字符串），可以忽略那些不规则字符串的匹配。
            list.add(new MatchKeywordStartToEnd(m.start(), m.end(), m.group("scope"), str));
            return list;
        }
        return list;
    }

    /**
     * 用来匹配开始-结束字符串中间的数据内容，并返回  发现一个返回一个
     *
     * @param start 内部格式化为正则
     * @param end   内部格式化为正则
     * @param text
     * @return
     */
    public static List<MatchKeywordStartToEnd> matchKeywordStartToEndFindoneRegex(String start, String end, String text) {
        start = escapeExprSpecialWord(start);
        end = escapeExprSpecialWord(end);
        List<MatchKeywordStartToEnd> list = new ArrayList<>();
        String regex = getRegex(start, end);
//        System.err.println(REGEX);
//        System.err.println(regex);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text); // 获取 matcher 对象
        while (m.find()) {
//            System.out.println("start(): " + m.start());
//            System.out.println("end(): " + m.end());
            String str = text.substring(m.start(), m.end());
//            System.err.println(str);
//            String finalVal = str.substring(m.start()+1,m.end()-1);
//            str = str.replace(start, "");
//            str = str.replace(end, "");
            //(?<scope>.*?)是用于标识scope的方式，表示夹在中间的字符串都叫scope，可以通过matcher.group("scope")提取。
            //[\\s\\S]*? 是用来匹配任意字符，表示在前后有界定的情况下（例如夹在<xxx></xxx>中间的字符串），可以忽略那些不规则字符串的匹配。
            list.add(new MatchKeywordStartToEnd(m.start(), m.end(), m.group("scope"), str));
            return list;
        }
        return list;
    }

    /**
     * 用来匹配开始-结束字符串中间的数据内容，并返回  发现一个返回一个
     *
     * @param start 内部格式化为正则
     * @param end   内部格式化为正则
     * @param text
     * @return
     */
    public static MatchKeywordStartToEnd matchKeywordStartToEndFindoneRegexLimit1(String start, String end, String text) {
        System.err.println(start + "]]]]" + end + "]]]]" + text);
        start = escapeExprSpecialWord(start);
        end = escapeExprSpecialWord(end);
        String regex = getRegex(start, end);
//        System.err.println(REGEX);
//        System.err.println(regex);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text); // 获取 matcher 对象
        while (m.find()) {
//            System.out.println("start(): " + m.start());
//            System.out.println("end(): " + m.end());
            String str = text.substring(m.start(), m.end());
//            System.err.println(str);
//            String finalVal = str.substring(m.start()+1,m.end()-1);
//            str = str.replace(start, "");
//            str = str.replace(end, "");
            //(?<scope>.*?)是用于标识scope的方式，表示夹在中间的字符串都叫scope，可以通过matcher.group("scope")提取。
            //[\\s\\S]*? 是用来匹配任意字符，表示在前后有界定的情况下（例如夹在<xxx></xxx>中间的字符串），可以忽略那些不规则字符串的匹配。
            return new MatchKeywordStartToEnd(m.start(), m.end(), m.group("scope"), str);
        }
        return null;
    }

    /**
     * 用来匹配开始-结束字符串中间的数据内容，并返回  发现一个返回一个
     *
     * @param start 内部格式化为正则
     * @param end   内部格式化为正则
     * @param text
     * @return
     */
    public static MatchKeywordStartToEnd matchKeywordStartToEndFindoneRegexLimit1FromLetter(String start, String end, String text) {
        start = escapeExprSpecialWord(start);
        end = escapeExprSpecialWord(end);
        String regex = getRegexFromLetter(start, end);
//        System.err.println(REGEX);
//        System.err.println(regex);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text); // 获取 matcher 对象
        while (m.find()) {
//            System.out.println("start(): " + m.start());
//            System.out.println("end(): " + m.end());
            String str = text.substring(m.start(), m.end());
//            System.err.println(str);
//            String finalVal = str.substring(m.start()+1,m.end()-1);
//            str = str.replace(start, "");
//            str = str.replace(end, "");
            //(?<scope>.*?)是用于标识scope的方式，表示夹在中间的字符串都叫scope，可以通过matcher.group("scope")提取。
            //[\\s\\S]*? 是用来匹配任意字符，表示在前后有界定的情况下（例如夹在<xxx></xxx>中间的字符串），可以忽略那些不规则字符串的匹配。
            return new MatchKeywordStartToEnd(m.start(), m.end(), m.group("scope"), str);
        }
        return null;
    }


    public static String getRegex(String start, String end) {
//        return StringUtils.concat("\\",start,"(?<scope>.*?)", end);           //不支持跨行匹配
        return StringUtils.concat("\\", start, "(?<scope>[\\s\\S]*?)", end);      //支持跨行匹配
    }

    public static String getRegexFromLetter(String start, String end) {
//        return StringUtils.concat("\\",start,"(?<scope>.*?)", end);           //不支持跨行匹配
        return StringUtils.concat(start, "(?<scope>[\\s\\S]*?)", end);      //支持跨行匹配
    }

    public static MatchKeywordStartToEnd matchKeywordStartToEndFindoneRegexLimit1Specal(String start, String end, String text) {
        start = escapeExprSpecialWordSpecial(start);
        end = escapeExprSpecialWordSpecial(end);
        String regex = getRegexFromLetter(start, end);
//        System.err.println(REGEX);
        System.err.println(regex);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text); // 获取 matcher 对象
        while (m.find()) {
//            System.out.println("start(): " + m.start());
//            System.out.println("end(): " + m.end());
            String str = text.substring(m.start(), m.end());
//            System.err.println(str);
//            String finalVal = str.substring(m.start()+1,m.end()-1);
//            str = str.replace(start, "");
//            str = str.replace(end, "");
            //(?<scope>.*?)是用于标识scope的方式，表示夹在中间的字符串都叫scope，可以通过matcher.group("scope")提取。
            //[\\s\\S]*? 是用来匹配任意字符，表示在前后有界定的情况下（例如夹在<xxx></xxx>中间的字符串），可以忽略那些不规则字符串的匹配。
            return new MatchKeywordStartToEnd(m.start(), m.end(), m.group("scope"), str);
        }
        return null;
    }
}
