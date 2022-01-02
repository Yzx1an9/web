package com.yzx.service;

import com.yzx.bean.User;

import java.sql.Connection;

public interface UserService {
    void registUser(User user);

    User login(User user);

    boolean isUsed(String username,String email);

    boolean UsernameIsUsed(String username);
}
