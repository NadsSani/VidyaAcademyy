package com.vidhya.vidyaacademy;


public class Pending_Classlist_Adpter {

    private String Class_Name;
    private String s;

    public Pending_Classlist_Adpter(String class_Name, String s) {
       this.Class_Name = class_Name;
       this.s = s;
    }

    public String getClass_Name() {
        return Class_Name;
    }

    public String getS() {
        return s;
    }

    public void setClass_Name(String class_Name) {
        Class_Name = class_Name;
    }
}
