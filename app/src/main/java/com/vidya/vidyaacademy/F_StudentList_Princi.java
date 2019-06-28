package com.vidya.vidyaacademy;

import android.content.Context;
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


public class F_StudentList_Princi extends Fragment {

    ArrayList<Princi_Studlist_Adapter> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;
    String AdminID,ClassID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate( R.layout.fragment_recy_princi_cardstud,container,false );



        /*Intent i = getIntent();
        final String ClassID = i.getStringExtra("ClassID");
        final String AdminID=i.getStringExtra( "AdminID" );*/

        AdminID = getArguments().getString("AdminID");
        ClassID = getArguments().getString("ClassID");
        Log.e("Bundle_value",ClassID);


        final RecyclerView princi_recyclerforstudcard = (RecyclerView) view.findViewById(R.id.princi_recyclerforstudcard);

        princi_recyclerforstudcard.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        princi_recyclerforstudcard.setLayoutManager(layoutManager);




        sharedPreferences = getActivity().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        String userid = sharedPreferences.getString("userid", "");

        arrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("users/admin/" + AdminID + "/" + ClassID  );

        Log.e("ClassID",ClassID);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.e("classlist", dataSnapshot1.getKey());


                    Log.e("studentlist", dataSnapshot1.child( "name" ).toString());

                    Princi_Studlist_Adapter princi_stud_cardDetails = new Princi_Studlist_Adapter(dataSnapshot1.child("name").getValue().toString(), dataSnapshot1.child("address").getValue().toString(), dataSnapshot1.child("parent_name").getValue().toString(), dataSnapshot1.child("image").getValue().toString(),dataSnapshot1.getKey().toString());
                    arrayList.add(princi_stud_cardDetails);

                }
                RecyclerViewForStudentInfo_princi playAdapter1 = new RecyclerViewForStudentInfo_princi(getActivity(),arrayList,ClassID,AdminID);
                princi_recyclerforstudcard.setAdapter(playAdapter1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "there is some error found", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }
}
