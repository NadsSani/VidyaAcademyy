package com.vidhya.vidyaacademy;

public class Princi_Admin_CardDetails {
    private String name;
    private String userid;
    private String image;

    public Princi_Admin_CardDetails( String name,String userid,String image) {

        this.userid=userid;
        this.name = name;
        this.image=image;

    }

    public String getName() {
        return name;
    }


    public String getUserid() {
        return userid;
    }
    public String getImage() {
        return image;
    }


}