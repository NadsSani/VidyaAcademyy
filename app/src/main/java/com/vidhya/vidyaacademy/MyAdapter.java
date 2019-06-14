package com.vidhya.vidyaacademy;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {
    Context context;
    ArrayList<String> arrayList=new ArrayList<>();
    public MyAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource,objects);
        this.context = context;
        arrayList=objects;

    }
    @Override
    public int getCount() {
        return super.getCount();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(R.layout.activity_layout_for_listview,null);
        TextView tv_classlist_class=(TextView)v.findViewById(R.id.tv_classlist_class);
        tv_classlist_class.setText(arrayList.get(position).toString());


        /*
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,RecyclerViewForStudentInfo.class);


            }
        });
*/
        return v;

    }


}
