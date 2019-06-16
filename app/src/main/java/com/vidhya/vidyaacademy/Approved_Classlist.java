package com.vidhya.vidyaacademy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class Approved_Classlist extends Activity {
    ArrayList<Approved_Classlist_Adpter> arrayList1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;
     String AdminID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recy_approved_classlist );
        Intent i = getIntent();
        AdminID = i.getStringExtra( "AdminID" );
        Log.e( "Admin", AdminID );


        arrayList1 = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference( "users/admin/" + AdminID );
        final RecyclerView recyclerView = (RecyclerView) findViewById( R.id.recyclerforapprovedclasslist );

        recyclerView.setHasFixedSize( true );

        LinearLayoutManager layoutManagern = new LinearLayoutManager( this );

        recyclerView.setLayoutManager( layoutManagern );

        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.e( "children", dataSnapshot1.getChildren().toString() );

                    switch (dataSnapshot1.getKey().toString()) {


                        case "password":
                            continue;
                        case "name":
                            continue;
                        case "address":
                            continue;
                        case "email":
                            continue;
                        case "phno":
                            continue;
                        case "image":
                            continue;
                        case "pending":
                            continue;
                        default:
                            Approved_Classlist_Adpter approvedClasslistAdpter = new Approved_Classlist_Adpter( dataSnapshot1.getChildren().toString(), dataSnapshot1.getKey().toString() );
                            arrayList1.add( approvedClasslistAdpter );


                    }


                }
                RecyclerViewFor_ApprovedClasslist recyclerViewForApprovedClasslist = new RecyclerViewFor_ApprovedClasslist( Approved_Classlist.this, arrayList1,AdminID );
                recyclerView.setAdapter( recyclerViewForApprovedClasslist );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( getApplicationContext(), "there is some error found", Toast.LENGTH_SHORT ).show();

            }
        } );

    }
}
