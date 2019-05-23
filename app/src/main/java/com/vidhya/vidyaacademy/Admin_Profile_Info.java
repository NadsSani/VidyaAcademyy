package com.vidhya.vidyaacademy;

public class Admin_Profile_Info {

    private String a_name;
    private String a_address;
    private String a_email;
    private Integer phno;

    public Admin_Profile_Info(String a_name, String a_address, String a_email, Integer phno) {
        this.a_name = a_name;
        this.a_address = a_address;
        this.a_email = a_email;
        this.phno = phno;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_address() {
        return a_address;
    }

    public void setA_address(String a_address) {
        this.a_address = a_address;
    }

    public String getA_email() {
        return a_email;
    }

    public void setA_email(String a_email) {
        this.a_email = a_email;
    }

    public Integer getPhno() {
        return phno;
    }

    public void setPhno(Integer phno) {
        this.phno = phno;
    }
}