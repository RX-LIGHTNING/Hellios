package com.example.demo;

public class Photograph {
    private String photograph;
    private String description;
    Photograph(String photograph, String description){
        this.photograph = photograph;
        this.description = description;
    }
    public String getPhotograph(){
        return photograph;
    }
    public String getDescription(){
        return description;
    }
}
