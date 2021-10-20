package com.example.demo;

public class SelectedUser {
    public String Login;
    public Integer id;
    public String Contact;

    public String getContact() {
        return Contact;
    }
    public String getLogin() {
        return Login;
    }
    public boolean getStatus(){
        return DatabaseController.getUserStatus(getLogin());
    }

    SelectedUser(String Login, String Contacts){
        this.Login = Login;
        this.Contact = Contacts;
    }
}
