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
public class Client2BusinessModelMapper {
    public BookBM map(BookCM bookCM) {
        BookBM bookBM = new BookBM();
        bookBM.setId(Integer.valueOf(bookCM.getId()));
        bookBM.setTitle(bookCM.getTitle());
        bookBM.setGenre(map(bookCM.getGenre()));
        bookBM.setAuthors(map(bookCM.getAuthors()));
        return bookBM;
    }

    private GenreBM map(GenreCM genreCM) {
        GenreBM genreBM = new GenreBM();
        genreBM.setId(Integer.valueOf(genreCM.getId()));
        genreBM.setName(genreCM.getName());
        return genreBM;
    }

    private List<AuthorBM> map(List<AuthorCM> authors) {
        return authors.stream().map(authorCM -> map(authorCM)).collect(Collectors.toList());
    }

    private AuthorBM map(AuthorCM authorCM) {
        AuthorBM authorBM = new AuthorBM();
        authorBM.setId(Integer.valueOf(authorCM.getId()));
        authorBM.setForname(authorCM.getForname());
        authorBM.setSurname(authorCM.getSurname());
        return authorBM;
    }
}
