package com.itla.blogui.entidad;

import java.util.Date;

public class Users {
        /*    "createdAt": 1569023623192,
    "email": "lmpj@lmpj.com",
    "id": 25,
    "name": "Luisa Peralta",
    "posts": 2*/



    public int id;
    public String name,email,token;
    public int posts;
//    public Date createdAt;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", posts=" + posts +
                '}';
    }
}
