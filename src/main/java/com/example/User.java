package com.example;
public class User extends Hotel {
    private final int user_id;
    private String user_login;
    private String user_password;


    public User(String login, String password, int id){
        this.user_id = id;
        this.user_login = login;
        this.user_password = password;
    }


    public int getUser_id() {
        return user_id;
    }


    public String getUser_login() {
        return user_login;
    }


    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }


    public String getUser_password() {
        return user_password;
    }


    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }


    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", user_login=" + user_login + ", user_password=" + user_password + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + user_id;
        result = prime * result + ((user_login == null) ? 0 : user_login.hashCode());
        result = prime * result + ((user_password == null) ? 0 : user_password.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (user_id != other.user_id)
            return false;
        if (user_login == null) {
            if (other.user_login != null)
                return false;
        } else if (!user_login.equals(other.user_login))
            return false;
        if (user_password == null) {
            if (other.user_password != null)
                return false;
        } else if (!user_password.equals(other.user_password))
            return false;
        return true;
    }

    

    
}