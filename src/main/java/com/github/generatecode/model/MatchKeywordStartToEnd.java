package com.github.generatecode.model;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/8 13:50
 * @Description : 匹配关键字实体类
 */
public class MatchKeywordStartToEnd {

    /**
     * 搜索的索引开始值
     */
    private Integer start;

    /**
     * 搜索的索引结束值
     */
    private Integer end;
    /**
     * 匹配到的关键字解析
     */
    private String keyword;

    public String getKeywordFull() {
        return keywordFull;
    }

    public void setKeywordFull(String keywordFull) {
        this.keywordFull = keywordFull;
    }

    /**
     * 匹配到的关键字解析-full
     */
    private String keywordFull;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public MatchKeywordStartToEnd() {
    }

    public MatchKeywordStartToEnd(Integer start, Integer end, String keyword) {
        this.start = start;
        this.end = end;
        this.keyword = keyword;
    }

    public MatchKeywordStartToEnd(Integer start, Integer end, String keyword, String keywordFull) {
        this.start = start;
        this.end = end;
        this.keyword = keyword;
        this.keywordFull = keywordFull;
    }

    @Override
    public String toString() {
        return "MatchKeywordStartToEnd{" +
                "start=" + start +
                ", end=" + end +
                ", keyword='" + keyword + '\'' +
                ", keywordFull='" + keywordFull + '\'' +
                '}';
    }
}
