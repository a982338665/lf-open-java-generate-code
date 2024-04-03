package com.github.generatecode.constant;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/7/14 21:32
 * @Description : 模板解析中用到的常量值
 */
public interface Constant {

    /**
     * 定义的类的模板里面，用来指定默认包的，会被作为分隔符
     */
    String PACKAGE_VAR = "package ";

    String SETFILENAME_START_VAR = "#setFileName[";
    String SETFILENAME_END_VAR = "]";

    String SETFILEPATH_START_VAR = "#setFilePath[";
    String SETFILEPATH_END_VAR = "]";

    /**
     * 指明该模板是否要自动导包，特指数据库字段对应的数据类型包
     */
    String AUTOIMPORT_VAR = "#autoImport";

    /**
     * #setFilePath[]中取出的路径中  解析出当前生成类的包名，此为正则起始位置
     */
    String PACKNAME_FORM_PATH_START_VAR = "/src/main/java/";
    /**
     * #setFilePath[]中取出的路径中  解析出当前生成类的包名，此为正则结束位置
     */
    String PACKNAME_FORM_PATH_END_VAR = "";

    /**
     * 类的第一行内容拼接  开头package
     */
    String PACKNAME_CONTENT_START_VAR = "package ";
    /**
     * 类的第一行内容拼接  结尾封号
     */
    String PACKNAME_CONTENT_END_VAR = ";\n";


    /**
     * 模板中的for循环处理
     ****************************************************************/
    String FOREACH_START_VAR = "#foreach_start";
    String FOREACH_END_VAR = "#foreach_end";
    String FOREACH_START_KH_VAR = "(";
    String FOREACH_END_KH_VAR = ")";
    String FOREACH_END_IN_VAR = " in ";


    String IF_START_VAR = "#if";
    String IF_END_VAR = "#end";
    String IF_START_KH_VAR = "[";
    String IF_END_KH_VAR = "]";
    String IF_END_IN_VAR = " in ";


    /**通过此来判断其是否为XML，XML为另一种格式解析*/
    String IS_XML_VAR = "#XML";



}
