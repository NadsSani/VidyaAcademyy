package com.vidhya.vidyaacademy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class Approved_Studentlist extends AppCompatActivity {

    ArrayList<Approved_Studlist_Adapter> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy_approved_studentlist );


        Intent i = getIntent();
        final String ClassID = i.getStringExtra("ClassID");
        final String AdminID=i.getStringExtra( "AdminID" );


        final RecyclerView princi_recyclerforstudcard = (RecyclerView) findViewById(R.id.approved_studentlist_recy);

        princi_recyclerforstudcard.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        princi_recyclerforstudcard.setLayoutManager(layoutManager);



/*
        sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        String userid = sharedPreferences.getString("userid", "");*/

        arrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("users/admin/" + AdminID + "/" + ClassID  );

        Log.e("ClassID",ClassID);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.e("classlist", dataSnapshot1.getKey());

                    Log.e("status", dataSnapshot1.child( "status" ).getValue().toString());
                    /*SID=dataSnapshot1.getKey();
                    Log.e("SID",SID);*/
                    if (dataSnapshot1.child( "status" ).getValue().toString().equals( "approved" )) {
                        Log.e( "pending" ,dataSnapshot1.getChildren().toString());
                        Approved_Studlist_Adapter approvedStudlistAdapter = new Approved_Studlist_Adapter( dataSnapshot1.child( "name" ).getValue().toString(), dataSnapshot1.child( "address" ).getValue().toString(), dataSnapshot1.child( "parent_name" ).getValue().toString(), dataSnapshot1.child( "image" ).getValue().toString(), dataSnapshot1.getKey().toString() );
                        arrayList.add( approvedStudlistAdapter );
                    }
                    else {
                        continue;
                    }

                }
                RecyclerViewForApprovedStudentList playAdapter1 = new RecyclerViewForApprovedStudentList( Approved_Studentlist.this,arrayList,ClassID,AdminID);
                princi_recyclerforstudcard.setAdapter(playAdapter1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "there is some error found", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
