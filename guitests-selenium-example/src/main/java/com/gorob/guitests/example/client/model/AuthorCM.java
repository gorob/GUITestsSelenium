package com.gorob.guitests.example.client.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AuthorCM extends AbstractClientModel {
    @NotNull
    @NotEmpty
    private String forname;

    @NotNull
    @NotEmpty
    private String surname;

    public String getName(){
        return getForname() + " " + getSurname();
    }

    @Override
    public String toString(){
        return getName();
    }
}
