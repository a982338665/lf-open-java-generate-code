package com.github.generatecode.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * create by lishengbo on 2017-12-20 11:42
 */
public class JDBCUtils {

    /**
     * Statement 和 PreparedStatement之间的关系和区别.
     * 关系：PreparedStatement继承自Statement,都是接口
     * 区别：PreparedStatement可以使用占位符，是预编译的，批处理比Statement效率高
     */
    public static void conn() {
        String URL = "jdbc:mysql://127.0.0.1:3306/Supermarket?characterEncoding=utf-8";
        String USER = "root";
        String PASSWORD = "123";
        // 1.加载驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获得数据库链接
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // 3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
            String name = "张三";
            //预编译
            String sql = "select * from userinfo where UserName=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
//			String sql="select * from userinfo where UserName='"+name+"'";
//			Statement statement = conn.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
            // 4.处理数据库的返回结果(使用ResultSet类)
            while (rs.next()) {
                System.out.println(rs.getString("UserName") + " " + rs.getString("Password"));
            }
            // 关闭资源
            conn.close();
            rs.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
