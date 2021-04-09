package com.github.generatecode.model;

import com.github.generatecode.template.TypeCovert;
import com.github.generatecode.util.StringUtils;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/7 15:33
 * @Description : 针对于单个表的基本属性值
 */
public class FieldInfo {

    /**
     * 数据库字段名称
     */
    private String fieldName;
    /**
     * 数据库字段注释
     */
    private String fieldNote;
    /**
     * 转换后的实体类字段名称
     */
    private String camelCaseFieldName;
    /**
     * 数据库字段的类型 - 后面会根据此字段类型转换为对应的java属性
     */
    private String fieldType;
    /**
     * 类属性字段的类型
     */
    private String classType;
    /**
     * 类属性字段的类型
     */
    private String classTypeShort;

    public FieldInfo() {
    }

    public FieldInfo(String fieldName, String fieldNote, String camelCaseFieldName, String fieldType, String classType, String classTypeShort) {
        this.fieldName = fieldName;
        this.fieldNote = fieldNote;
        this.camelCaseFieldName = camelCaseFieldName;
        this.fieldType = fieldType;
        this.classType = classType;
        this.classTypeShort = classTypeShort;
    }

    public FieldInfo(String fieldName, String fieldNote, String camelCaseFieldName, String fieldType) {
        this.fieldName = fieldName;
        this.fieldNote = fieldNote;
        this.camelCaseFieldName = camelCaseFieldName;
        this.fieldType = fieldType;
//        if(StringUtils.isEmpty(this.fieldType)){
//            String classType = TypeCovert.getClassType(fieldType);
//            String classTypeShort = TypeCovert.getClassTypeShort(fieldType);
//            this.classType = classType;
//            this.classTypeShort = classTypeShort;
//        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldNote() {
        return fieldNote;
    }

    public void setFieldNote(String fieldNote) {
        this.fieldNote = fieldNote;
    }

    public String getCamelCaseFieldName() {
        return camelCaseFieldName;
    }

    public void setCamelCaseFieldName(String camelCaseFieldName) {
        this.camelCaseFieldName = camelCaseFieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
//        String classType = TypeCovert.getClassType(fieldType);
//        String classTypeShort = TypeCovert.getClassTypeShort(fieldType);
//        this.classType = classType;
//        this.classTypeShort = classTypeShort;
    }

    public String getClassType() {
        return classType;
    }

    public String getclassTypeShort() {
        return classTypeShort;
    }
}
