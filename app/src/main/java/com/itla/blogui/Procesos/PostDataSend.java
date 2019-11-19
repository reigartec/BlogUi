package com.itla.blogui.Procesos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class PostDataSend {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("tags")
    @Expose
    private String tags[];

    @Override
    public String toString() {
        return "PostDataSend{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }



}
