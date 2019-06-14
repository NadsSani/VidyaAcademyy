package com.vidhya.vidyaacademy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CircleImageView  iv_profile_princi;
    TextView tv_navhead_princiname,tv_navhead_princimail;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__principal);
        Toolbar toolbar = findViewById(R.id.toolbarP);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View headerLayout = navigationView.getHeaderView(0);
        iv_profile_princi=headerLayout.findViewById( R.id.iv_profile_princi );
        tv_navhead_princiname=headerLayout.findViewById( R.id.tv_navhead_princiname );
        tv_navhead_princimail=headerLayout.findViewById( R.id.tv_navhead_princimail );



        sharedPreferences = getApplicationContext().getSharedPreferences( "MyShared", Context.MODE_PRIVATE );
        String userid = sharedPreferences.getString( "userid", "" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "users/principal/" + userid );
        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                    String Name = dataSnapshot.child( "name" ).getValue().toString();
                    String Email = dataSnapshot.child( "email" ).getValue().toString();
                    tv_navhead_princiname.setText( Name );
                    tv_navhead_princimail.setText( Email );

                    Glide.with( getApplicationContext() ).load( dataSnapshot.child( "image" ).getValue() ).into( iv_profile_princi );
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        /*Intent i=new Intent(getApplicationContext(),ProfileScreenXMLUIDesign.class);
        startActivity(i);*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity__principal, menu);
        return true;
    }

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       */
/* if (id == R.id.action_settings) {
            return true;
        }*//*


        return super.onOptionsItemSelected(item);
    }
*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent i=new Intent(getApplicationContext(), Principal_Profile.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_addAdmin) {
            Intent i=new Intent(getApplicationContext(),AddAdmin.class);
            startActivity(i);

        } else if (id == R.id.nav_approval) {
            Intent i=new Intent(getApplicationContext(),AdminList.class);
            startActivity(i);

        } else if (id == R.id.nav_logoutP) {
            SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(Principal.this, Login.class);
            intent.putExtra("finish", true);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            finish();


        }/* else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
