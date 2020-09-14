package com.zyjy.qq.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    /**
     * 获取数据库连接
     *
     * @return 数据库连接对象
     */
    public static Connection getConnection() {
        Properties prop = new Properties();
        Connection conn = null;
        try {
            prop.load(new BufferedReader(new FileReader("config/jdbc.properties")));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源
     *
     * @param ps 预编译对象
     * @param rs 结果集对象
     */
    public static void release(PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
