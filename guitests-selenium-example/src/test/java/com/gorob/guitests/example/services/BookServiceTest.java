package com.gorob.guitests.example.services;

import com.gorob.guitests.example.services.impl.BookService;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService createUnderTest(){
        return new BookService();
    }

    @Test
    public void getAllBooks() {
        BookService bookService = createUnderTest();
        assertEquals(3, bookService.getAllBooks().size());
    }

    @Test
    public void deleteBook() {
        BookService bookService = createUnderTest();

        assertEquals(3, bookService.getAllBooks().size());

        bookService.deleteBook(1);
        assertEquals(2, bookService.getAllBooks().size());

        bookService.deleteBook(1);
        assertEquals(2, bookService.getAllBooks().size());

        bookService.deleteBook(2);
        assertEquals(1, bookService.getAllBooks().size());

        bookService.deleteBook(2);
        assertEquals(1, bookService.getAllBooks().size());

        bookService.deleteBook(3);
        assertEquals(0, bookService.getAllBooks().size());

        bookService.deleteBook(3);
        assertEquals(0, bookService.getAllBooks().size());
    }
}