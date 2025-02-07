package com.example;
public class User extends Hotel {
    private String login;
    private int password_hash;


    public User(String login, String password){
        this.login = login;
        this.password_hash = password.hashCode();
    }

    public String get_login(){
        return login;
    }
    public int get_password_hash(){
        return password_hash;
    }
}