package com.vidya.vidyaacademy;

public class AddAdmin_adapter {
    private String Name,Address,Email,Phno,password;

    public AddAdmin_adapter(String name, String address, String email, String phno, String password) {
        this.Name = name;
        this.Address = address;
        this.Email = email;
        this.Phno = phno;
        this.password = password;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhno() {
        return Phno;
    }

    public String getPassword() {
        return password;
    }
}
