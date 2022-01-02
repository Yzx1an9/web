package com.yzx.web;


import com.yzx.bean.Book;
import com.yzx.bean.Page;
import com.yzx.service.BookService;
import com.yzx.service.impl.BookServiceimpl;
import com.yzx.utils.BeanUtils;
import com.yzx.utils.JdbcUtils;
import com.yzx.utils.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BookServlet extends BaseServlet {
    BookService bs = new BookServiceimpl();

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bs.query_all_books();
        req.setAttribute("books", books);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/manager/book_manager.jsp");
        requestDispatcher.forward(req, resp);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = BeanUtils.ParamsToBean(req.getParameterMap(), new Book());
        String newpageno = req.getParameter("newpageno");
        int i = WebUtils.parseInt(newpageno, 1) + 1;
        bs.add_book(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookservlet?action=page&pageNo=" + i);


    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
        bs.delete_book_byid(i);
        resp.sendRedirect(req.getContextPath() + "/manager/bookservlet?action=page&pageNo=" + req.getParameter("newpageno"));

    }

    public void writeback(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
        Book book = bs.query_book_byid(i);
        req.setAttribute("book", book);
        RequestDispatcher newpageno = req.getRequestDispatcher("/pages/manager/book_edit.jsp");
        newpageno.forward(req, resp);


    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = BeanUtils.ParamsToBean(req.getParameterMap(), new Book());
        bs.update_book(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookservlet?action=page&pageNo=" + req.getParameter("newpageno"));


    }

    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        String url = "manager/bookservlet?action=page";
        Page<Book> page = bs.page(pageNo, pageSize, url);
        req.setAttribute("page", page);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/manager/book_manager.jsp");
        requestDispatcher.forward(req, resp);


    }


}
