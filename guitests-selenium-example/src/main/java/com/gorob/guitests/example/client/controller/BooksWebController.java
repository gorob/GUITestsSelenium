package com.gorob.guitests.example.client.controller;

import com.gorob.guitests.example.client.model.AuthorCM;
import com.gorob.guitests.example.client.model.BookCM;
import com.gorob.guitests.example.client.model.ClientModelCreator;
import com.gorob.guitests.example.client.model.GenreCM;
import com.gorob.guitests.example.model.BookBM;
import com.gorob.guitests.example.model.BusinessModelCreator;
import com.gorob.guitests.example.services.IBookService;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BooksWebController extends AbstractController {
    static final String REDIRECT = "redirect:";

    static final String VIEW_NAME_BOOKS = "books/books_view";
    static final String VIEW_NAME_NEW_BOOK = "books/book_insert";
    static final String VIEW_NAME_EDIT_BOOK = "books/book_update";

    static final String BASE_URL = "/books";
    private static final String SUB_URL_ADD = "/add";
    private static final String SUB_URL_EDIT = "/edit";
    private static final String SUB_URL_DELETE = "/delete";

    static final String MODEL_KEY_BOOKS = "books";
    static final String MODEL_KEY_BOOK_TO_EDIT = "bookToEdit";
    static final String MODEL_KEY_GENRES = "genres";

    @Getter(AccessLevel.PRIVATE)
    private IBookService bookService;

    public BooksWebController(IBookService bookService){
        this.bookService = bookService;
    }

    @GetMapping(BASE_URL)
    public String getBooks(Model model){
        model.addAttribute(MODEL_KEY_BOOKS, readAndMapBooks());
        return VIEW_NAME_BOOKS;
    }

    @GetMapping(BASE_URL + SUB_URL_ADD)
    public String getBookToInsert(Model model){
        model.addAttribute(MODEL_KEY_BOOK_TO_EDIT, ClientModelCreator.createEmptyBook());
        model.addAttribute(MODEL_KEY_GENRES, readAndMapGenres());
        return VIEW_NAME_NEW_BOOK;
    }

    @PostMapping(BASE_URL + SUB_URL_ADD)
    public String insertBook(@ModelAttribute(MODEL_KEY_BOOK_TO_EDIT) @Valid BookCM newBookCM, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return VIEW_NAME_NEW_BOOK;
        }

        newBookCM.setGenre(readAndMapGenre(newBookCM.getGenre().getId()));

        List<AuthorCM> authors = new ArrayList<>();
        authors.add(ClientModelCreator.createUnknownAuthor());
        newBookCM.setAuthors(authors);

        getBookService().addBook(mapBook(newBookCM));

        return REDIRECT + BASE_URL;
    }

    @GetMapping(BASE_URL + SUB_URL_EDIT + "/{id}")
    public String getBookToUpdate(@PathVariable("id") String id, Model model){
        model.addAttribute(MODEL_KEY_BOOK_TO_EDIT, readAndMapBook(id));
        model.addAttribute(MODEL_KEY_GENRES, readAndMapGenres());
        return VIEW_NAME_EDIT_BOOK;
    }

    @PostMapping(BASE_URL + SUB_URL_EDIT)
    public String updateBook(@ModelAttribute(MODEL_KEY_BOOK_TO_EDIT) @Valid BookCM bookToEditCM, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return VIEW_NAME_EDIT_BOOK;
        }

        // read genre for selected id in combobox on form
        bookToEditCM.setGenre(readAndMapGenre(bookToEditCM.getGenre().getId()));

        // preserve authors before save
        BookCM oldBookCM = readAndMapBook(bookToEditCM.getId());
        bookToEditCM.setAuthors(oldBookCM.getAuthors());

        getBookService().saveBook(mapBook(bookToEditCM));

        return REDIRECT + BASE_URL;
    }

    @PostMapping(BASE_URL + SUB_URL_DELETE + "/{id}")
    public String deleteBook(@PathVariable("id") String id){
        getBookService().deleteBook(mapBookId(id));

        return REDIRECT + BASE_URL;
    }

    private List<BookCM> readAndMapBooks(){
        return getBm2cmMapper().mapBooks(getBookService().getAllBooks());
    }

    private BookCM readAndMapBook(String bookId){
        return getBm2cmMapper().map(getBookService().getBook(mapBookId(bookId)));
    }

    private BookBM mapBook(BookCM bookCM){
        return getCm2bmMapper().map(bookCM);
    }

    private int mapBookId(String bookId) {
        return Integer.parseInt(bookId);
    }

    private List<GenreCM> readAndMapGenres(){
        return getBm2cmMapper().mapGenres(getBookService().getAllGenres());
    }

    private GenreCM readAndMapGenre(String genreId){
        return getBm2cmMapper().map(getBookService().getGenre(mapGenreId(genreId)));
    }

    private int mapGenreId(String genreId) {
        return Integer.parseInt(genreId);
    }

}
