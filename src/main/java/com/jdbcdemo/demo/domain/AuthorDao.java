package com.jdbcdemo.demo.domain;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    public void create(Author author);
    public void update(Long authorId, Author author);
    public void delete(Long authorId);
    public Optional<Author> find(Long authorId);
    public List<Author> findAll();
}
