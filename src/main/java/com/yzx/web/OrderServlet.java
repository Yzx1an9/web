package com.yzx.web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yzx.bean.*;
import com.yzx.service.OrderService;
import com.yzx.service.impl.OrderServiceImpl;
import com.yzx.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServlet extends BaseServlet {
    private OrderService os = new OrderServiceImpl();

    public void addordertouser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer id = user.getId();
        Order order = os.addOrder(cart, id);
        JdbcUtils.CommitAndClose();
        req.getSession().setAttribute("order", order);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp?orderid=" + order.getOrderId());
    }

    public void checkmyorder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if(user == null){
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }
        Integer id = user.getId();
        List<Order> orders = os.checAllOrder(id);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);


    }

    public void checkorderitem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User use = new User(1,"123","2144","123213");
        Gson gson = new Gson();
        String s = gson.toJson(use);
        gson.fromJson(s,User.class);
        List<User> list = new ArrayList<>();
        list.add(use);
        list.add(new User());
        String s1 = gson.toJson(list);
        gson.fromJson(s1,new TypeToken<ArrayList<User>>(){}.getType());
        String orderid = req.getParameter("orderid");
        String op = req.getParameter("op");
        List<OrderItem> orderitem = os.checkorderitem(orderid);
        req.setAttribute("operate", op);
        req.setAttribute("orders", orderitem);
        req.getRequestDispatcher("/pages/order/orderitem.jsp").forward(req, resp);

    }

    public void showalluserorder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = os.showalluserorder();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    public void sendorderitem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderid = req.getParameter("orderid");

        os.sendOrderitem(orderid);

        resp.sendRedirect(req.getContextPath() + "/orderservlet?action=showalluserorder");


    }

    public void completeorder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderid = req.getParameter("orderid");
        os.completeorder(orderid);
        resp.sendRedirect(req.getContextPath() + "/orderservlet?action=checkmyorder");
    }
}
