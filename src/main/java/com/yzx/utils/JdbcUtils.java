package com.yzx.utils;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    /**
     * 防止对Jdbc工具类进行实例化;
     */
    private JdbcUtils(){

    }
    /**
     * 静态代码块类加载时调用,直接注册驱动
     */

//    static {
//        InputStream resourceAsStream = Jdbcutils.class.getClassLoader().getResourceAsStream("jdb.properties");
//        Properties pro = new Properties();
//        try {
//            pro.load(resourceAsStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String driver = pro.getProperty("driver");
//        try {
//            Class.forName(driver);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    private static DataSource dataSource;

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    static {
        try {

            Properties pro = new Properties();
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pro.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection GetConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection == null){
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            threadLocal.set(connection);
        }
        return connection;

//        InputStream resourceAsStream = Jdbcutils.class.getClassLoader().getResourceAsStream("aa/jdb.properties");
//
//        Properties pro = new Properties();
//        try {
//            pro.load(resourceAsStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String url = pro.getProperty("url");
//        String user = pro.getProperty("user");
//        String password = pro.getProperty("password");
//        return DriverManager.getConnection(url,user,password);
    }

    public static void CommitAndClose(){
        Connection connection = threadLocal.get();
        if(connection != null){
            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                Close(connection);
            }

        }
        threadLocal.remove();
    }

    public static void RollbackAndClose(){
        Connection connection = threadLocal.get();
        if(connection != null){
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                Close(connection);
            }
        }
        threadLocal.remove();
    }

    private static void Close(Connection con){
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

