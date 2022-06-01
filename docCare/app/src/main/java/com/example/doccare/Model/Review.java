package com.example.doccare.Model;


import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Generated("jsonschema2pojo")
public class Review {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("user")
    @Expose
    private Reviewer user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Reviewer getUser() {
        return user;
    }

    public void setUser(Reviewer user) {
        this.user = user;
    }

    public Review(String id, Integer rate, String comment, Reviewer user) {
        this.id = id;
        this.rate = rate;
        this.comment = comment;
        this.user = user;
    }
}


