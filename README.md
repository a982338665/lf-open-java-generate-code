# lf-open-java-generate-code
代码生成器

# 1 代码设计思想

## 1.1 用户可修改内容

    1.对外提供jdbc相关信息设置，以便解析数据库使用
    2.对外暴露模板定义所在位置 - 针对于不同架构，不同的人，会将自己定义的模板放在指定文件夹下以供使用
    3.支持多表一次生成，提供要生成表的List集合
    4.对外暴露的一些标准常量：例如 作者信息，邮件等
        SetGenerateConf.put_dynamic_map("test", "hhhhh");
        内置的常量信息有：
        DYNAMIC_MAP.put(getVarVal("nowDate"), new Date().toLocaleString());
        //表示作者，使用时模板为!##{author}##
        DYNAMIC_MAP.put(getVarVal("author"), "皇夜_");
        //表示作者，使用时模板为!##{author}##
        DYNAMIC_MAP.put(getVarVal("url"), "CSDN 皇夜_");
        在模板中使用方式： !##{author}##
        信息需要替换则使用 SetGenerateConf.put_dynamic_map("author", "Mr.li");
    5.对外暴露的管道符申明：
        OutPipeFunction.PIPE_MAP.put("方法名","lambda表达式");       
    
## 1.2 模板设计使用方式
     
    1.注释：#!  注释内容   !#
    2.设置文件名称：#setFileName[ 文件名称 ] 必须在package之前，否则不解析
    3.表解析变量替换：!{camelCaseTableName}Model.java 
    4.常量信息替换：!##{author}##
    5.循环支持：
        #foreach_start($XXXX in $tableInfo.fieldInfos)
            /**
            * $XXXX.fieldNote
            * #! $XXXX.fieldNote2  注意：中间的空格为两格，一个用来匹配数据，一个用来做符号间隔!#
            */
            private $XXXX.classTypeShort  $XXXX.camelCaseFieldName;
        #foreach_end
    6.循环之管道符支持：|upper
        #foreach_start($XXXX in $tableInfo.fieldInfos)
            public $XXXX.classTypeShort  get$XXXX.camelCaseFieldName|upper () {
                return $XXXX.camelCaseFieldName;
            }
        #foreach_end
        注意：
           $XXXX.camelCaseFieldName 强调必须以空格结尾以便识别具体参数
           $XXXX.camelCaseFieldName|upper 后面紧跟|upper 表示管道符，转换为首字母大写，仅支持一个
