package com.github.generatecode;

import com.github.generatecode.model.OutTableInfo;
import com.github.generatecode.out.OutPipeFunction;
import com.github.generatecode.out.SetGenerateConf;
import com.github.generatecode.template.TypeCovert;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/7 13:53
 * @Description :
 */
public class GenerateCode {

    public static void main(String[] args) {
//        demo();
        SetGenerateConf instance = SetGenerateConf.getInstance();
        //================================================================================================= 公共部分
        instance.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        instance.setUser("root");
        instance.setPassword("password");
        instance.setDriver("com.mysql.jdbc.Driver");

        //================================================================================================ 私有部分
        String project = ".\\kinovo-performance\\";
        String entityPath = "com\\kinovo\\performance\\domain";
        String entityC2sPath = "com\\kinovo\\performance\\domain\\c2s";
        String daoPath = "com\\kinovo\\performance\\mapper";
        String servicePath = "com\\kinovo\\performance\\service";
        String serviceImplPath = "com\\kinovo\\performance\\service\\impl";
        String controllerPath = "com\\kinovo\\performance\\controller";

        SetGenerateConf.put_dynamic_map("basePath", project);
        SetGenerateConf.put_dynamic_map("entityPath", entityPath);
        SetGenerateConf.put_dynamic_map("entityC2sPath", entityC2sPath);
        SetGenerateConf.put_dynamic_map("daoPath", daoPath);
        SetGenerateConf.put_dynamic_map("servicePath", servicePath);
        SetGenerateConf.put_dynamic_map("serviceImplPath", serviceImplPath);
        SetGenerateConf.put_dynamic_map("controllerPath", controllerPath);
        //定义作者信息
        SetGenerateConf.put_dynamic_map("author", "皇夜_");
        //需要生成的表
        instance.setTableList(Arrays.asList(
                new OutTableInfo("test", "")
//                ,new OutTableInfo("test44", "")
        ));
        com.github.generatecode.out.GenerateCode.generateCode();
    }



    private static void demo() {
        SetGenerateConf instance = SetGenerateConf.getInstance();
        //================================================================================================= 公共部分
        instance.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        instance.setUser("root");
        instance.setPassword("password");
        instance.setDriver("com.mysql.jdbc.Driver");

        //数据库类型 == 类类型的对应关系 可覆盖
        Map<String, String> types = TypeCovert.getInstance();
        types.put("datetime", "java.time.LocalDateTime");
        types.put("timestamp", "java.time.LocalDateTime");

        //预置模板可能会用到的管道符
        Map<String, Function> pipeMap = OutPipeFunction.PIPE_MAP;
        Function<Boolean, String> judgeTrue = (e) -> e == true ? "true" : "false";
        Function<String, String> judgeDateTime = (e) -> e.equals("LocalDateTime") ? "true" : "false";
        pipeMap.put("judgeTrue", judgeTrue);
        pipeMap.put("judgeDateTime", judgeDateTime);
        //以下为管道符的使用方式


        //================================================================================================ 私有部分
        //自定义模板存放位置
        instance.setTemplateUrl("./template");
        //此处不支持反斜杠，预置包名的  默认值，此处配置则不需要 针对每个人出不同给的模板,模板预置的初始路径
        String project = ".\\kinovo-performance\\";
        String entityPath = "com\\kinovo\\performance\\domain";
        String entityC2sPath = "com\\kinovo\\performance\\domain\\c2s";
        String daoPath = "com\\kinovo\\performance\\mapper";
        String servicePath = "com\\kinovo\\performance\\service";
        String serviceImplPath = "com\\kinovo\\performance\\service\\impl";
        String controllerPath = "com\\kinovo\\performance\\controller";

        SetGenerateConf.put_dynamic_map("basePath", project);
        SetGenerateConf.put_dynamic_map("entityPath", entityPath);
        SetGenerateConf.put_dynamic_map("entityC2sPath", entityC2sPath);
        SetGenerateConf.put_dynamic_map("daoPath", daoPath);
        SetGenerateConf.put_dynamic_map("servicePath", servicePath);
        SetGenerateConf.put_dynamic_map("serviceImplPath", serviceImplPath);
        SetGenerateConf.put_dynamic_map("controllerPath", controllerPath);
//        可改后缀
//        SetGenerateConf.put_dynamic_map("entitySuffix", "Entity");
//        SetGenerateConf.put_dynamic_map("entityC2sSuffix", "C2SEntity");
//        SetGenerateConf.put_dynamic_map("daoSuffix", "Mapper");
//        SetGenerateConf.put_dynamic_map("mapperSuffix", "Mapper");
//        SetGenerateConf.put_dynamic_map("serviceSuffix", "Service");
//        SetGenerateConf.put_dynamic_map("serviceImplSuffix", "ServiceImpl");
//        SetGenerateConf.put_dynamic_map("controllerSuffix", "Controller");
        //定义作者信息
        SetGenerateConf.put_dynamic_map("author", "皇夜_");
        //需要生成的表
        instance.setTableList(Arrays.asList(
                new OutTableInfo("test", "")
//                ,new OutTableInfo("test44", "")
        ));
        com.github.generatecode.out.GenerateCode.generateCode();
    }
}
