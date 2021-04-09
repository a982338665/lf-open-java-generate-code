package com.github.generatecode.model;

import java.util.List;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/7 15:04
 * @Description : 一些内置的模板变量介绍
 */
public class TableInfo {

    /**
     * jdbc解析后获取的类属性 - 表名
     */
    private String tableName;
    /**
     * jdbc解析后获取的类属性 - 表注释
     */
    private String tableNote;

    /**
     * jdbc解析后获取的类属性 - 驼峰名称
     */
    private String camelCaseTableName;

    /**
     * jdbc解析后获取的类属性 - 是否驼峰
     */
    private boolean camelCase = true;

    /**
     * 字段对应值
     */
    private List<FieldInfo> fieldInfos;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableNote() {
        return tableNote;
    }

    public void setTableNote(String tableNote) {
        this.tableNote = tableNote;
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

    public List<FieldInfo> getFieldInfos() {
        return fieldInfos;
    }

    public void setFieldInfos(List<FieldInfo> fieldInfos) {
        this.fieldInfos = fieldInfos;
    }
}
