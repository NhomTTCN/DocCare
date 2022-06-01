package com.example.doccare.Model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class DoctorInfo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;
    @SerializedName("department_name")
    @Expose
    private String departmentName;
    @SerializedName("clinic_name")
    @Expose
    private String clinicName;

    private boolean is_expanded = true;


    public boolean isIs_expanded() {
        return is_expanded;
    }

    public void setIs_expanded(boolean is_expanded) {
        this.is_expanded = is_expanded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public DoctorInfo(String id, String fullName, String gender, String email, String address, String hospitalName, String departmentName, String clinicName, boolean is_expanded) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.hospitalName = hospitalName;
        this.departmentName = departmentName;
        this.clinicName = clinicName;
        this.is_expanded = true;
    }
    public DoctorInfo(){}
}