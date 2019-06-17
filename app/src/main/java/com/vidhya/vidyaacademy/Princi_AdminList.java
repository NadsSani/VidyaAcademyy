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

public class Princi_AdminList extends AppCompatActivity {


    ArrayList<Princi_Adminlist_Adapter> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy_princi_cardadmin );
        Intent i = getIntent();
        final String UID = i.getStringExtra("admin_list");


        arrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("users/admin" );
        final RecyclerView recyclerforadmincard = (RecyclerView) findViewById(R.id.recyclerforadmincard);

        recyclerforadmincard.setHasFixedSize(true);

        LinearLayoutManager layoutManagern = new LinearLayoutManager(this);

        recyclerforadmincard.setLayoutManager(layoutManagern);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Log.e("Admin_List",dataSnapshot1.getChildren().toString());

                    Princi_Adminlist_Adapter princi_admin_cardDetails= new Princi_Adminlist_Adapter(dataSnapshot1.child("name").getValue().toString(),dataSnapshot1.getKey().toString(),dataSnapshot1.child( "image" ).getValue().toString());
                    arrayList.add(princi_admin_cardDetails);


                }
                RecyclerViewForAdminInfo playAdapternew = new RecyclerViewForAdminInfo( Princi_AdminList.this, arrayList);
                recyclerforadmincard.setAdapter(playAdapternew);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "there is some error found", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
