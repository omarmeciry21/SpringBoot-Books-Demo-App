package com.jdbcdemo.demo.domain;

public interface AuthorDao {
    public void create(Author author);
    public void update(Long authorId, Author author);
    public void delete(Long authorId);
}
