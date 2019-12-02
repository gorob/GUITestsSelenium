package com.gorob.guitests.example.client.model.mapper;

import com.gorob.guitests.example.client.model.AuthorCM;
import com.gorob.guitests.example.client.model.BookCM;
import com.gorob.guitests.example.client.model.GenreCM;
import com.gorob.guitests.example.model.AuthorBM;
import com.gorob.guitests.example.model.BookBM;
import com.gorob.guitests.example.model.GenreBM;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Business2ClientModelMapper {
    public List<BookCM> mapBooks(List<BookBM> booksBM) {
        return booksBM.stream().map(bookBM -> map(bookBM)).collect(Collectors.toList());
    }

    public BookCM map(BookBM bookBM) {
        BookCM bookCM = new BookCM();
        bookCM.setId("" + bookBM.getId());
        bookCM.setTitle(bookBM.getTitle());
        bookCM.setGenre(map(bookBM.getGenre()));
        bookCM.setAuthors(map(bookBM.getAuthors()));
        return bookCM;
    }

    public List<GenreCM> mapGenres(List<GenreBM> genresBM){
        return genresBM.stream().map(genreBM -> map(genreBM)).collect(Collectors.toList());
    }

    public GenreCM map(GenreBM genreBM) {
        GenreCM genreCM = new GenreCM();
        genreCM.setId("" + genreBM.getId());
        genreCM.setName(genreBM.getName());
        return genreCM;
    }

    private List<AuthorCM> map(List<AuthorBM> authorsBM) {
        return authorsBM.stream().map(authorBM -> map(authorBM)).collect(Collectors.toList());
    }

    private AuthorCM map(AuthorBM authorBM) {
        AuthorCM authorCM = new AuthorCM();
        authorCM.setId("" + authorBM.getId());
        authorCM.setForname(authorBM.getForname());
        authorCM.setSurname(authorBM.getSurname());
        return authorCM;
    }
}
