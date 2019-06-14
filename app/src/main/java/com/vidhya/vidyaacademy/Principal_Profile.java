package com.vidhya.vidyaacademy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class Principal_Profile extends AppCompatActivity {

    CircleImageView iv_profile_princi;
    TextView tv_profile_p_name, tv_profile_p_email, tv_profile_p_phno, tv_profile_p_academic_year;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_princi_profile );

        iv_profile_princi = findViewById( R.id.iv_profile_princi );
        tv_profile_p_name = findViewById( R.id.tv_profile_p_name );
        tv_profile_p_email = findViewById( R.id.tv_profile_p_email );
        tv_profile_p_phno = findViewById( R.id.tv_profile_p_phno );
        tv_profile_p_academic_year = findViewById( R.id.tv_profile_p_academic_year );

        sharedPreferences = getApplicationContext().getSharedPreferences( "MyShared", Context.MODE_PRIVATE );
        String userid = sharedPreferences.getString( "userid", "" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "users/principal/" + userid );
        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String Name = dataSnapshot.child( "name" ).getValue().toString();
                String Academic_year = dataSnapshot.child( "academic_year" ).getValue().toString();
                String Email = dataSnapshot.child( "email" ).getValue().toString();
                String Phno = dataSnapshot.child( "phno" ).getValue().toString();

                tv_profile_p_name.setText( Name );
                tv_profile_p_email.setText( Email );
                tv_profile_p_phno.setText( Phno );
                tv_profile_p_academic_year.setText( Academic_year );

                Glide.with( getApplicationContext() ).load( dataSnapshot.child( "image" ).getValue() ).into( iv_profile_princi );


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( getApplicationContext(), "there is some error found", Toast.LENGTH_SHORT ).show();


            }
        } );


    }
}