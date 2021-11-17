package com.example.demo;

public class Log {
    public String user;
    public String action;
    public String table;
    public String result;

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
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Log(String user, String action, String table, String result) {
        this.user = user;
        this.action = action;
        this.table = table;
        this.result = result;
    }
}
