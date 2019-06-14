package com.vidhya.vidyaacademy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.bumptech.glide.Glide;



public class Admin_Profile extends Activity implements NavigationView.OnNavigationItemSelectedListener{

    TextView tv_profile_a_name,tv_profile_a_address,tv_profile_a_phno,tv_profile_a_email;
    ImageView iv_profile_admin;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private Admin_Profile_Info admin_profile_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);


        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout_admin_profile);
        NavigationView navigationView = findViewById(R.id.nav_view1_admin_profile);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
*/



        tv_profile_a_name=findViewById(R.id.tv_profile_a_name);
        tv_profile_a_address=findViewById(R.id.tv_profile_a_address);
        tv_profile_a_phno=findViewById(R.id.tv_profile_a_phno);
        tv_profile_a_email=findViewById(R.id.tv_profile_a_email);
        iv_profile_admin=findViewById(R.id.iv_profile_admin);




        sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        String userid=sharedPreferences.getString("userid","");
        databaseReference= FirebaseDatabase.getInstance().getReference("users/admin/"+ userid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Log.e("Admin Profile", dataSnapshot1.getKey());

                    if (dataSnapshot.getKey().equals("password")){
                        continue;
                    }
                    else {
                        String Name=dataSnapshot.child("name").getValue().toString();
                        String Address=dataSnapshot.child("address").getValue().toString();
                        String Email=dataSnapshot.child("email").getValue().toString();
                        String Phno=dataSnapshot.child("phno").getValue().toString();



                        tv_profile_a_name.setText(Name);
                        tv_profile_a_address.setText(Address);
                        tv_profile_a_email.setText(Email);
                        tv_profile_a_phno.setText(Phno);

                        Glide.with( getApplicationContext() ).load( dataSnapshot.child( "image" ).getValue() ).into( iv_profile_admin );

                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"there is some error found",Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @SuppressWarnings("StatementWithEmptyBody")

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
            Intent i=new Intent(getApplicationContext(), Admin_Profile.class);
            startActivity(i);
        }/* else if (id == R.id.nav_studentDetails) {

        }*/ else if (id == R.id.nav_marks) {
           /* Intent i=new Intent(getApplicationContext(),Marks.class);
            startActivity(i);*/

        } else if (id == R.id.nav_view) {
            Intent i=new Intent(getApplicationContext(), ClassList_list_Admin.class);
            startActivity(i);

        } else if (id == R.id.nav_logout) {
            SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(Admin_Profile.this, Login.class);
            intent.putExtra("finish", true);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            finish();


        } /*else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
