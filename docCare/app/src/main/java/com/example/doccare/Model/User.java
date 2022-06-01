package com.example.doccare.Model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("jsonschema2pojo")
public class User implements Serializable {

    @SerializedName("account")
    @Expose
    private Account account;
    @SerializedName("insurance")
    @Expose
    private Insurance insurance;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("is_activate")
    @Expose
    private Boolean isActivate;
    @SerializedName("name")
    @Expose
    private String Name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("birthday")
    @Expose
    private Object birthday;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("career")
    @Expose
    private String career;
    @SerializedName("username")
    @Expose
    private String username;

    public Boolean getActivate() {
        return isActivate;
    }

    public void setActivate(Boolean activate) {
        isActivate = activate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    @SerializedName("password")
    @Expose
    private String password;

    public User(){
    }

    public User(Account account, Insurance insurance, String createdAt, Boolean isActivate, String name, String phone, Object birthday, String gender, String career, String username, String password) {
        this.account = account;
        this.insurance = insurance;
        this.createdAt = createdAt;
        this.isActivate = isActivate;
        Name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.career = career;
        this.username = username;
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
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


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

}