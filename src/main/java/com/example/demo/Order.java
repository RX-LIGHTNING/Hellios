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
        if(status==true) this.Status = "Done.";
        else if(status==false) this.Status = "In progress.";
    }
}
