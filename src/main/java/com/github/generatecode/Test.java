package com.github.generatecode;

import com.github.generatecode.model.OutTableInfo;
import com.github.generatecode.out.GenerateCode;
import com.github.generatecode.out.SetGenerateConf;

import java.util.Arrays;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/7 13:53
 * @Description :
 */
public class Test {

    public static void main(String[] args) {
        SetGenerateConf instance = SetGenerateConf.getInstance();
//        instance.setTemplateUrl("D:\\history-git\\git-20200729\\lf-open-java-generate-code\\template\\xml");
        instance.setTemplateUrl("D:\\history-git\\git-20200729\\lf-open-java-generate-code\\template\\mp");

//        instance.setDefaultBasePath("./xxx/");
//        此处不支持反斜杠，预置包名的  默认值，此处配置则不需要 针对每个人出不同给的模板
        SetGenerateConf.put_dynamic_map("basePath", ".\\xxxx\\");
        instance.setTableList(Arrays.asList(
                new OutTableInfo("t_s_test", "t_s_")
        ));
        GenerateCode.generateCode();

//        String url = SetGenerateConf.getInstance().getUrl();
//        String[] split = url.split("\\?");
//        if(split.length==2){
//            String urls = split[0];
//            int i = urls.lastIndexOf("/");
////            urls.la
//            String substring = urls.substring(i+1);
//            System.err.println(substring);
//        }
    }
}
