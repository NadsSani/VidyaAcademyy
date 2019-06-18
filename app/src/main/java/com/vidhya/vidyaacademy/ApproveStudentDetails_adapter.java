package com.vidhya.vidyaacademy;

public class ApproveStudentDetails_adapter {

    private String Name, Address, Parent_Name, Tutor , Reg_No, S_Class,Image;
    private String Status;

    public ApproveStudentDetails_adapter(String name, String address, String parent_Name, String reg_No, String s_Class, String tutor,String status,String Image1) {
        Name = name;
        Address = address;
        Parent_Name = parent_Name;
        Tutor = tutor;
        Reg_No = reg_No;
        S_Class = s_Class;
        Status=status;
        Image=Image1;
    }

    public ApproveStudentDetails_adapter(String name, String address, String parentName, String regNo, String classID, String adminID) {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getParent_Name() {
        return Parent_Name;
    }

    public void setParent_Name(String parent_Name) {
        Parent_Name = parent_Name;
    }

    public String getTutor() {
        return Tutor;
    }

    public void setTutor(String tutor) {
        Tutor = tutor;
    }

    public String getReg_No() {
        return Reg_No;
    }

    public void setReg_No(String reg_No) {
        Reg_No = reg_No;
    }

    public String getS_Class() {
        return S_Class;
    }

    public void setS_Class(String s_Class) {
        S_Class = s_Class;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}