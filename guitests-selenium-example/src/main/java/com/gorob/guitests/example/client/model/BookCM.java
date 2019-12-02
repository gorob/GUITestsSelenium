package com.gorob.guitests.example.client.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookCM extends AbstractClientModel implements Serializable {
    @NotNull
    @NotEmpty
    private String title;

    private GenreCM genre;

    private List<AuthorCM> authors;

    public String getAuthorsAsCommaseparatedList(){
        return String.join(",", getAuthors().stream().map(author -> author.getName()).collect(Collectors.toList())).trim();
    }

    public String getGenreName(){
        return getGenre().getName();
    }

    public void setGenreName(String genreName){
        getGenre().setName(genreName);
    }
}
