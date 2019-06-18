package com.vidhya.vidyaacademy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class F_Approved_StudentDetails extends Fragment {

    TextView tv_stud_name_approved, tv_stud_address_approved, tv_stud_pname_approved;
    //Button btn_cancel_princi_studdetails, btn_approve_princi_studdetails;
    CircleImageView tv_stud_photo_approved;
    String Name, Address, ParentName;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_approved_student_details, container, false);

        tv_stud_name_approved = view.findViewById(R.id.tv_stud_name_approved);
        tv_stud_address_approved = view.findViewById(R.id.tv_stud_address_approved);
        tv_stud_pname_approved = view.findViewById(R.id.tv_stud_pname_approved);
        // btn_cancel_princi_studdetails = findViewById( R.id.btn_cancel_princi_studdetails );
        //btn_approve_princi_studdetails = findViewById( R.id.btn_approve_princi_studdetails );
        tv_stud_photo_approved = view.findViewById(R.id.tv_stud_photo_approved);

        /*Intent i = getIntent();
        final String ClassID = i.getStringExtra("ClassID");
        final String AdminID = i.getStringExtra("AdminID");
        final String RegNo = i.getStringExtra("RegNo");


        databaseReference = FirebaseDatabase.getInstance().getReference("users/admin/" + AdminID + "/" + ClassID + "/" + RegNo);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    //Log.e(  "Approval_Data",dataSnapshot.child( "name" ).getValue().toString());

                    Name = dataSnapshot.child("name").getValue().toString();
                    Address = dataSnapshot.child("address").getValue().toString();
                    ParentName = dataSnapshot.child("parent_name").getValue().toString();

                    tv_stud_name_approved.setText(Name);
                    tv_stud_address_approved.setText(Address);
                    tv_stud_pname_approved.setText(ParentName);

                    Glide.with(getActivity()).load(dataSnapshot.child("image").getValue().toString()).into(tv_stud_photo_approved);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Log.e("ClassID_Approval", ClassID);
        Log.e("AdminID_Approval", AdminID);
        Log.e("RegNo_Approval", RegNo);

       *//* btn_approve_princi_studdetails.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = database.getReference( "users/parent/" );

                databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ApprovedStudentDetails_adapter studentDetailsAdapter = new ApprovedStudentDetails_adapter( Name, Address, ParentName, RegNo, ClassID, AdminID );

                        databaseReference.child( ParentName ).setValue( studentDetailsAdapter );
                        Toast.makeText( getApplicationContext(), "Data Approved" , Toast.LENGTH_LONG ).show();


                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );


            }
        } );

        btn_cancel_princi_studdetails.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = database.getReference( "users/admin/"+AdminID+"/pending/");

                ApprovedStudentDetails_adapter studentDetailsAdapter = new ApprovedStudentDetails_adapter( Name, Address, ParentName, RegNo, ClassID, AdminID );

                databaseReference.child( ClassID ).child( RegNo ).setValue( studentDetailsAdapter );
                Toast.makeText( getApplicationContext(), "Data Rejected " , Toast.LENGTH_LONG ).show();


            }
        } );
*//*
*/
        return view;
    }
}
