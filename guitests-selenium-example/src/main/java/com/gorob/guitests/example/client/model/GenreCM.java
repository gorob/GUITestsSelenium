package com.gorob.guitests.example.client.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class GenreCM extends AbstractClientModel {
    @NotNull
    @NotEmpty
    private String name;

    @Override
    public String toString(){
        return getName();
    }
}
