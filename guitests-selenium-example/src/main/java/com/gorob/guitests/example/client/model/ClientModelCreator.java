package com.gorob.guitests.example.client.model;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ClientModelCreator {
    public static BookCM createEmptyBook(){
        return createBook("0", "", createUnknownGenre(), createUnknownAuthor());
    }

    public static GenreCM createEmptyGenre(){
        return createGenre("0","");
    }

    public static AuthorCM createEmptyAuthor(){
        return createAuthor("0", "", "");
    }

    public static BookCM createBook(String id, String title, GenreCM genre, AuthorCM... authors){
        BookCM book = new BookCM();
        book.setId(id);
        book.setTitle(title);
        book.setGenre(genre);
        book.setAuthors(Arrays.stream(authors).collect(Collectors.toList()));
        return book;
    }

    public static GenreCM createGenre(String id, String name){
        GenreCM genre = new GenreCM();
        genre.setId(id);
        genre.setName(name);
        return genre;
    }

    public static AuthorCM createAuthor(String id, String forename, String surename){
        AuthorCM author = new AuthorCM();
        author.setId(id);
        author.setForname(forename);
        author.setSurname(surename);
        return author;
    }

    public static AuthorCM createUnknownAuthor(){
        return createAuthor("99999", "", "<unknown>");
    }

    public static GenreCM createUnknownGenre() {
        return createGenre("99999", "<unknown>");
    }
}
