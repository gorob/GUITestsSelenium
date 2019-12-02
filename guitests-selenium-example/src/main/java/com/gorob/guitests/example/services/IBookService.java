package com.gorob.guitests.example.services;

import com.gorob.guitests.example.model.BookBM;
import com.gorob.guitests.example.model.GenreBM;

import java.util.List;

public interface IBookService {
    List<BookBM> getAllBooks();

    BookBM getBook(int bookId);

    void addBook(BookBM bookToAddBM);

    void deleteBook(int id);

    void saveBook(BookBM bookToSave);

    List<GenreBM> getAllGenres();

    GenreBM getGenre(int genreId);
}
