package com.huawei.oa.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @Author:VictoryChan
 * @Description：JDBC工具类
 * @Date:Creat in17:44 2022/8/28
 * @Modified By:
 */
public class DBUtil {

    //静态变量：在类加载时执行。
    //并且是由顺序的，自上而下的顺序。
    //属性资源文件绑定
    private static ResourceBundle bundle =ResourceBundle.getBundle("resources.jdbc");
    //根据属性配置文件key获取value
    private static String driver=bundle.getString("driver");
    private static String url=bundle.getString("url");
    private static String user=bundle.getString("user");
    private static String password=bundle.getString("password");


    static {
        //注册驱动(只需要注册一次，放在静态代码块中。DBUtil类加载时执行)
        try {
            //"com.mysql.jdbc.Driver"是连接数据库的驱动，不能写死，因为以后可能还需要连接Oracle数据库。
            //如果连接Oracle数据库的时候，还需要修改java代码，显然违背了OCP开闭原则。
            //OCP开闭原则：对扩展开放，对修改关闭。（什么是符合OCP呢？在进行功能扩展的时候，不需要修改java源代码。）
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接对象
     * @return conn 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        //获取连接
        Connection conn= DriverManager.getConnection(url,user,password);
        return conn;
    }

    /**
     * 释放资源
     * @param conn 连接对象
     * @param ps 数据库操作对象
     * @param rs 结果集对象
     */
    public static void close(Connection conn, Statement ps,ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    //注册驱动 ok
    //获取连接 ok
    //获取数据库操作对象
    //执行SQL语句
    //处理查询结果集
    //释放资源
}
