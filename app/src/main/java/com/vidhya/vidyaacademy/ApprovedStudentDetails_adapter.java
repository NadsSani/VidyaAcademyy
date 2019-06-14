package com.vidhya.vidyaacademy;

public class ApprovedStudentDetails_adapter {

    private String Name, Address, Parent_Name, Tutor , Reg_No, S_Class;

    public ApprovedStudentDetails_adapter(String name, String address, String parent_Name, String reg_No, String s_Class, String tutor) {
        Name = name;
        Address = address;
        Parent_Name = parent_Name;
        Tutor = tutor;
        Reg_No = reg_No;
        S_Class = s_Class;
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
}