package com.itla.blogui.entidad;

import java.io.Serializable;
import java.util.Arrays;

public class Postui implements Serializable {
    private String body;
    private int comments;
    private Long createdAt;
    private int id;
    private boolean liked;
    private int likes;
    private String tags[];
    private String title;
    private String userEmail;
    private int userId;
    private String userName;
    private int views;

    public Postui(String body, int comments, Long createdAt, int id, boolean liked, int likes, String[] tags, String title, String userEmail, int userId, String userName, int views) {
        this.body = body;
        this.comments = comments;
        this.createdAt = createdAt;
        this.id = id;
        this.liked = liked;
        this.likes = likes;
        this.tags = tags;
        this.title = title;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userName = userName;
        this.views = views;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "Post{" +
                "body='" + body + '\'' +
                ", comments=" + comments +
                ", createdAt=" + createdAt +
                ", id=" + id +
                ", liked=" + liked +
                ", likes=" + likes +
                ", tags=" + Arrays.toString(tags) +
                ", title='" + title + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", views=" + views +
                '}';
    }

    public Postui(){

    }
}
