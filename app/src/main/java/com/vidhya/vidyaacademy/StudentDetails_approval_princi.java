package com.vidhya.vidyaacademy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentDetails_approval_princi extends AppCompatActivity {

    TextView tv_stud_name_princi_approval, tv_stud_address_princi_approval, tv_stud_pname_princi_approval;
    Button btn_cancel_princi_studdetails, btn_approve_princi_studdetails;
    CircleImageView tv_stud_photo_princi_approval;
    String Name, Address, ParentName;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_student_details_approval_princi );
        tv_stud_name_princi_approval = findViewById( R.id.tv_stud_name_princi_approval );
        tv_stud_address_princi_approval = findViewById( R.id.tv_stud_address_princi_approval );
        tv_stud_pname_princi_approval = findViewById( R.id.tv_stud_pname_princi_approval );
        btn_cancel_princi_studdetails = findViewById( R.id.btn_cancel_princi_studdetails );
        btn_approve_princi_studdetails = findViewById( R.id.btn_approve_princi_studdetails );
        tv_stud_photo_princi_approval = findViewById( R.id.tv_stud_photo_princi_approval );

        Intent i = getIntent();
        final String ClassID = i.getStringExtra( "ClassID" );
        final String AdminID = i.getStringExtra( "AdminID" );
        final String RegNo = i.getStringExtra( "RegNo" );


        databaseReference = FirebaseDatabase.getInstance().getReference( "users/admin/" + AdminID + "/" + ClassID + "/" + RegNo );
        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    //Log.e(  "Approval_Data",dataSnapshot.child( "name" ).getValue().toString());

                    Name = dataSnapshot.child( "name" ).getValue().toString();
                    Address = dataSnapshot.child( "address" ).getValue().toString();
                    ParentName = dataSnapshot.child( "parent_name" ).getValue().toString();

                    tv_stud_name_princi_approval.setText( Name );
                    tv_stud_address_princi_approval.setText( Address );
                    tv_stud_pname_princi_approval.setText( ParentName );

                    Glide.with( getApplicationContext() ).load( dataSnapshot.child( "image" ).getValue().toString() ).into( tv_stud_photo_princi_approval );

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


        Log.e( "ClassID_Approval", ClassID );
        Log.e( "AdminID_Approval", AdminID );
        Log.e( "RegNo_Approval", RegNo );

        btn_approve_princi_studdetails.setOnClickListener( new View.OnClickListener() {
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

    }
}
