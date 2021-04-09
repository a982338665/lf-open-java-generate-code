package com.github.generatecode.model;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/7 15:04
 * @Description : 一些内置的模板变量介绍
 */
public class OutTableInfo {

    public OutTableInfo() {
    }

    public OutTableInfo(String tableName) {
        this.tableName = tableName;
    }

    public OutTableInfo(String tableName, String prefix) {
        this.tableName = tableName;
        this.prefix = prefix;
    }

    public OutTableInfo(String tableName, String prefix, String camelCaseTableName, boolean camelCase) {
        this.tableName = tableName;
        this.prefix = prefix;
        this.camelCaseTableName = camelCaseTableName;
        this.camelCase = camelCase;
    }
    public OutTableInfo(String tableName, String prefix,  boolean camelCase) {
        this.tableName = tableName;
        this.prefix = prefix;
        this.camelCase = camelCase;
    }
    public OutTableInfo(String tableName,  boolean camelCase) {
        this.tableName = tableName;
        this.camelCase = camelCase;
    }


    /**
     * jdbc解析后获取的类属性 - 表名
     */
    private String tableName;
    /**
     * jdbc解析后获取的类属性 - 表注释
     */
    private String prefix;

    /**
     * jdbc解析后获取的类属性 - 驼峰名称
     */
    private String camelCaseTableName;

    /**
     * jdbc解析后获取的类属性 - 是否驼峰
     */
    private boolean camelCase = true;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getCamelCaseTableName() {
        return camelCaseTableName;
    }

    public void setCamelCaseTableName(String camelCaseTableName) {
        this.camelCaseTableName = camelCaseTableName;
    }

    public boolean isCamelCase() {
        return camelCase;
    }

    public void setCamelCase(boolean camelCase) {
        this.camelCase = camelCase;
    }
}
