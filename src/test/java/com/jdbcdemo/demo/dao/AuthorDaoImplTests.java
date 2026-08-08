package com.jdbcdemo.demo.dao;

import com.jdbcdemo.demo.TestUtils;
import com.jdbcdemo.demo.domain.Author;
import com.jdbcdemo.demo.domain.impl.AuthorDaoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Author author = TestUtils.createTestAuthorA();

        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (? , ? , ?)"),
                eq(author.getId()),
                eq(author.getName()),
                eq(author.getAge())
        );
    }

    @Test
    public void testThatUpdateAuthorGeneratesCorrectSql(){
        Author author = TestUtils.createTestAuthorA();
        underTest.create(author);

        author.setName("Omar Elmesiry");

        underTest.update(author.getId(), author);

        verify(jdbcTemplate).update(eq("UPDATE authors SET id=?, name=?, age=? WHERE id=?"),
                eq(author.getId()),
                eq(author.getName()),
                eq(author.getAge()),
                eq(author.getId()));
    }

    @Test
    public void testThatDeleteAuthorCreatesCorrectSql(){
        Author author = TestUtils.createTestAuthorA();
        underTest.create(author);

        underTest.delete(author.getId());

        verify(jdbcTemplate).update(eq("DELETE FROM authors WHERE id=?"), eq(author.getId()));
    }

    @Test
    public void testThatReadOneAuthorCreatesCorrectSql(){
        Author authorA = TestUtils.createTestAuthorA();
        Author authorB = TestUtils.createTestAuthorB();
        Author authorC = TestUtils.createTestAuthorC();

        underTest.create(authorA);
        underTest.create(authorB);
        underTest.create(authorC);

        Optional<Author> authorData = underTest.find(authorB.getId());

        verify(jdbcTemplate).query(eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(authorB.getId()));

    }

    @Test
    public void testThatFindManyAuthorsCreatesCorrectSql(){
        underTest.findAll();
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }
}
