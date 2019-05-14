package com.vidhya.vidyaacademy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.loginbutton);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = username.getText().toString();

                final String pass = password.getText().toString();

                signin(user, pass);


            }
        });
    }
        public void signin(final String user ,final String pass){

            if(user.isEmpty() || pass.isEmpty()){
                Toast.makeText(MainActivity.this,"userid and password cannot be empty",Toast.LENGTH_LONG).show();
                return;
            }

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                            // Log.e(TAG,dataSnapshot1.getChildren().toString());

                            if (user.equals(dataSnapshot2.getKey()) && pass.equals(dataSnapshot2.child("password").getValue())) {

                                switch (dataSnapshot1.getKey().toString()){

                                    case "admin":
                                        Intent intent = new Intent(MainActivity.this,Admin.class);
                                        startActivity(intent);

                                        break;
                                    case "parent":
                                        Intent intent1 = new Intent(MainActivity.this,Parent.class);
                                        startActivity(intent1);
                                        break;
                                    case "principal":
                                        Intent intent2 = new Intent(MainActivity.this,Principal.class);
                                        startActivity(intent2);
                                        break;
                                }


                            }

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
    }
}