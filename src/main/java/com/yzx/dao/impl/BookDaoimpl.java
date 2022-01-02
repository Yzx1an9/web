package com.yzx.dao.impl;

import com.yzx.bean.Book;
import com.yzx.dao.Basedao;
import com.yzx.dao.BookDao;

import java.sql.Connection;
import java.util.List;

public class BookDaoimpl extends Basedao implements BookDao {
    @Override
    public int add_book(Book book) {
        String sql = "insert into t_book(name,author,price,sales,stock,img_path)values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
    public int delete_book_byid(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql,id);
    }

    @Override
    public int update_book(Book book) {
        String sql = "update t_book set name = ?,author = ?,price = ?,sales = ?,stock = ?,img_path = ? where id = ?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }

    @Override
    public Book query_book_byid(Integer id) {
        String sql = "select * from t_book where id = ?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> query_all_books() {
        String sql = "select * from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public int query_count() {
        String sql = "select count(*) from t_book";
        return ((Long) queryForSingleValue(sql)).intValue();


    }

    @Override
    public List<Book> query_part_book(int pageNo, int pageSize) {
        String sql = "select * from t_book limit ?,?";
        return queryForList(Book.class,sql,pageNo,pageSize);
    }


    @Override
    public int query_count_byprice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        return ((Long) queryForSingleValue(sql, min, max)).intValue();
    }

    @Override
    public List<Book> query_part_book_byprice(int begin, int pageSize, int min, int max) {
        String sql = "select * from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }
}
