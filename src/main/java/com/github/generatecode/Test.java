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
        instance.setTemplateUrl("D:\\history-git\\git-20200729\\lf-open-java-generate-code\\template\\mp");
        SetGenerateConf.put_dynamic_map("test", "hhhhh");
        instance.setTableList(Arrays.asList(
                new OutTableInfo()
        ));
        GenerateCode.generateCode();
    }
}
