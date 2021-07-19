package com.github.generatecode.model;

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
     * 数据库字段的类型 - 无数字
     */
    private String fieldTypeNoNum;
    /**
     * 类属性字段的类型
     */
    private String classType;
    /**
     * 类属性字段的类型
     */
    private String classTypeShort;
    /**
     * 是否为主键
     */
    private boolean primaryKey;



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
    public FieldInfo(String fieldName, String fieldNote, String camelCaseFieldName, String fieldType, String classType, String classTypeShort,String fieldTypeNoNum) {
        this.fieldName = fieldName;
        this.fieldNote = fieldNote;
        this.camelCaseFieldName = camelCaseFieldName;
        this.fieldType = fieldType;
        this.classType = classType;
        this.classTypeShort = classTypeShort;
        this.fieldTypeNoNum = fieldTypeNoNum;
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


    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getFieldTypeNoNum() {
        return fieldTypeNoNum;
    }

    public void setFieldTypeNoNum(String fieldTypeNoNum) {
        this.fieldTypeNoNum = fieldTypeNoNum;
    }

    @Override
    public String toString() {
        return "FieldInfo{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldNote='" + fieldNote + '\'' +
                ", camelCaseFieldName='" + camelCaseFieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", fieldTypeNoNum='" + fieldTypeNoNum + '\'' +
                ", classType='" + classType + '\'' +
                ", classTypeShort='" + classTypeShort + '\'' +
                ", primaryKey=" + primaryKey +
                '}';
    }
}
