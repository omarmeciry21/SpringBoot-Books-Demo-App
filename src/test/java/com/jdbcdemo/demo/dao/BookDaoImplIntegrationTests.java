package com.jdbcdemo.demo.dao;

import com.jdbcdemo.demo.TestUtils;
import com.jdbcdemo.demo.domain.Author;
import com.jdbcdemo.demo.domain.Book;
import com.jdbcdemo.demo.domain.impl.AuthorDaoImpl;
import com.jdbcdemo.demo.domain.impl.BookDaoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoImplIntegrationTests {

    BookDaoImpl underTest;
    AuthorDaoImpl authorDao;

    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl underTest, AuthorDaoImpl authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){

        Author author = TestUtils.createTestAuthorA();
        authorDao.create(author);

        Book book = TestUtils.createTestBookA();
        underTest.create(book);

        Optional<Book> result = underTest.find(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }
}
