package com.vidhya.vidyaacademy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClassList_list_Admin extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView list_classlist;
    MyAdapter adapter;
    String[] default_items=new String[]{"ClassA","ClassB"};
    private final static String TAG="Aswathy";
    List<String> itemlist;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ArrayList<String> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classlist_list_admin);
        list_classlist=findViewById(R.id.list_classlist);
        sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        String userid=sharedPreferences.getString("userid","");



        itemlist=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("users/admin/"+ userid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemlist.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    /*for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {*/
                        Log.e(TAG, dataSnapshot1.getKey());
                    /*}*/

                    switch (dataSnapshot1.getKey().toString()){

                            case "password":
                                continue;
                            case "name":
                                continue;
                            case "address":
                                continue;
                            case "email":
                                continue;
                            case "phno":
                                continue;
                            case "image":
                                continue;
                        case "pending":
                            continue;
                            default:
                                arrayList.add(dataSnapshot1.getKey());

                    }

                   /* if (dataSnapshot1.getKey().equals("password")){
                        continue;
                    }
                    else {
                        arrayList.add(dataSnapshot1.getKey());
                    }*/

                }
                adapter = new MyAdapter(ClassList_list_Admin.this, R.layout.activity_classlist_list_admin,arrayList);
                list_classlist.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"there is some error found",Toast.LENGTH_SHORT).show();

            }
        });
        list_classlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"clicked on"+position,Toast.LENGTH_SHORT).show();

                list_classlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),"clicked on"+position,Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(), StudentList_Admin.class);
                        i.putExtra("student_list",arrayList.get(position));
                        Log.e("position",arrayList.get(position));
                        startActivity(i);

                    }
                });

            }
        });
    }

}
