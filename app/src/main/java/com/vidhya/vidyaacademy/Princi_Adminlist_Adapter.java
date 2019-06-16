package com.vidhya.vidyaacademy;

public class Princi_Adminlist_Adapter {
    private String name;
    private String userid;
    private String image;

    public Princi_Adminlist_Adapter(String name, String userid, String image) {

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