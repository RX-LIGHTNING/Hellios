package com.example.demo;

public class Photograph {
    private String photograph;
    private String description;
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    Photograph(String photograph, String description,int id){
        this.photograph = photograph;
        this.description = description;
        this.id = id;
    }
    public String getPhotograph(){
        return photograph;
    }
    public String getDescription(){
        return description;
    }
}
