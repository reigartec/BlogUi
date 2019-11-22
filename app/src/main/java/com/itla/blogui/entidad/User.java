package com.itla.blogui.entidad;

import java.util.Arrays;

public class User {

    /**    "createdAt": 1569696058866,
     "email": "elnovato@itla.com",
     "id": 69,
     "name": "RG",
     "posts": 6,
     "token": {
     "createdAt": 0,
     "token": "a09583c3-1203-4a89-92b9-ee695f48ff84",
     "userId": 0
     }*/

    private Long createdAt;
    public String name,email;
    public int id;
    public int posts;

    public User() {
        this.createdAt = createdAt;
        this.name = name;
        this.email = email;
        this.id = id;
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", posts=" + posts +
                '}';
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }

}
