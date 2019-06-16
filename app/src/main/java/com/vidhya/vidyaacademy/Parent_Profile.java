package com.vidhya.vidyaacademy;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Parent_Profile extends AppCompatActivity {

    CircleImageView user_profile_photo_pp;
    TextView parent_pro_name, parent_pro_StudentName, parent_pro_Address, parent_pro_Class, parent_pro_Staff, parent_pro_Reg_No;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_parent_profile );

        user_profile_photo_pp = findViewById( R.id.user_profile_photo_pp );
        parent_pro_name = findViewById( R.id.parent_pro_name );
        parent_pro_StudentName = findViewById( R.id.parent_pro_StudentName );
        parent_pro_Address = findViewById( R.id.parent_pro_Address );
        parent_pro_Class = findViewById( R.id.parent_pro_Class );
        parent_pro_Staff = findViewById( R.id.parent_pro_Staff );
        parent_pro_Reg_No = findViewById( R.id.parent_pro_Reg_No );




        sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        String userid=sharedPreferences.getString("userid","");
        databaseReference= FirebaseDatabase.getInstance().getReference("users/parent/"+ userid);
        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.e( "Admin Profile", dataSnapshot.getKey() );
                    Log.e( "Admin Profile", dataSnapshot.child( "name").getValue().toString() );


                        String PName = dataSnapshot.getKey().toString();
                        String SName = dataSnapshot.child( "name" ).getValue().toString();
                        String Address = dataSnapshot.child( "address" ).getValue().toString();
                        String Class = dataSnapshot.child( "s_Class" ).getValue().toString();
                        String Reg_No = dataSnapshot.child( "reg_No" ).getValue().toString();
                        String Tutor = dataSnapshot.child( "tutor" ).getValue().toString();


                        parent_pro_name.setText( PName );
                        parent_pro_StudentName.setText( SName );
                        parent_pro_Address.setText( Address );
                        parent_pro_Class.setText( Class );
                        parent_pro_Staff.setText( Tutor );
                        parent_pro_Reg_No.setText( Reg_No );


                        Glide.with( getApplicationContext() ).load( dataSnapshot.child( "image" ).getValue() ).into( user_profile_photo_pp );


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

    }
}

