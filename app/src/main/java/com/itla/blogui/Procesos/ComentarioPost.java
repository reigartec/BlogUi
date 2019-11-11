package com.itla.blogui.Procesos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComentarioPost {

    @SerializedName("body")
    @Expose
    private String body;


    @Override
    public String toString() {
        return "ComentarioPost{" +
                "body='" + body + '\'' +
                '}';
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
