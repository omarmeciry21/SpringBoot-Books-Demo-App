package com.jdbcdemo.demo.domain;

public interface BookDao {
    public void create(Book book);
    public void update(String isbn,Book book);
}