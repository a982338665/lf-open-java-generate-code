package com.github.generatecode.util;

import com.github.generatecode.out.SetGenerateConf;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2021/4/7 13:57
 * @Description : jdbc封装
 */
public class JDBCUtils {


//    public static void main(String[] args) {
//        List<Map<String, Object>> data = getData("select * from test");
//        data.forEach(System.err::println);
//    }

    private static Connection loadingDriverAndGetConnect() {
        SetGenerateConf setGenerateConf = SetGenerateConf.getInstance();
        String driver = setGenerateConf.getDriver();
        String url = setGenerateConf.getUrl();
        String user = setGenerateConf.getUser();
        String password = setGenerateConf.getPassword();
        try {
            Class.forName(driver);//注册加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("驱动加载失败：====" + e.getLocalizedMessage());
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("获取连接失败：====" + e.getLocalizedMessage());
        }
        return conn;
    }

    /**
     * 根据sql查询结果集
     *
     * @param sql
     * @return
     */
    public static List<Map<String, Object>> getData(String sql) {
        System.err.println(sql);
        List<Map<String, Object>> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = loadingDriverAndGetConnect();
            //编写sql
//            String sql = "show create table test";
            //创建语句执行者
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            //获取列数
            int columnCount = metaData.getColumnCount();
            if (columnCount > 0) {
                //遍历行数
                while (rs.next()) {
                    HashMap<String, Object> map = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        Object object = rs.getObject(i);
                        String columnName = metaData.getColumnName(i);
                        map.put(columnName, object);
                    }
                    list.add(map);
                }
            }
//            list.forEach(System.err::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, stmt, conn);
        }
        return list;
    }

    /**
     * 关闭资源
     *
     * @param rs
     * @param stmt
     * @param conn
     */

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        close(rs);
        close(stmt);
        close(conn);
    }

    /**
     * 关闭close资源
     *
     * @param conn
     */
    public static void close(Connection conn) {
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭ResultSet资源
     *
     * @param rs
     */

    public static void close(ResultSet rs) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭Statement资源
     *
     * @param stmt
     */

    public static void close(Statement stmt) {
        if (null != stmt) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
