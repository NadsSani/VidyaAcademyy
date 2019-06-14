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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class Admin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CircleImageView iv_admin_photo;
    TextView tv_navhead_adminname, tv_navhead_adminmail;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String Name, Email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        NavigationView navigationView = findViewById( R.id.nav_view1 );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener( this );
        View headerLayout = navigationView.getHeaderView(0);
        iv_admin_photo=headerLayout.findViewById( R.id.iv_admin_photo_1 );
        tv_navhead_adminname=headerLayout.findViewById( R.id.tv_navhead_adminname_1);
        tv_navhead_adminmail=headerLayout.findViewById( R.id.tv_navhead_adminmail_1 );



        sharedPreferences = getApplicationContext().getSharedPreferences( "MyShared", Context.MODE_PRIVATE );
        String userid = sharedPreferences.getString( "userid", "" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "users/admin/" + userid );
        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Log.e( "Admin Profile1", dataSnapshot.getKey() );
                    Log.e( "Admin Name", dataSnapshot.child( "name" ).getValue().toString() );
                    // Log.e( "Admin Details",dataSnapshot1.getChildren().toString());


                    Name = dataSnapshot.child( "name" ).getValue().toString();
                    tv_navhead_adminname.setText( Name );
                    Log.e( "Admin Name", dataSnapshot.child( "name" ).toString() );
                    Email = dataSnapshot.child( "email" ).getValue().toString();
                    tv_navhead_adminmail.setText( Email );
                    Log.e( "Admin Email", dataSnapshot.child( "email" ).toString() );

                    Glide.with( getApplicationContext() ).load( dataSnapshot.child( "image" ).getValue() ).into( iv_admin_photo );


                }
                }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        } );


       /* Intent i=new Intent(getApplicationContext(),ClassList_list.class);
        startActivity(i);*/

    }

    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/

        moveTaskToBack( true );

        finish();

        android.os.Process.killProcess( android.os.Process.myPid() );
        System.exit( 1 );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.admin, menu );

        return true;
    }

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a activity_parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
            Intent i = new Intent( getApplicationContext(), Admin_Profile.class );
            startActivity( i );
        }/* else if (id == R.id.nav_studentDetails) {

        }*/ else if (id == R.id.nav_marks) {
           /* Intent i=new Intent(getApplicationContext(),Marks.class);
            startActivity(i);*/

        } else if (id == R.id.nav_view) {
            Intent i = new Intent( getApplicationContext(), ClassList_list_Admin.class );
            startActivity( i );

        } else if (id == R.id.nav_logout) {
            SharedPreferences preferences = getApplicationContext().getSharedPreferences( "MyShared", Context.MODE_PRIVATE );
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent( Admin.this, Login.class );
            intent.putExtra( "finish", true );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity( intent );

            finish();


        } /*else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}
