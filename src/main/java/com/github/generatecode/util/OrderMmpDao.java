package com.github.generatecode.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 皇夜_
 * @URL : CSDN 皇夜_
 * @createTime : 2021-4-12 1:09:44
 * @Description : 生成模板映射类- 存储模板对应关系
 * <p>
 * 模板文件所在位置
 * 模板文件定义规则 ：
 * 文件内容规则定义：
 * 文件名称：OrderMmpDao.java
 * 表名称：t_s_order_mmp
 * 文件内容：--
 * 代码生成位置
 */
public class OrderMmpDao {

    static final Map<String, String> map = new HashMap<>();


    /**
     * 主键
     * 暂无，请查看该处属性设置是否正确01？fieldNote2
     */
    private Long id;

    /**
     * 姓名
     * 暂无，请查看该处属性设置是否正确01？fieldNote2
     */
    private String name;

    /**
     * 产品类型
     * 暂无，请查看该处属性设置是否正确01？fieldNote2
     */
    private String productType;

    /**
     * 时间
     * 暂无，请查看该处属性设置是否正确01？fieldNote2
     */
    private Date createTime;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getProductType() {
        return productType;
    }


    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
