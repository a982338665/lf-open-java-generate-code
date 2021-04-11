package com.github.generatecode.out;

import com.github.generatecode.model.FieldInfo;
import com.github.generatecode.model.MatchKeywordStartToEnd;
import com.github.generatecode.model.OutTableInfo;
import com.github.generatecode.model.TableInfo;
import com.github.generatecode.template.TypeCovert;
import com.github.generatecode.util.ClassUtil;
import com.github.generatecode.util.RegexMatches;
import com.github.generatecode.util.StringUtils;
import com.github.generatecode.util.TextUtil;

import java.util.*;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/7 16:46
 * @Description : 代码生成工具类
 */
public class GenerateCode {


    /**
     * 说明文档：
     * 属性
     * $author 设置中的作者 java.lang.String
     * $encode 设置的编码 java.lang.String
     * $modulePath 选中的module路径 java.lang.String
     * $projectPath 项目绝对路径 java.lang.String
     * <p>
     * 对象
     * $tableInfo 表对象
     * obj 表原始对象 com.intellij.database.model.DasTable
     * name 表名（转换后的首字母大写）java.lang.String
     * comment 表注释 java.lang.String
     * fullColumn 所有列 java.util.List<ColumnInfo>
     * pkColumn 主键列 java.util.List<ColumnInfo>
     * otherColumn 其他列 java.util.List<ColumnInfo>,除主键以外的列
     * savePackageName 保存的包名 java.lang.String
     * savePath 保存路径 java.lang.String
     * saveModelName 保存的model名称 java.lang.String
     * columnInfo 列对象
     * obj 列原始对象 com.intellij.database.model.DasColumn
     * name 列名（首字母小写） java.lang.String
     * comment 列注释 java.lang.String
     * type 列类型（类型全名） java.lang.String
     * shortType 列类型（短类型） java.lang.String
     * custom 是否附加列 java.lang.Boolean
     * ext 附加字段（Map类型） java.lang.Map<java.lang.String, java.lang.Object>
     * $tableInfoList java.util.List<TableInfo>所有选中的表
     * $importList 所有需要导入的包集合 java.util.Set<java.lang.String>
     * <p>
     * 回调
     * &callback        setFileName(String) 设置文件储存名字
     * setSavePath(String) 设置文件储存路径，默认使用选中路径
     * <p>
     * 工具
     * $tool
     * firstUpperCase(String name) 首字母大写方法
     * firstLowerCase(String name) 首字母小写方法
     * getClsNameByFullName(String fullName) 通过包全名获取类名
     * getJavaName(String name) 将下划线分割字符串转驼峰命名(属性名)
     * getClassName(String name) 将下划线分割字符串转驼峰命名(类名)
     * hump2Underline(String str) 将驼峰字符串转下划线字符串
     * append(Object... objs) 多个数据进行拼接
     * newHashSet(Object... objs) 创建一个HashSet对象
     * newArrayList(Object... objs) 创建一个ArrayList对象
     * newLinkedHashMap() 创建一个LinkedHashMap()对象
     * newHashMap() 创建一个HashMap()对象
     * getField(Object obj, String fieldName) 获取对象的属性值,可以访问任意修饰符修饰的属性.配合debug方法使用.
     * call(Object... objs) 空白执行方法,用于调用某些方法时消除返回值
     * debug(Object obj) 调式方法,用于查询对象结构.可查看对象所有属性与public方法
     * serial() 随机获取序列化的UID
     * service(String serviceName, Object... param)远程服务调用
     * parseJson(String) 将字符串转Map对象
     * toJson(Object, Boolean) 将对象转json对象，Boolean：是否格式化json，不填时为不格式化。
     * toUnicode(String, Boolean) 将String转换为unicode形式，Boolean：是否转换所有符号，不填时只转换中文及中文符号。
     * $time
     * currTime(String format) 获取当前时间，指定时间格式（默认：yyyy-MM-dd HH:mm:ss）
     * $generateService
     * run(String, Map<String,Object>) 代码生成服务，参数1：模板名称，参数2：附加参数。
     */
    public static void generateCode() {
        SetGenerateConf instance = SetGenerateConf.getInstance();
        List<String> allFile = TextUtil.getAllFile(instance.getTemplateUrl(), false);

        //获取需要生成的表结构信息模拟数据
//        List<OutTableInfo> tableList = instance.getTableList();
        List<OutTableInfo> tableList = getTableOutInfoMock();
        //解析数据表信息并赋值list
//        List<TableInfo> tableInfoList =  new ArrayList<>();
        List<TableInfo> tableInfoList = getTableInfoMock(tableList);
        for (String path : allFile
        ) {
            //读取模板信息 - 并且替换相关表结构
            final String[] reader = {TextUtil.readerStr(path)};
            //1.去掉注释生成---指模板中的注释而非代码注释
            removeNote(reader);
            String text = reader[0];
            //2.解析生成文件的路径
            String filePath;
            try {
                filePath = analysisGenerateCodePath(text);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            System.err.println(filePath);
            //3.基础常量替换,替换reader中的常量定义数据 => 指可以修改的自定义规则
            tHbaseVar(reader);
            //4.依据不同的表生成不同的数据文件
            for (TableInfo table : tableInfoList
            ) {
                String coperReader = reader[0];
                //4.1.数据库解析常量替换，替换基本属性信息
                coperReader = dealbaseInfo(coperReader, table);
                //4.2 针对每个表都有不一样的文件名，需要将文本文件拆分为两部分 2.1 文件名 2.2 文件内容
                String[] packages = coperReader.split("package");
                String fileName = packages[0].trim();
                String aPackage = packages[1];
                String content = StringUtils.concat("package", aPackage);
//                System.err.println(fileName + "=========\n" + content);
                //4.3 循环问题解决
                MatchKeywordStartToEnd e = RegexMatches.matchKeywordStartToEndFindoneRegexLimit1("#foreach_start", "#foreach_end", content);
                Map<String, Object> analyseArrayAndTmpValMap = analyseArrayAndTmpVal(e, table, content);
                String tmpVarOut = (String) analyseArrayAndTmpValMap.get("tmpVarOut");
                String toReplace = (String) analyseArrayAndTmpValMap.get("toReplace");
                List<FieldInfo> fieldInfos = (List<FieldInfo>) analyseArrayAndTmpValMap.get("fieldInfos");
                String replace = content.replace(e.getKeywordFull(), toReplace);
                System.err.println(replace);
                //4.4  循环里面包if问题解决

                //4.5 if问题单独解决

            }
        }
    }

    private static void tHbaseVar(String[] reader) {
        Map<String, String> dynamicMap = SetGenerateConf.get_dynamic_map();
        for (Map.Entry<String, String> entry : dynamicMap.entrySet()) {
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            reader[0] = reader[0].replace(mapKey, mapValue);
        }
    }


    private static Map<String, Object> analyseArrayAndTmpVal(MatchKeywordStartToEnd e, TableInfo table, String content) {
        Map<String, Object> baseInfo = new HashMap<>();
        String tmpVarOut = "";
        String toReplace = "";
        List<FieldInfo> fieldInfos = new ArrayList<>();
        if (e != null) {
            String keyword = e.getKeyword();
            //解析foreach后面的括号
            MatchKeywordStartToEnd r = RegexMatches.matchKeywordStartToEndFindoneRegexLimit1("(", ")", keyword);
            if (r != null) {
                //获取括号内容
                String kuohao = r.getKeyword();
                String[] ins = kuohao.split(" in ");
                if (ins.length != 2) {
                    try {
                        throw new Exception("#foreach 后面的格式不规范！");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    String tmpVar = ins[0].trim();
                    tmpVarOut = tmpVar.replace("$", "");
                    String baseVar = ins[1].trim();
                    //截取前缀 $tableInfo. TODO 后面需要去判断是哪个信息集合的前缀，暂时只截取此前缀
                    baseVar = baseVar.replace("$tableInfo.", "");
                    try {
                        //获取该表字段信息的值
                        fieldInfos = (List<FieldInfo>) ClassUtil.getPropertyValue(table, baseVar);
//                        System.err.println("fieldInfos----" + fieldInfos);
                        baseInfo.put("tmpVarOut", tmpVarOut);
                        baseInfo.put("fieldInfos", fieldInfos);
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            //解析完成后，需要将foreach中（）及内容替换为空
            keyword = keyword.replace(r.getKeywordFull(), "");
            //匹配处理for循环中的对象数据，匹配以该值开头,以.结束的数据
            MatchKeywordStartToEnd rif = RegexMatches.matchKeywordStartToEndFindoneRegexLimit1(StringUtils.concat("$", tmpVarOut, "."), " ", keyword);
            //找到替换的对应关系，片段循环
//            System.err.println("keyword===" + keyword);
            //首次匹配需要生成foreach循环文本 ， 第二次开始要进行整行文本准备
            if (rif != null) {
//                System.err.println(rif.getKeyword());
                for (FieldInfo fieldInfo : fieldInfos) {
                    try {
                        String tmpKeyword = keyword;
                        String propertyValue = (String) ClassUtil.getPropertyValue(fieldInfo, rif.getKeyword());
                        tmpKeyword = tmpKeyword.replace(rif.getKeywordFull(), propertyValue);
//                        System.err.println(tmpKeyword);
                        String result = anaylseForeachData(tmpVarOut, fieldInfo, tmpKeyword);
//                        System.err.println(result);
                        toReplace = StringUtils.concat(toReplace, result);
//                        System.err.println(s);
                    } catch (IllegalAccessException ex) {
                        System.err.println("获取字段属性报错：" + keyword + "里面的" + rif.getKeyword());
                        ex.printStackTrace();
                    }
                }
            }
//            System.err.println();
            baseInfo.put("toReplace", toReplace);


            //解析foreach里面是否有if条件 - TODO if语句暂不支持解析
            /*MatchKeywordStartToEnd rif = RegexMatches.matchKeywordStartToEndFindoneRegexLimit1("#if_start", "#if_end", keyword);
            if (r != null) {
                //获取括号内容
                String kuohao = r.getKeyword();
                String[] ins = kuohao.split(" in ");
                if (ins.length != 2) {
                    try {
                        throw new Exception("#foreach 后面的格式不规范！");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    String tmpVar = ins[0].trim();
                    tmpVarOut = tmpVar.replace("$", "");
                    String baseVar = ins[1].trim();
                    //截取前缀 $tableInfo. TODO 后面需要去判断是哪个信息集合的前缀，暂时只截取此前缀
                    baseVar = baseVar.replace("$tableInfo.", "");
                    try {
                        //获取该表字段信息的值
                        fieldInfos = (List<FieldInfo>) ClassUtil.getPropertyValue(table, baseVar);
//                        System.err.println("fieldInfos----" + fieldInfos);
                        baseInfo.put("tmpVarOut", tmpVarOut);
                        baseInfo.put("fieldInfos", fieldInfos);
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                }
            }*/
        }
        return baseInfo;
    }

    private static String anaylseForeachData(String tmpVarOut, FieldInfo fieldInfo, String tmpKeyword) throws IllegalAccessException {
        MatchKeywordStartToEnd riff = RegexMatches.matchKeywordStartToEndFindoneRegexLimit1(StringUtils.concat("$", tmpVarOut, "."), " ", tmpKeyword);
        if (riff != null) {
            //特殊的以;结尾的需要去掉
            if (riff.getKeyword().contains(";")) {
                riff.setKeyword(riff.getKeyword().replace(";", ""));
                String propertyValue2 = (String) ClassUtil.getPropertyValue(fieldInfo, riff.getKeyword());
                tmpKeyword = tmpKeyword.replace(riff.getKeywordFull(), " " + propertyValue2 + ";");
            } else {
                String propertyValue2 = (String) ClassUtil.getPropertyValue(fieldInfo, riff.getKeyword());
                tmpKeyword = tmpKeyword.replace(riff.getKeywordFull(), " " + propertyValue2);
            }
//            System.err.println(tmpKeyword);
            return anaylseForeachData(tmpVarOut, fieldInfo, tmpKeyword);
        } else {
            return tmpKeyword;
        }
    }

    private static String analysisGenerateCodePath(String text) throws Exception {
        SetGenerateConf instance = SetGenerateConf.getInstance();
        String[] packages = text.split("package");
        String filePath = "";
        if (packages.length < 2) {

            try {
                throw new Exception("首行缺少文件名申明！！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
//                String fileName = packages[0];
//                System.err.println("fileName==========" + fileName);
            String aPackage = packages[1];
            String[] packer = aPackage.split("\n");
            String packagePath = packer[0];
            packagePath = packagePath.replace(";", "").trim();
            filePath = instance.getGenerateCodeUrl() + StringUtils.convertPath(packagePath);
//                System.err.println(fileName + "=========" + filePath);
//                System.err.println(new File(filePath).getAbsolutePath());
        }
        if (StringUtils.isEmpty(filePath)) {
            throw new Exception("代码生成位置解析失败！" + filePath);
        }
        return filePath;
    }

    private static void removeNote(String[] reader) {
        List<MatchKeywordStartToEnd> list = RegexMatches.matchKeywordStartToEnd(RegexMatches.escapeExprSpecialWord(SetGenerateConf.getNoteTemplateStart())
                , RegexMatches.escapeExprSpecialWord(SetGenerateConf.getNoteTemplateEnd()), reader[0]);
        if (list.size() > 0) {
            list.forEach(v -> {
                reader[0] = reader[0].replace(v.getKeywordFull(), "");
            });
        }
    }

    private static String dealbaseInfo(String reader, TableInfo table) {
        //1.查询数据库常量位置信息
        List<MatchKeywordStartToEnd> list = RegexMatches.matchKeywordStartToEndFindoneRegex(SetGenerateConf.getDbTemplateStart()
                , SetGenerateConf.getDbTemplateEnd(), reader);
        for (MatchKeywordStartToEnd key : list
        ) {
            //获取属性值
            String keyword = key.getKeyword();
            //根据属性值取得解析表后的真实数值
            try {
                Object propertyValue = ClassUtil.getPropertyValue(table, keyword);
                reader = reader.replace(key.getKeywordFull(), String.valueOf(propertyValue));
                return dealbaseInfo(reader, table);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.err.println("配置的模板中有未识别的参数信息：：" + key.getKeywordFull());
            }
        }
        return reader;
    }

    /**
     * 获取tableInfo的模拟数据
     *
     * @param tableList
     */
    public static List<TableInfo> getTableInfoMock(List<OutTableInfo> tableList) {
        //假设每个表结构数据都解析为
        List<TableInfo> list = new ArrayList<>();
        for (int i = 0; i < tableList.size(); i++) {
            OutTableInfo v = tableList.get(i);
            TableInfo tableInfo = new TableInfo();
            String prefix = v.getPrefix();
            if (StringUtils.isEmpty(prefix)) {
                //判断是否驼峰命名
                tableInfo.setCamelCaseTableName(StringUtils.getCamelCase(v.getTableName(), v.isCamelCase()));
            } else {
                //不是空先截取前缀,主要是看驼峰命名中是否为 截取出前缀
                String trim = v.getTableName().replace(prefix, "").trim();
                tableInfo.setCamelCaseTableName(StringUtils.getCamelCase(trim, v.isCamelCase()));
            }
            tableInfo.setCamelCase(v.isCamelCase());
            tableInfo.setTableName(v.getTableName());
            tableInfo.setTableNote("表注释，待解析");
            //TODO 待替换-解析部分
            if (i % 2 == 0) {
                tableInfo.setFieldInfos(Arrays.asList(
                        new FieldInfo("id", "主键", StringUtils.getCamelCase("id", false), "bigint",
                                TypeCovert.getClassType("bigint"), TypeCovert.getClassTypeShort("bigint")),
                        new FieldInfo("name", "姓名", StringUtils.getCamelCase("name", false), "char", TypeCovert.getClassType("char"), TypeCovert.getClassTypeShort("char")),
                        new FieldInfo("product_type", "产品类型", StringUtils.getCamelCase("product_type", false), "varchar", TypeCovert.getClassType("varchar"), TypeCovert.getClassTypeShort("varchar")),
                        new FieldInfo("create_time", "时间", StringUtils.getCamelCase("create_time", false), "datetime", TypeCovert.getClassType("datetime"), TypeCovert.getClassTypeShort("datetime"))
                ));
            } else {
                tableInfo.setFieldInfos(Arrays.asList(
                        new FieldInfo("id1", "主键1", StringUtils.getCamelCase("id1", false), "bigint",
                                TypeCovert.getClassType("bigint"), TypeCovert.getClassTypeShort("bigint")),
                        new FieldInfo("name1", "姓名1", StringUtils.getCamelCase("name1", false), "char", TypeCovert.getClassType("char"), TypeCovert.getClassTypeShort("char")),
                        new FieldInfo("product_type1", "产品类型1", StringUtils.getCamelCase("product_type1", false), "varchar", TypeCovert.getClassType("varchar"), TypeCovert.getClassTypeShort("varchar")),
                        new FieldInfo("create_time1", "时间1", StringUtils.getCamelCase("create_time1", false), "datetime", TypeCovert.getClassType("datetime"), TypeCovert.getClassTypeShort("datetime"))
                ));
            }
            list.add(tableInfo);
        }
        list.forEach(System.err::println);
        return list;
    }

    /**
     * 获取tableInfo的表结构模拟数据
     */
    public static List<OutTableInfo> getTableOutInfoMock() {
        List<OutTableInfo> list = Arrays.asList(
                new OutTableInfo("t_s_user"),
                new OutTableInfo("t_s_order", "t_s_"),
                new OutTableInfo("t_s_order_mmp", "t_s_", true)

        );
        // t_s_user
        return list;
    }
}
