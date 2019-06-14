package com.vidhya.vidyaacademy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    ImageView img_login_logo;
    EditText edt_login_username, edt_login_password;
    Button btn_login_signin;
    TextView tv_login_register, tv_login_forgot;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    static final String REMINDER="Reminder";
    static final String userid="userid";
    static final String password="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login_signin = (Button) findViewById(R.id.btn_login_signin);
        edt_login_username = (EditText) findViewById(R.id.edt_login_username);
        edt_login_password = (EditText) findViewById(R.id.edt_login_password);
        tv_login_register = (TextView) findViewById(R.id.tv_login_register);
        tv_login_forgot = (TextView) findViewById(R.id.tv_login_forgot);
        img_login_logo = (ImageView) findViewById(R.id.img_login_logo);
        btn_login_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = edt_login_username.getText().toString();

                final String pass = edt_login_password.getText().toString();

                signin(user, pass);

                overridePendingTransition(0, 0);
                View relativeLayout = findViewById(R.id.login_container);
                Animation animation = AnimationUtils.loadAnimation(Login.this, android.R.anim.fade_in);
                relativeLayout.startAnimation(animation);


            }
        });
    }

    public void signin(final String user, final String pass) {

        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(Login.this, "userid and password cannot be empty", Toast.LENGTH_LONG).show();
            return;

        }


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                        // Log.e(TAG,dataSnapshot1.getChildren().toString());

                        if (user.equals(dataSnapshot2.getKey()) && pass.equals(dataSnapshot2.child("password").getValue())) {

                            sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            editor.putString(REMINDER,"yes");
                            editor.putString(userid,dataSnapshot2.getKey().toString());
                            editor.putString(password,dataSnapshot2.child("password").getValue().toString());
                            editor.commit();

                            switch (dataSnapshot1.getKey().toString()) {

                                case "admin":
                                    Toast.makeText(getApplicationContext(), "admin", Toast.LENGTH_LONG).show();
                                    sharedToSave(dataSnapshot2);

                                    editor.putString("key","admin");
                                    editor.commit();

                                    Intent intent = new Intent(Login.this, Admin.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "adminl", Toast.LENGTH_LONG).show();

                                    break;
                                case "parent":
                                    Toast.makeText(getApplicationContext(), "activity_parent", Toast.LENGTH_LONG).show();
                                    sharedToSave(dataSnapshot2);
                                    editor.putString("key","activity_parent");
                                    editor.commit();
                                    Intent intent1 = new Intent(Login.this, Parent.class);
                                    startActivity(intent1);
                                    Toast.makeText(getApplicationContext(), "parentl", Toast.LENGTH_LONG).show();

                                    break;
                                case "principal":
                                    Toast.makeText(getApplicationContext(), "Principal", Toast.LENGTH_LONG).show();
                                    sharedToSave(dataSnapshot2);

                                    editor.putString("key","principal");
                                    editor.commit();

                                    Intent intent2 = new Intent(Login.this, Principal.class);
                                    startActivity(intent2);
                                    Toast.makeText(getApplicationContext(), "activity_principal", Toast.LENGTH_LONG).show();

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

    public void sharedToSave(DataSnapshot dataSnapshot2) {
        sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("username", dataSnapshot2.getKey().toString());
        editor.putString("password", dataSnapshot2.child("password").getValue().toString());
        editor.commit();


    }




}