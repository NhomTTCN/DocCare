package com.example.doccare.Model;

public class Messenger {
    String idWith;
    String username;
    String doctorname;
    String message;
    String time;
    Boolean isDoctor;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public Boolean getDoctor() {
        return isDoctor;
    }

    public void setDoctor(Boolean doctor) {
        isDoctor = doctor;
    }

    public Messenger(String idWith, String username, String doctorname, String message, String time, Boolean isDoctor) {
        this.idWith = idWith;
        this.username = username;
        this.doctorname = doctorname;
        this.message = message;
        this.time = time;
        this.isDoctor = isDoctor;
    }

    public Messenger() {
    }

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
