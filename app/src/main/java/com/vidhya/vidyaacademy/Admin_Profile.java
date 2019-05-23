package com.vidhya.vidyaacademy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_Profile extends AppCompatActivity {

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
        setContentView(R.layout.activity_profile);

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
                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"there is some error found",Toast.LENGTH_SHORT).show();

            }
        });


    }
}
