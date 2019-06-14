package com.vidhya.vidyaacademy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class MyAdapter_Tab extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String nadeem;
    String Student_name;
    PersonalDetails personaldetails;
    public MyAdapter_Tab(Context context, FragmentManager fm, int totalTabs, String nadeem,String Student_name) {
        super(fm);
        myContext = context;
        this.nadeem = nadeem;
        this.Student_name=Student_name;
        this.totalTabs = totalTabs;
        this.personaldetails = new PersonalDetails();
        Bundle bundle1=new Bundle();
        bundle1.putString("nadeem",nadeem);
        bundle1.putString("student_name",Student_name);
        personaldetails.setArguments(bundle1);
        Log.e("Nadeem....1",nadeem);
        Log.e("student_name",Student_name);
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:




                return personaldetails;


            case 1:
                MarkDetails markdetails = new MarkDetails();
                return markdetails;
            default:
                return null;
        }
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
