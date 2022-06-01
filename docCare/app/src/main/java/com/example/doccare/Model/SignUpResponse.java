package com.example.doccare.Model;

public class SignUpResponse {
    String id;
    String email;
    String role;
    String access_token;    String refresh_token;


    public SignUpResponse(){
    }

    public SignUpResponse(String id, String email, String role, String access_token, String refresh_token) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
