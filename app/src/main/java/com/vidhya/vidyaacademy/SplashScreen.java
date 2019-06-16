package com.vidhya.vidyaacademy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.vidhya.vidyaacademy.Login.REMINDER;

public class SplashScreen extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*Simple hold animation to hold ImageView in the centre of the screen at a slightly larger
        scale than the ImageView's original one.*/
        Animation hold = AnimationUtils.loadAnimation(this, R.anim.hold);

        /* Translates ImageView from current position to its original position, scales it from
        larger scale to its original scale.*/
        final Animation translateScale = AnimationUtils.loadAnimation(this, R.anim.translate_scale);

        final ImageView imageView = (ImageView) findViewById(R.id.img_login_logo);




        translateScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                    sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
                Log.e("Aswathy",sharedPreferences.getString("Reminder",""));

                if(sharedPreferences.getString("Reminder","").equals("yes")){


                    switch(sharedPreferences.getString("key",""))
                    {

                        case "admin":
                            Intent i=new Intent(getApplicationContext(),Admin.class);
                            startActivity(i);

                            break;

                        case "activity_parent":
                            Intent i2=new Intent(getApplicationContext(),Parent.class);
                            startActivity(i2);


                            break;

                        case "activity_main_principal":
                            Intent i3=new Intent(getApplicationContext(), Principal.class);
                            startActivity(i3);


                            break;

                    }

                }else {
                    Intent i=new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                }


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.clearAnimation();
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        hold.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.clearAnimation();
                imageView.startAnimation(translateScale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        imageView.startAnimation(hold);


    }

    public void signin(final String user, final String pass){

        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "userid and password cannot be empty", Toast.LENGTH_LONG).show();
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
                            editor.commit();

                            switch (dataSnapshot1.getKey().toString()) {

                                case "admin":
                                    Toast.makeText(getApplicationContext(), "admin", Toast.LENGTH_LONG).show();
                                    sharedToSave(dataSnapshot2);

                                    Intent intent = new Intent(getApplicationContext(), Admin.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(), "adminl", Toast.LENGTH_LONG).show();

                                    break;
                              /* case "parent":
                                   *//* Toast.makeText(getApplicationContext(), "activity_parent", Toast.LENGTH_LONG).show();
                                    sharedToSave(dataSnapshot2);
                                    Intent intent1 = new Intent(getApplicationContext(), Parent.class);
                                    startActivity(intent1);
                                    Toast.makeText(getApplicationContext(), "parentl", Toast.LENGTH_LONG).show();

                                    break;*//*
                               continue;*/
                                case "principal":
                                    Toast.makeText(getApplicationContext(), "Principal", Toast.LENGTH_LONG).show();
                                    sharedToSave(dataSnapshot2);
                                    Intent intent2 = new Intent(getApplicationContext(), Principal.class);
                                    startActivity(intent2);
                                    Toast.makeText(getApplicationContext(), "principal", Toast.LENGTH_LONG).show();

                                    break;
                            }


                        }
                        else if (user.equals( dataSnapshot2.getKey() ) && pass.equals( dataSnapshot2.child( "reg_No" ).getValue() )) {

                            sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            editor.putString(REMINDER,"yes");
                            editor.commit();

                            switch (dataSnapshot1.getKey().toString()) {


                                case "admin":
                                    continue;
                                case "parent":
                                    Toast.makeText( getApplicationContext(), "activity_parent", Toast.LENGTH_LONG ).show();
                                    sharedToSave2( dataSnapshot2 );
                                    editor.putString( "key", "activity_parent" );
                                    editor.commit();
                                    Intent intent1 = new Intent( SplashScreen.this, Parent.class );
                                    startActivity( intent1 );
                                    Toast.makeText( getApplicationContext(), "parentl", Toast.LENGTH_LONG ).show();

                                    break;
                                case "principal":
                                    continue;


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
    public void sharedToSave2(DataSnapshot dataSnapshot2) {
        sharedPreferences = getApplicationContext().getSharedPreferences( "MyShared", Context.MODE_PRIVATE );
        editor = sharedPreferences.edit();
        editor.putString( "username", dataSnapshot2.getKey().toString() );
        editor.putString( "password", dataSnapshot2.child( "reg_No" ).getValue().toString() );
        editor.commit();


    }


}
