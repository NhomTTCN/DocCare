package com.example.doccare.Model;

public class Messenger {
    String idWith;
    String username;
    String doctorname;
    String message;
    String time;


    public Messenger(){}
    public String getIdWith() {
        return idWith;
    }

    public void setIdWith(String idWith) {
        this.idWith = idWith;
    }

    public String getUser_name() {
        return username;
    }

    public void setUser_name(String user_name) {
        this.username = user_name;
    }

    public String getDoctor_name() {
        return doctorname;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctorname = doctor_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Messenger(String idWith, String user_name, String doctor_name, String message, String time) {
        this.idWith = idWith;
        this.username = user_name;
        this.doctorname = doctor_name;
        this.message = message;
        this.time = time;
    }



}
