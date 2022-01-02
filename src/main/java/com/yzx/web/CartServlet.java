package com.yzx.web;

import com.google.gson.Gson;
import com.yzx.bean.Book;
import com.yzx.bean.Cart;
import com.yzx.bean.Item;
import com.yzx.bean.Page;
import com.yzx.service.BookService;
import com.yzx.service.impl.BookServiceimpl;
import com.yzx.utils.JdbcUtils;
import com.yzx.utils.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CartServlet extends BaseServlet {
    private BookService bs = new BookServiceimpl();

    public void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String id = req.getParameter("bookid");
            int i = Integer.parseInt(id);
            Book book = bs.query_book_byid(i);
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            Item item = new Item(i, book.getName(), 1, book.getPrice(), book.getPrice());
            if (cart == null) {
                cart = new Cart();
                req.getSession().setAttribute("cart", cart);
            }
            cart.addItem(item);
        req.getSession().setAttribute("count", cart.getItemscount());
        req.getSession().setAttribute("name", book.getName());
        HashMap<String,Object> map = new HashMap<>();
        map.put("count",cart.getItemscount());
        map.put("name",item.getName());
        Gson gson = new Gson();
        resp.getWriter().write(gson.toJson(map));

    }

//    public void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("bookid");
//        int i = Integer.parseInt(id);
//        Book book = bs.query_book_byid(i);
//        Cart cart = (Cart) req.getSession().getAttribute("cart");
//        if (cart == null) {
//            cart = new Cart();
//            req.getSession().setAttribute("cart", cart);
//        }
//        cart.addItem(new Item(i, book.getName(), 1, book.getPrice(), book.getPrice()));
////            req.getSession().setAttribute("count", cart.getItemscount());
////            req.getSession().setAttribute("name", book.getName());
////            resp.sendRedirect(req.getHeader("Referer"));
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("count",cart.getItemscount());
//        map.put("name",book.getName());
//        resp.getWriter().write(new Gson().toJson(map));
//    }



    public void deleteitem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String id = req.getParameter("bookid");
            int i = Integer.parseInt(id);
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            cart.deleteItem(i);
            resp.sendRedirect(req.getHeader("Referer"));
    }

    public void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            cart.clearcart();
            resp.sendRedirect(req.getHeader("Referer"));
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String id = req.getParameter("id");
            int i = WebUtils.parseInt(id, 0);
            String count = req.getParameter("count");
            int i1 = WebUtils.parseInt(count, 0);

            Cart cart = (Cart) req.getSession().getAttribute("cart");
            cart.updatecart(i, i1);
            resp.sendRedirect(req.getHeader("Referer"));


    }

//    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        Connection conn = null;
//        try {
//            conn = JdbcUtils.GetConnection();
//            conn.setAutoCommit(false);
//            int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
//            int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
//            String url = "cartservlet?action=page";
//            Page<Item> page = new Page<>();
//            page.setPageNo(pageNo);
//            page.setRequesturl(url);
//            Cart cart = (Cart) req.getSession().getAttribute("cart");
//            List<Item> values = (List<Item>) cart.getItems().values();
//            page.setPageItems(values);
//            req.setAttribute("page",page);
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/cart/cart.jsp");
//            requestDispatcher.forward(req,resp);
//            conn.commit();
//        } catch (SQLException throwables) {
//            if(conn != null){
//                try {
//                    conn.rollback();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            throwables.printStackTrace();
//        }finally {
//            JdbcUtils.Close(null,null);
//        }
//
//    }

}
