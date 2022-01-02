package com.yzx.dao.impl;

import com.yzx.bean.User;
import com.yzx.dao.Basedao;
import com.yzx.dao.UserDao;

import java.sql.Connection;
import java.util.function.UnaryOperator;

public class UserDaoImpl extends Basedao implements UserDao {
    @Override
    public int addUser( User user) {
        String sql = "insert into user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());

    }

    @Override
    public User queryUserByName( String username,String email) {
        String sql = "select * from user where username = ? or email = ?";
        return queryForOne(User.class,sql,username,email);
    }

    @Override
    public User queryUserByNameAndPwd( String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public boolean queryByUsername(String username) {
        String sql = "select * from user where username = ?";
        if(queryForOne(User.class,sql,username) == null){
            return false;
        }else return true;
    }
}
