package com.jdbcdemo.demo.domain;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    public void create(Book book);
    public void update(String isbn,Book book);
    public void delete(String isbn);
    Optional<Book> find(String isbn);
    List<Book> findAll();
}