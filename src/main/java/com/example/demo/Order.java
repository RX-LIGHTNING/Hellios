package com.example.demo;

import java.sql.Date;

public class Order {
    public String Photograph;
    public String Status;
    public int Id;
    public int UserId;
    public String username;
    public String usercontact;
    public Date orderdate;



    public String getPhotograph() {
        return Photograph;
    }

    public void setPhotograph(String photograph) {
        Photograph = photograph;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercontact() {
        return usercontact;
    }

    public void setUsercontact(String usercontact) {
        this.usercontact = usercontact;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public int getId() {
        return Id;
    }

    public Order(int id,int userId, String photograph_name, int status, String user_name, String user_contact, Date order_date) {
        this.Photograph = photograph_name;
        this.Status = (status == 2) ? "Done." :
                      (status == 1) ? "In progress." :
                      (status == 0) ? "Verifying." :
                      (status == -1)?"Canceled" :
                      "undefined"; //(status == -1)?"Canceled" :
        this.UserId = userId;
        this.username=user_name;
        this.usercontact = user_contact;
        this.orderdate = order_date;
        this.Id = id;
    }
}
