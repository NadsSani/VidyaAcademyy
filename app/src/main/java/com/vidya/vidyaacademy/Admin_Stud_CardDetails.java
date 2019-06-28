package com.vidya.vidyaacademy;

public class Admin_Stud_CardDetails {
    private String name;
    private String address;
    private String parent_name;
    private String image;
    private String regno;

    public Admin_Stud_CardDetails(String name, String address, String parent_name, String image, String regno) {
        this.name = name;
        this.address = address;
        this.parent_name = parent_name;
        this.image = image;
        this.regno = regno;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }
}
