package com.example.demo;

public class Order {
    public String Photograph;
    public String Status;

    public String getPhotograph() {
        return Photograph;
    }

    public String getStatus() {
        return Status;
    }

    public Order(String photograph, boolean status) {
        this.Photograph = photograph;
        this.Status = status ? "Done." : "In progress.";
//        if(status) this.Status = "Done.";
//        else this.Status = "In progress.";
    }
}
