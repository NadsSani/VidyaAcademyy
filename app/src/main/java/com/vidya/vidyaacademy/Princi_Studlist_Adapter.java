package com.vidya.vidyaacademy;

public class Princi_Studlist_Adapter {

    private String name;
    private String address;
    private String email;
    private String image;
    private String phno;

    public Princi_Studlist_Adapter(String name, String address, String email, String image, String phno) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.image = image;
        this.phno = phno;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
