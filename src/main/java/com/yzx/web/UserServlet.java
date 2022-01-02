package com.yzx.web;


import com.google.gson.Gson;
import com.yzx.bean.User;
import com.yzx.service.impl.UserServiceImpl;
import com.yzx.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class UserServlet extends BaseServlet {
    private UserServiceImpl us = new UserServiceImpl();

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }


    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = us.login(new User(null, username, password, null));
        if (user == null) {
            req.setAttribute("error", "用户名或者密码错误!!");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);

        } else {


            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }

    }

    public void isUseful(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        Gson gson = new Gson();
        HashMap<String,Object> map = new HashMap<>();
        map.put("isUseful",us.UsernameIsUsed(username));
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }

    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        String s = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");

        if (s != null && s.equalsIgnoreCase(code) && !us.isUsed(username, email)) {
            User user = new User(null, username, password, email);
            us.registUser(user);
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
        } else {
            if (!s.equalsIgnoreCase(code)) {
                req.setAttribute("error", "验证码错误");
            } else {
                req.setAttribute("error", "用户名或者邮箱已存在!");
            }
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }
}
