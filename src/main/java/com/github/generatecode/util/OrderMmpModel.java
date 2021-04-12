package com.github.generatecode.util;
import java.util.Date;
import java.lang.String;
import java.lang.Long;

import java.util.Map;

/**
 * @author : 皇夜_
 * @URL : CSDN 皇夜_
 * @createTime : 2021-4-12 10:13:26
 * @Description : 生成模板映射类- 存储模板对应关系
 *
 * 模板文件所在位置
 *      模板文件定义规则 ：
 *      文件内容规则定义：
 *          文件名称：OrderMmpDao.java
 *          表名称：t_s_order_mmp
 *          文件内容：--
 * 代码生成位置
 */
public class OrderMmpModel {


        /**
        * 主键
        *
        */
        private Long id;

        /**
        * 姓名
        *
        */
        private String name;

        /**
        * 产品类型
        *
        */
        private String productType;

        /**
        * 时间
        *
        */
        private Date createTime;




        public Long getId() {
            return id;
        }

        public void setId(Long id){
            this.id= id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name){
            this.name= name;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType){
            this.productType= productType;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime){
            this.createTime= createTime;
        }

}

