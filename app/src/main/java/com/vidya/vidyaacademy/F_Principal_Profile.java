package com.vidya.vidyaacademy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class F_Principal_Profile extends Fragment {

    CircleImageView iv_profile_princi;
    TextView tv_profile_p_name, tv_profile_p_email, tv_profile_p_phno, tv_profile_p_academic_year;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate( R.layout.fragment_princi_profile,container,false);

        iv_profile_princi = view.findViewById( R.id.iv_profile_princi );
        tv_profile_p_name = view.findViewById( R.id.tv_profile_p_name );
        tv_profile_p_email = view.findViewById( R.id.tv_profile_p_email );
        tv_profile_p_phno = view.findViewById( R.id.tv_profile_p_phno );
        tv_profile_p_academic_year = view.findViewById( R.id.tv_profile_p_academic_year );

        sharedPreferences = getActivity().getSharedPreferences( "MyShared", Context.MODE_PRIVATE );
        String userid = sharedPreferences.getString( "userid", "" );
        databaseReference = FirebaseDatabase.getInstance().getReference( "users/principal/" + userid );
        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String Name = dataSnapshot.child( "name" ).getValue().toString();
                String Academic_year = dataSnapshot.child( "academic_year" ).getValue().toString();
                String Email = dataSnapshot.child( "email" ).getValue().toString();
                String Phno = dataSnapshot.child( "phno" ).getValue().toString();

                tv_profile_p_name.setText( Name );
                tv_profile_p_email.setText( Email );
                tv_profile_p_phno.setText( Phno );
                tv_profile_p_academic_year.setText( Academic_year );

                Glide.with( getActivity() ).load( dataSnapshot.child( "image" ).getValue() ).into( iv_profile_princi );


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( getActivity(), "there is some error found", Toast.LENGTH_SHORT ).show();


            }
        } );



        return view;
    }
}
