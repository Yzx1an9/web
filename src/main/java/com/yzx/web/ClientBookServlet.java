package com.yzx.web;

import com.yzx.bean.Book;
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

public class ClientBookServlet extends BaseServlet {

    private BookService bs = new BookServiceimpl();

    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        String url = "client/bookservlet?action=page";
        Page<Book> page = bs.page(pageNo, pageSize, url);
        req.setAttribute("page", page);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/client/index.jsp");
        requestDispatcher.forward(req, resp);


    }

    public void pagebyprice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        String url = "client/bookservlet?action=pagebyprice";
        StringBuilder url1 = new StringBuilder();
        url1.append(url);
        if (req.getParameter("min") != null) {
            url1.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null) {
            url1.append("&max=").append(req.getParameter("max"));
        }
        Page<Book> page = bs.pagebyprice(pageNo, pageSize, min, max);
        page.setRequesturl(url1.toString());
        req.setAttribute("page", page);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/client/index.jsp");
        requestDispatcher.forward(req, resp);


    }

}
