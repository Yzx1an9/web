package com.yzx.service.impl;

import com.yzx.bean.Book;
import com.yzx.bean.Page;
import com.yzx.dao.BookDao;
import com.yzx.dao.impl.BookDaoimpl;
import com.yzx.service.BookService;

import java.sql.Connection;
import java.util.List;

public class BookServiceimpl implements BookService {
    private BookDao bd = new BookDaoimpl();
    @Override
    public int add_book(Book book) {
        return bd.add_book(book);
    }

    @Override
    public int delete_book_byid(Integer id) {
        return bd.delete_book_byid(id);
    }

    @Override
    public int update_book( Book book) {
        return bd.update_book(book);
    }

    @Override
    public Book query_book_byid( Integer id) {
        return bd.query_book_byid(id);
    }

    @Override
    public Page<Book> page( int pageNo, int pageSize, String url) {
        Page<Book> bookPage = new Page<>();
        bookPage.setRequesturl(url);
        int pageTotalCount = bd.query_count();
        bookPage.setPageTotalCount(pageTotalCount);
        int pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        bookPage.setPageTotal(pageTotal);
        bookPage.setPageNo(pageNo);
        int begin = (bookPage.getPageNo() - 1) * pageSize;
        List<Book> books = bd.query_part_book(begin,pageSize);
        bookPage.setPageItems(books);
        return bookPage;
    }

    @Override
    public Page<Book> pagebyprice( int pageNo, int pageSize, int min, int max) {
        Page<Book> bookPage = new Page<>();

        int pageTotalCount = bd.query_count_byprice(min,max);
        bookPage.setPageTotalCount(pageTotalCount);
        int pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        bookPage.setPageTotal(pageTotal);
        bookPage.setPageNo(pageNo);
        int begin = (bookPage.getPageNo() - 1) * pageSize;
        List<Book> books = bd.query_part_book_byprice(begin,pageSize,min,max);
        bookPage.setPageItems(books);
        return bookPage;
    }

    @Override
    public List<Book> query_all_books() {
        return bd.query_all_books();
    }
}
