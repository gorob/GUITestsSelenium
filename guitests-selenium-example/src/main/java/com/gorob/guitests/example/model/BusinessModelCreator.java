package com.gorob.guitests.example.model;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BusinessModelCreator {
    public static BookBM createEmptyBook(){
        return createBook(0, "", createUnknownGenre(), createUnknownAuthor());
    }

    public static GenreBM createEmptyGenre(){
        return createGenre(0,"");
    }

    public static AuthorBM createEmptyAuthor(){
        return createAuthor(0, "", "");
    }

    public static BookBM createBook(int id, String title, GenreBM genre, AuthorBM... authors){
        BookBM book = new BookBM();
        book.setId(id);
        book.setTitle(title);
        book.setGenre(genre);
        book.setAuthors(Arrays.stream(authors).collect(Collectors.toList()));
        return book;
    }

    public static GenreBM createGenre(int id, String name){
        GenreBM genre = new GenreBM();
        genre.setId(id);
        genre.setName(name);
        return genre;
    }

    public static AuthorBM createAuthor(int id, String forename, String surename){
        AuthorBM author = new AuthorBM();
        author.setId(id);
        author.setForname(forename);
        author.setSurname(surename);
        return author;
    }

    public static AuthorBM createUnknownAuthor(){
        return createAuthor(99999, "", "<unknown>");
    }

    public static GenreBM createUnknownGenre() {
        return createGenre(99999,"<unknown>");
    }
}
