package com.yzx.dao;

import com.yzx.bean.Book;

import java.sql.Connection;
import java.util.List;

public interface BookDao {

    int add_book(Book book);

    int delete_book_byid(Integer id);

    int update_book(Book book);

    Book query_book_byid(Integer id);

    List<Book> query_all_books();

    int query_count();

    List<Book> query_part_book(int pageNo, int pageSize);

    int query_count_byprice(int min, int max);

    List<Book> query_part_book_byprice(int begin, int pageSize, int min, int max);
}
