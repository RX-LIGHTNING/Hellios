package com.example.demo;

import java.sql.Date;

public class Log {
    public String user;
    public String action;
    public String table;
    public java.sql.Date date;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getResult() {
        return date.toString();
    }

    public void setResult(String result) {
        this.date = date;
    }

    public Log(String user, String action, String table, Date result) {
        this.user = user;
        this.action = action;
        this.table = table;
        this.date = result;
    }
}
