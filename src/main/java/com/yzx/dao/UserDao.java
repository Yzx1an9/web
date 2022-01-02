package com.yzx.dao;

import com.yzx.bean.User;

import java.sql.Connection;

public interface UserDao {
    int addUser( User user);

    User queryUserByName(String username,String email);

    User queryUserByNameAndPwd(String username,String password);

    boolean queryByUsername(String username);
}
