package com.yzx.web;

import com.google.gson.Gson;
import com.yzx.bean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class AjaxServlet extends BaseServlet{
    protected void ajaxtest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(1,"2143","12442","#123123");
        Gson gson = new Gson();
        String s = gson.toJson(user);
        resp.getWriter().write(s);
        System.out.println(req.getParameter("username"));
        System.out.println(req.getParameter("password"));
    }
}
