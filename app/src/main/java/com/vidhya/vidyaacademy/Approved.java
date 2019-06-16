package com.vidhya.vidyaacademy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Approved extends AppCompatActivity {
    ArrayList<Approved_Adapter> approvedAdapterArrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recy_approved );

        Intent i = getIntent();
        final String UID = i.getStringExtra("admin_list");


        approvedAdapterArrayList = new ArrayList<Approved_Adapter>();
        databaseReference = FirebaseDatabase.getInstance().getReference("users/admin" );
        final RecyclerView recyclerforapproved= (RecyclerView) findViewById(R.id.recyclerfor_approved);

        recyclerforapproved.setHasFixedSize(true);

        LinearLayoutManager layoutManagern = new LinearLayoutManager(this);

        recyclerforapproved.setLayoutManager(layoutManagern);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Log.e("Admin_List",dataSnapshot1.getChildren().toString());

                    Approved_Adapter approvedAdapter= new Approved_Adapter(dataSnapshot1.child("name").getValue().toString(),dataSnapshot1.getKey().toString(),dataSnapshot1.child( "image" ).getValue().toString());
                    approvedAdapterArrayList.add(approvedAdapter);


                }
                RecyclerViewForApproved recyclerViewForApproved = new RecyclerViewForApproved( Approved.this,approvedAdapterArrayList);
                recyclerforapproved.setAdapter(recyclerViewForApproved);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "there is some error found", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
