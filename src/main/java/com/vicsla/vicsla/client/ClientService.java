package com.vicsla.vicsla.client;


import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ClientService {


    @PostConstruct
    private void test(){
        System.out.println("post constructor");
    }
    private String text = "old";

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

}
