package com.jdbcdemo.demo.dao;

import com.jdbcdemo.demo.TestUtils;
import com.jdbcdemo.demo.domain.Author;
import com.jdbcdemo.demo.domain.impl.AuthorDaoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDaoImplIntegrationTests {

    private final AuthorDaoImpl underTest;

    @Autowired
    AuthorDaoImplIntegrationTests(final AuthorDaoImpl authorDao){
        underTest = authorDao;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestUtils.createTestAuthorA();
        underTest.create(author);
        Optional<Author> result = underTest.find(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }
}
