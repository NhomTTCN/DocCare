package com.example.doccare.Model;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("jsonschema2pojo")
public class Insurance implements Serializable {

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("is_activate")
    @Expose
    private Boolean isActivate;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("expired_date")
    @Expose
    private String expiredDate;
    @SerializedName("created_date")
    @Expose
    private String createdDate;

    public Insurance(){
    }

    public Insurance(String createdAt, Boolean isActivate, String code, String name, String expiredDate, String createdDate) {
        this.createdAt = createdAt;
        this.isActivate = isActivate;
        this.code = code;
        this.name = name;
        this.expiredDate = expiredDate;
        this.createdDate = createdDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsActivate() {
        return isActivate;
    }

    public void setIsActivate(Boolean isActivate) {
        this.isActivate = isActivate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}