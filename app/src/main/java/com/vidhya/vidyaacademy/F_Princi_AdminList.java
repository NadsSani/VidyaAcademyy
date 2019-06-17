package com.vidhya.vidyaacademy;

import android.content.Intent;
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

public class F_Princi_AdminList extends Fragment {

    ArrayList<Princi_Adminlist_Adapter> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate( R.layout.fragment_recy_princi_cardadmin,container,false );

       /* Intent i = getIntent();
        final String UID = i.getStringExtra("admin_list");
*/

        arrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("users/admin" );
        final RecyclerView recyclerforadmincard = (RecyclerView) view.findViewById(R.id.recyclerforadmincard);

        recyclerforadmincard.setHasFixedSize(true);

        LinearLayoutManager layoutManagern = new LinearLayoutManager(getContext());

        recyclerforadmincard.setLayoutManager(layoutManagern);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Log.e("Admin_List",dataSnapshot1.getChildren().toString());

                    Princi_Adminlist_Adapter princi_admin_cardDetails= new Princi_Adminlist_Adapter(dataSnapshot1.child("name").getValue().toString(),dataSnapshot1.getKey().toString(),dataSnapshot1.child( "image" ).getValue().toString());
                    arrayList.add(princi_admin_cardDetails);


                }
                RecyclerViewForAdminInfo playAdapternew = new RecyclerViewForAdminInfo( getActivity(), arrayList);
                recyclerforadmincard.setAdapter(playAdapternew);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "there is some error found", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }
}
