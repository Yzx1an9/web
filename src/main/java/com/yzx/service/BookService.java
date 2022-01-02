package com.yzx.service;

import com.yzx.bean.Book;
import com.yzx.bean.Page;

import java.sql.Connection;
import java.util.List;

public interface BookService {

    int add_book( Book book);

    int delete_book_byid(Integer id);

    int update_book(Book book);

    Book query_book_byid(Integer id);

    List<Book> query_all_books();

    Page<Book> page( int pageNo, int pageSize, String url);

    Page<Book> pagebyprice( int pageNo, int pageSize, int min, int max);
}
