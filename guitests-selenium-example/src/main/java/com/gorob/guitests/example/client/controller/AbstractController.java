package com.gorob.guitests.example.client.controller;

import com.gorob.guitests.example.client.model.mapper.Business2ClientModelMapper;
import com.gorob.guitests.example.client.model.mapper.Client2BusinessModelMapper;
import lombok.Getter;

@Getter
public abstract class AbstractController {
    private Client2BusinessModelMapper cm2bmMapper;
    private Business2ClientModelMapper bm2cmMapper;

    public AbstractController(){
        this.cm2bmMapper = new Client2BusinessModelMapper();
        this.bm2cmMapper = new Business2ClientModelMapper();
    }
}
