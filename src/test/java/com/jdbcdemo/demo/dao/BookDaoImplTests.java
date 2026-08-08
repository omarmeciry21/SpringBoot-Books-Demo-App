package com.jdbcdemo.demo.dao;

import com.jdbcdemo.demo.TestUtils;
import com.jdbcdemo.demo.domain.Author;
import com.jdbcdemo.demo.domain.Book;
import com.jdbcdemo.demo.domain.impl.AuthorDaoImpl;
import com.jdbcdemo.demo.domain.impl.BookDaoImpl;
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
public class BookDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private AuthorDaoImpl authorDao;
    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql(){

        Book book = TestUtils.createTestBookA();
        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq(book.getIsbn()),
                eq(book.getTitle()),
                eq(book.getAuthorId())
        );
    }

    @Test
    public void testThatUpdateBookCreatesCorrectSql(){
        Book book = TestUtils.createTestBookA();
        underTest.create( book);

        book.setTitle("New Book A Title");

        underTest.update(book.getIsbn(),book);

        verify(jdbcTemplate).update(eq("UPDATE books SET isbn=?, title=?, author_id=? WHERE isbn=?"),
                eq(book.getIsbn()),
                eq(book.getTitle()),
                eq(book.getAuthorId()),
                eq(book.getIsbn()));
    }

    @Test
    public void testThatDeleteBookCreatesCorrectSql(){

        Book book = TestUtils.createTestBookA();
        underTest.create( book);

        underTest.delete(book.getIsbn());

        verify(jdbcTemplate).update(eq("DELETE FROM books WHERE isbn=?"),eq(book.getIsbn()));
    }

    @Test
    public void testThatReadBookCreatesCorrectSql(){
        Book bookB = TestUtils.createTestBookB();
        underTest.create(bookB);

        Optional<Book> bookData = underTest.find(bookB.getIsbn());

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq(bookB.getIsbn())
        );
    }
}
