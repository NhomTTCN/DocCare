package com.example.doccare.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Account implements Serializable {

    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("is_activate")
    @Expose
    private Boolean isActivate = true;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;
    @SerializedName("role")
    @Expose
    private Object role;

    public Account(){
    }

    public Account(String lastLogin, String createdAt, Boolean isActivate, String username, String email, String password, String avatar, Boolean isActive, Object role) {
        this.lastLogin = lastLogin;
        this.createdAt = createdAt;
        this.isActivate = true;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.isActive = isActive;
        this.role = role;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

}