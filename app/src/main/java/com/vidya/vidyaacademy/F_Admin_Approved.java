package com.vidya.vidyaacademy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class F_Admin_Approved extends Fragment {


    ArrayList<Approved_Adapter> approvedAdapterArrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recy_approved, container, false);


       /* Intent i = getIntent();
        final String UID = i.getStringExtra("admin_list");
*/

        approvedAdapterArrayList = new ArrayList<Approved_Adapter>();
        databaseReference = FirebaseDatabase.getInstance().getReference("users/admin");
        final RecyclerView recyclerforapproved = (RecyclerView)view. findViewById(R.id.recyclerfor_approved);

        recyclerforapproved.setHasFixedSize(true);

        LinearLayoutManager layoutManagern = new LinearLayoutManager(getActivity());

        recyclerforapproved.setLayoutManager(layoutManagern);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Log.e("Admin_List", dataSnapshot1.getChildren().toString());

                    Approved_Adapter approvedAdapter = new Approved_Adapter(dataSnapshot1.child("name").getValue().toString(), dataSnapshot1.getKey().toString(), dataSnapshot1.child("image").getValue().toString());
                    approvedAdapterArrayList.add(approvedAdapter);


                }
                RecyclerViewForApproved recyclerViewForApproved = new RecyclerViewForApproved(getActivity(), approvedAdapterArrayList);
                recyclerforapproved.setAdapter(recyclerViewForApproved);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "there is some error found", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }
}
