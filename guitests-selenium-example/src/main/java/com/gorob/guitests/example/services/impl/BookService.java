package com.gorob.guitests.example.services.impl;

import com.gorob.guitests.example.database.BusinessModelRepository;
import com.gorob.guitests.example.model.BookBM;
import com.gorob.guitests.example.model.GenreBM;
import com.gorob.guitests.example.services.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Override
    public List<BookBM> getAllBooks() {
        return BusinessModelRepository.getBooks();
    }

    @Override
    public BookBM getBook(int bookId) {
        return BusinessModelRepository.getBookById(bookId);
    }

    @Override
    public void addBook(BookBM bookToAddBM) {
        BusinessModelRepository.getBooks().add(bookToAddBM);
    }

    @Override
    public void deleteBook(int id) {
        BookBM book = BusinessModelRepository.getBookById(id);
        if (book==null) { return; }
        BusinessModelRepository.getBooks().remove(BusinessModelRepository.getBookById(id));
    }

    @Override
    public void saveBook(BookBM bookToSave) {
        deleteBook(bookToSave.getId());
        addBook(bookToSave);
    }

    @Override
    public List<GenreBM> getAllGenres() {
        return BusinessModelRepository.getGenres();
    }

    @Override
    public GenreBM getGenre(int genreId) {
        return BusinessModelRepository.getGenreById(genreId);
    }
}
