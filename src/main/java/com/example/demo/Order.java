package com.example.demo;

public class Order {

    public String Photograph;
    public String Status;
    public int Id;
    public int UserId;
    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }



    public void setPhotograph(String photograph) {
        Photograph = photograph;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPhotograph() {
        return Photograph;
    }

    public String getStatus() {
        return Status;
    }

    public int getId() {
        return Id;
    }

    public Order(String photograph, boolean status) {
        this.Photograph = photograph;
        this.Status = status ? "Done." : "In progress.";
    }
    public Order(String photograph, boolean status,int UserId,int Id) {
        this.Photograph = photograph;
        this.Status = status ? "Done." : "In progress.";
        this.UserId = UserId;
        this.Id = Id;
    }
}
