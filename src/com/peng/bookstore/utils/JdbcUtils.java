package com.peng.bookstore.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {
    private static DataSource dataSource = new ComboPooledDataSource();
    private static ThreadLocal<Connection> tlc = new ThreadLocal<>();

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            Connection con = tlc.get();
            if (con != null) {
                return con;    //处于事务
            }
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //    开启事务
    public static void beginTransaction() {
        Connection con = tlc.get();
        if (con != null) {
            throw new RuntimeException("您已经开启了事务");
        }
        con = getConnection();
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //    提交事务
    public static void commitTransaction() {
        Connection con = tlc.get();
        if (con == null) {
            throw new RuntimeException("您还没开启事务，无法提交");
        }
        try {
            con.commit();
            tlc.remove();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //    回滚事务
    public static void rollbackTransaction() {
        Connection con = tlc.get();
        if (con == null) {
            throw new RuntimeException("您还没开启事务，无法回滚");
        }
        try {
            con.rollback();
            tlc.remove();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //释放连接
    public static void releaseConnection(Connection connection) {
        Connection con = tlc.get();
        try {
            if (con != null) connection.close();
            if (con != connection) connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
