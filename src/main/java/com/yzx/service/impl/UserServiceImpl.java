package com.yzx.service.impl;

import com.yzx.bean.User;
import com.yzx.dao.Basedao;
import com.yzx.dao.UserDao;
import com.yzx.dao.impl.UserDaoImpl;
import com.yzx.service.UserService;

import java.sql.Connection;
import java.util.function.UnaryOperator;


public class UserServiceImpl implements UserService {

    private UserDao bd = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        bd.addUser(user);
    }

    @Override
    public User login(User user) {
        return bd.queryUserByNameAndPwd(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean isUsed(String username,String email) {
        if(bd.queryUserByName(username,email) == null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean UsernameIsUsed(String username) {
        return bd.queryByUsername(username);
    }
}
