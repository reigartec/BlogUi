package com.itla.blogui.entidad;

import java.io.Serializable;

public class PostCommentList implements Serializable {

    /*        "body": "Great!",
        "createdAt": 1569028057906,
        "id": 6,
        "postId": 1,
        "userEmail": "ldilone@blog.com",
        "userId": 32,
        "userName": "Lewandy Dilone"*/

    @Override
    public String toString() {
        return "PostCommentList{" +
                "body='" + body + '\'' +
                ", createdAt=" + createdAt +
                ", id=" + id +
                ", postid=" + postid +
                ", userEmail='" + userEmail + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }

    private String body;
    private Long createdAt;
    private int id;
    private int postid;
    private String userEmail;
    private int userId;
    private String userName;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getCreatedAt() {
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

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
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

    public PostCommentList(String body, Long createdAt, int id, int postid, String userEmail, int userId, String userName) {
        this.body = body;
        this.createdAt = createdAt;
        this.id = id;
        this.postid = postid;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userName = userName;
    }
}
