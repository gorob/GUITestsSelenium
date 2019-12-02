package com.gorob.guitests.example.database;

import com.gorob.guitests.example.model.AuthorBM;
import com.gorob.guitests.example.model.BookBM;
import com.gorob.guitests.example.model.GenreBM;
import com.gorob.guitests.example.model.BusinessModelCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
In this guitests example wie don't use a database implementation, so we create and cache the models
in a global BusinessModelRepository class (no use of e.g. database repositories and mapping between
business models and database table models)
 */
public class BusinessModelRepository {
    private static List<BookBM> books;
    private static List<GenreBM> genres;
    private static List<AuthorBM> authors;

    public static List<BookBM> getBooks(){
        if (books==null){
            books = createInitialBooks();
        }

        return books;
    }

    public static List<GenreBM> getGenres(){
        if (genres==null){
            genres = createInitialGenres();
        }

        return genres;
    }

    public static List<AuthorBM> getAuthors(){
        if (authors==null){
            authors = createInitialAuthors();
        }

        return authors;
    }

    private static List<BookBM> createInitialBooks(){
        List<BookBM> books = new ArrayList<>();
        books.add(BusinessModelCreator.createBook(1, "Getting Things Done: the art of stress-free productivity", getGenreById(1), getAuthorById(1)));
        books.add(BusinessModelCreator.createBook(2, "Hitchhiker's Guide to the Galaxy", getGenreById(2), getAuthorById(2)));
        books.add(BusinessModelCreator.createBook(3, "Magic Cleaning", getGenreById(1), getAuthorById(3)));
        return books;
    }

    private static List<GenreBM> createInitialGenres(){
        List<GenreBM> genres = new ArrayList<>();
        genres.add(BusinessModelCreator.createGenre(1, "Guide"));
        genres.add(BusinessModelCreator.createGenre(2, "Fantasy"));
        return genres;
    }

    private static List<AuthorBM> createInitialAuthors(){
        List<AuthorBM> authors = new ArrayList<AuthorBM>();
        authors.add(BusinessModelCreator.createAuthor(1, "David", "Allen"));
        authors.add(BusinessModelCreator.createAuthor(2, "Douglas", "Adams"));
        authors.add(BusinessModelCreator.createAuthor(3, "Marie", "Kondo"));
        return authors;
    }

    public static BookBM getBookById(int id){
        List<BookBM> list = getBooks().stream().filter(book -> book.getId()==id).collect(Collectors.toList());
        return list.isEmpty() ? null : list.get(0);
    }

    public static GenreBM getGenreById(int id){
        List<GenreBM> list = getGenres().stream().filter(genre -> genre.getId()==id).collect(Collectors.toList());
        return list.isEmpty() ? null : list.get(0);
    }

    public static AuthorBM getAuthorById(int id){
        List<AuthorBM> list = getAuthors().stream().filter(author -> author.getId()==id).collect(Collectors.toList());
        return list.isEmpty() ? null : list.get(0);
    }
}
