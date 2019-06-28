package com.vidya.vidyaacademy;

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

public class F_Princi_StudentDetails extends Fragment {

    TextView tv_stud_name_princi_approval, tv_stud_address_princi_approval, tv_stud_pname_princi_approval;
    //Button btn_cancel_princi_studdetails, btn_approve_princi_studdetails;
    CircleImageView tv_stud_photo_princi_approval;
    String Name, Address, ParentName;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate( R.layout.fragment_student_details_princi,container,false );

        tv_stud_name_princi_approval = view.findViewById( R.id.tv_stud_name_princi_approval );
        tv_stud_address_princi_approval = view.findViewById( R.id.tv_stud_address_princi_approval );
        tv_stud_pname_princi_approval = view.findViewById( R.id.tv_stud_pname_princi_approval );
        // btn_cancel_princi_studdetails = findViewById( R.id.btn_cancel_princi_studdetails );
        //btn_approve_princi_studdetails = findViewById( R.id.btn_approve_princi_studdetails );
        tv_stud_photo_princi_approval = view.findViewById( R.id.tv_stud_photo_princi_approval );

        /*Intent i = getIntent();
        final String ClassID = i.getStringExtra( "ClassID" );
        final String AdminID = i.getStringExtra( "AdminID" );
        final String RegNo = i.getStringExtra( "RegNo" );*/

        final String AdminID = getArguments().getString("AdminID");
        final String ClassID = getArguments().getString("ClassID");
        final String StudID = getArguments().getString("StudID");
        Log.e("Bundle_value",StudID);


        databaseReference = FirebaseDatabase.getInstance().getReference( "users/admin/" + AdminID + "/" + ClassID + "/" + StudID );
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

                    Glide.with( getActivity() ).load( dataSnapshot.child( "image" ).getValue().toString() ).into( tv_stud_photo_princi_approval );

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


        Log.e( "ClassID_Approval", ClassID );
        Log.e( "AdminID_Approval", AdminID );
        Log.e( "RegNo_Approval", StudID );



        return view;
    }
}
