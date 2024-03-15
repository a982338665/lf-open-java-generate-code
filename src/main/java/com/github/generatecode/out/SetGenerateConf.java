package com.github.generatecode.out;


import com.github.generatecode.model.OutTableInfo;
import com.github.generatecode.util.StringUtils;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;

/**
 * 基础配置信息 - 对外暴露
 *
 * @author luofeng
 */

public class SetGenerateConf {

    private static SetGenerateConf setGenerateConf = new SetGenerateConf();

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    private String user = "root";
    private String password = "root123";

    private static String project = ".\\xxxx\\";
    private static String entityPath = "com\\kinovo\\xxxx\\domain";
    private static String entityC2sPath = "com\\kinovo\\xxxx\\domain\\c2s";
    private static String daoPath = "com\\kinovo\\xxxx\\mapper";
    private static String servicePath = "com\\kinovo\\xxxx\\service";
    private static String serviceImplPath = "com\\kinovo\\xxxx\\service\\impl";
    private static String controllerPath = "com\\kinovo\\xxxx\\controller";

    private SetGenerateConf() {

    }

    public static SetGenerateConf getInstance() {
        return setGenerateConf;
    }

    /**
     * 需要生成的表的集合
     */
    private List<OutTableInfo> tableList;

    public List<OutTableInfo> getTableList() {
        return tableList;
    }

    public void setTableList(List<OutTableInfo> tableList) {
        this.tableList = tableList;
    }

    /**
     * 读取模板的默认父路径
     */
    private String templateUrl = "./template";
    /**
     * 生成代码的默认父亲路径：设置为null时，表示无父路径，此时直接读取子路径作为生成路径
     *
     * @TODO -> 不便弃用，原计划作为组件引入，需要自动获取当前项目路径或者填写绝对路径
     */
    private String generateCodeUrl = "src/main/java";

    /**
     * 动态变量赋值map,不暴露出去
     */
    private static Map<String, String> DYNAMIC_MAP = new HashMap<>();

    /**
     * 模板变量组成规则：：前缀 + 申明的变量【只能是{0}】 + 后缀
     * {0}是用来占位的必须要保留，且外面不能在套{}，例如{{0}}
     */
    private static String varTemplate = "!##{0}##";


    /**
     * 数据库解析后生成变量的模板信息前缀
     */
    private static String dbTemplateStart = "$!{";
    /**
     * 数据库解析后生成变量的模板信息后缀
     */
    private static String dbTemplateEnd = "}";
    /**
     * 数据库解析后生成变量的模板信息注释前缀
     */
    private static String noteTemplateStart = "#!";
    /**
     * 数据库解析后生成变量的模板信息注释后缀
     */
    private static String noteTemplateEnd = "!#";

    public static String getNoteTemplateStart() {
        return noteTemplateStart;
    }

    public static void setNoteTemplateStart(String noteTemplateStart) {
        SetGenerateConf.noteTemplateStart = noteTemplateStart;
    }

    public static String getNoteTemplateEnd() {
        return noteTemplateEnd;
    }

    public static void setNoteTemplateEnd(String noteTemplateEnd) {
        SetGenerateConf.noteTemplateEnd = noteTemplateEnd;
    }

    private static Function<String, String> convertPkg = (e) -> e.replaceAll("\\\\", "\\.");

    /**
     * 申明一些内置的动态变量，可以修改，使变量在模板中可用
     */
    static {
        //表示当前时间
        DYNAMIC_MAP.put(getVarVal("nowDate"), new Date().toLocaleString());
        //表示作者，使用时模板为!##{author}##
        DYNAMIC_MAP.put(getVarVal("author"), "皇夜_");
        //表示作者，使用时模板为!##{author}##
        DYNAMIC_MAP.put(getVarVal("url"), "CSDN 皇夜_");
        put_dynamic_map("basePath", project);
        put_dynamic_map("entityPath", entityPath);
        put_dynamic_map("entityPkg", convertPkg.apply(entityPath));
        put_dynamic_map("entityC2sPath", entityC2sPath);
        put_dynamic_map("entityC2sPkg", convertPkg.apply(entityC2sPath));
        put_dynamic_map("daoPath", daoPath);
        put_dynamic_map("daoPkg", convertPkg.apply(daoPath));
        put_dynamic_map("servicePath", servicePath);
        put_dynamic_map("servicePkg", convertPkg.apply(servicePath));
        put_dynamic_map("serviceImplPath", serviceImplPath);
        put_dynamic_map("serviceImplPkg", convertPkg.apply(serviceImplPath));
        put_dynamic_map("controllerPath", controllerPath);
        put_dynamic_map("controllerPkg", convertPkg.apply(controllerPath));
        put_dynamic_map("entitySuffix", "Entity");
        put_dynamic_map("entityC2sSuffix", "C2SEntity");
        put_dynamic_map("daoSuffix", "Mapper");
        put_dynamic_map("mapperSuffix", "Mapper");
        put_dynamic_map("serviceSuffix", "Service");
        put_dynamic_map("serviceImplSuffix", "ServiceImpl");
        put_dynamic_map("controllerSuffix", "Controller");
    }

    /**
     * 定义动态常量值
     */
    public static void put_dynamic_map(String key, String value) {
        List<String> xxx = Arrays.asList("entityPath","entityC2sPath", "daoPath","servicePath", "serviceImplPath", "controllerPath");
        if (xxx.contains(key)){
            String replace = key.replace("Path", "Pkg");

            DYNAMIC_MAP.put(getVarVal(replace), convertPkg.apply(value));
        }
        DYNAMIC_MAP.put(getVarVal(key), value);
    }

    /**
     * 定义动态常量值
     */
    public static Map<String, String> get_dynamic_map() {
        return DYNAMIC_MAP;
    }

    private static String getVarVal(String val) {
        String format = MessageFormat.format(varTemplate, StringUtils.concat("{", val, "}"));
        return format;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public String getGenerateCodeUrl() {
        return generateCodeUrl;
    }

    public void setGenerateCodeUrl(String generateCodeUrl) {
        this.generateCodeUrl = generateCodeUrl;
    }

    public static String getVarTemplate() {
        return varTemplate;
    }

    public static void setVarTemplate(String varTemplate) {
        SetGenerateConf.varTemplate = varTemplate;
    }

    public static String getDbTemplateStart() {
        return dbTemplateStart;
    }

    public static void setDbTemplateStart(String dbTemplateStart) {
        SetGenerateConf.dbTemplateStart = dbTemplateStart;
    }

    public static String getDbTemplateEnd() {
        return dbTemplateEnd;
    }

    public static void setDbTemplateEnd(String dbTemplateEnd) {
        SetGenerateConf.dbTemplateEnd = dbTemplateEnd;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
