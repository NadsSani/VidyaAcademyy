package com.vidhya.vidyaacademy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddAdmin extends AppCompatActivity {
    EditText edt_addadmin_name, edt_addadmin_address, edt_addadmin_email, edt_addadmin_phno, edt_addadmin_password, edt_addadmin_userid;
    Spinner spinner_addadmin_class;
    Button btn_addadmin_upload, btn_addadmin_add;
    public AddAdmin_adapter addAdminPojo;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String name, email, phno, password, address;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_addadmin );

        edt_addadmin_name = findViewById( R.id.edt_addadmin_name );
        edt_addadmin_address = findViewById( R.id.edt_addadmin_address );
        edt_addadmin_email = findViewById( R.id.edt_addadmin_email );
        edt_addadmin_phno = findViewById( R.id.edt_addadmin_phno );
        edt_addadmin_password = findViewById( R.id.edt_addadmin_password );
        edt_addadmin_userid = findViewById( R.id.edt_addadmin_userid );/*
        spinner_addadmin_class=(Spinner)findViewById(R.id.spinner_addadmin_class);*//*
        btn_addadmin_upload=(Button)findViewById(R.id.btn_addadmin_upload);*/
        btn_addadmin_add = (Button) findViewById( R.id.btn_addadmin_add );


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference( "users/admin/" );


        btn_addadmin_add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String uid = edt_addadmin_userid.getText().toString();
                name = edt_addadmin_name.getText().toString();
                email = edt_addadmin_email.getText().toString();
                phno = edt_addadmin_phno.getText().toString();
                password = edt_addadmin_password.getText().toString();
                address = edt_addadmin_address.getText().toString();


                /*Log.e( "NAME",name );
                addAdminPojo.setName( name );
                addAdminPojo.setAddress( address );
                addAdminPojo.setEmail( email );
                addAdminPojo.setPhno( phno );
                addAdminPojo.setPswd( password );*/

                databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild( uid )) {
                            Toast.makeText( getApplicationContext(), "This UserId already exist....Try another UserId", Toast.LENGTH_LONG ).show();


                        } else {
                            addAdminPojo = new AddAdmin_adapter( name, email, address, phno, password );
                            databaseReference.child( uid ).setValue( addAdminPojo );
                            Toast.makeText( getApplicationContext(), "New Admin Created =" +uid, Toast.LENGTH_LONG ).show();
                            /*Intent i=new Intent( getApplicationContext(),AdminList.class );
                            startActivity( i );*/

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );


            }
        } );
    }
}
