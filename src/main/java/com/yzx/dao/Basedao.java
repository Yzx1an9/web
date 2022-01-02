package com.yzx.dao;

import com.yzx.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class Basedao {
    /**
     * 通用的增删改操作
     * @param conn
     * @param sql
     * @param args
     * @return 返回改变数据库内容的行数，return -1 表示操作失败
     */
    private QueryRunner queryRunner = new QueryRunner();
    public int update(String sql,Object...args){
        Connection conn = null;
        try {
            conn = JdbcUtils.GetConnection();
            return queryRunner.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    public <T> T queryForOne(Class<T> clazz,String sql,Object...args){
        BeanHandler<T> tBeanHandler = new BeanHandler<>(clazz);
        T query = null;
        Connection conn = null;
        try {
            conn = JdbcUtils.GetConnection();
            query = queryRunner.query(conn, sql, tBeanHandler, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
        return query;
    }

    public <T> List<T> queryForList(Class<T> clazz, String sql, Object...args){
        BeanListHandler<T> tBeanHandler = new BeanListHandler<T>(clazz);
        List<T> res = null;
        Connection conn = null;
        try {
            conn = JdbcUtils.GetConnection();
            res = queryRunner.query(conn, sql, tBeanHandler, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
        return res;
    }

    public <T> T queryForSingleValue(String sql,Object...args){
        ScalarHandler<T> tScalarHandler = new ScalarHandler<>();
        T res = null;
        Connection conn = null;
        try {
            conn = JdbcUtils.GetConnection();
            res = (T)queryRunner.query(conn, sql, tScalarHandler, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
        return res;
    }
}
