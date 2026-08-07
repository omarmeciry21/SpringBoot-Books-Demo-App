package com.jdbcdemo.demo.domain.impl;

import com.jdbcdemo.demo.domain.Author;
import com.jdbcdemo.demo.domain.AuthorDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;
    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update("INSERT INTO authors (id, name, age) VALUES (? , ? , ?)",
                author.getId(),
                author.getName(),
                author.getAge()
                );
    }
}
