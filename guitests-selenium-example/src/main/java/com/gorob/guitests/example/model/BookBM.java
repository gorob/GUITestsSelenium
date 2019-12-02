package com.gorob.guitests.example.model;

import lombok.Data;

import java.util.List;

@Data
public class BookBM extends AbstractBusinessModel {
    private String title;
    private GenreBM genre;
    private List<AuthorBM> authors;
}
