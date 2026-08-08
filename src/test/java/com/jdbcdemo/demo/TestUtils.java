package com.jdbcdemo.demo;

import com.jdbcdemo.demo.domain.Author;
import com.jdbcdemo.demo.domain.Book;

public class TestUtils {

    private TestUtils(){}

    public static Author createTestAuthorA(){
        return Author
                .builder()
                .id(1L)
                .name("Ahmed Shawky")
                .age(49)
                .build();
    }

    public static Author createTestAuthorB(){
        return Author
                .builder()
                .id(2L)
                .name("Omar Shawky")
                .age(23)
                .build();
    }
    public static Author createTestAuthorC(){
        return Author
                .builder()
                .id(3L)
                .name("Mostafa Shawky")
                .age(30)
                .build();
    }

    public static Book createTestBookA(){
        return Book.builder()
                .isbn("1234-56-78-1111")
                .title("Test Book A")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB(){
        return Book.builder()
                .isbn("1234-56-78-1112")
                .title("Test Book B")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookC(){
        return Book.builder()
                .isbn("1234-56-78-1113")
                .title("Test Book C")
                .authorId(1L)
                .build();
    }
}


