package com.github.generatecode.outbean;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/3/18 11:10
 * @Description : 对外的数据库基本属性
 */
public class JdbcProperties {

    private String driverName = "com.mysql.jdbc.Driver";
    private String url;
    private String pwd;
    private String userName;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private static JdbcProperties jdbcProperties = new JdbcProperties();

    private JdbcProperties(){}

    public static JdbcProperties getInstance(){
        return jdbcProperties;
    }

    @Override
    public String toString() {
        return "JdbcProperties{" +
                "driverName='" + driverName + '\'' +
                ", url='" + url + '\'' +
                ", pwd='" + pwd + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
