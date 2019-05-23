package com.vidhya.vidyaacademy;

public class Admin_Stud_CardDetails {
    private String name;
    private String address;
    private String parent_name;


    public Admin_Stud_CardDetails(String name, String address, String parent_name, String s) {
        this.name = name;
        this.address = address;
        this.parent_name = parent_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }
}
