package com.vidhya.vidyaacademy;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewForClassInfo_princi extends RecyclerView.Adapter<RecyclerViewForClassInfo_princi.ViewHolder> {


    TextView tv_princi_class_name ;
    ArrayList<Princi_class_CardDetails> list;
    Context context;
    String AdminID;

    public RecyclerViewForClassInfo_princi(Context context, ArrayList<Princi_class_CardDetails> arrayList, String adminID){
        this.list = arrayList;
        this.context = context;
        this.AdminID=adminID;
    }

    @NonNull
    @Override
    public RecyclerViewForClassInfo_princi.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView view = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_princi_card_class, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewForClassInfo_princi.ViewHolder viewHolder, final int i) {
        final int j;
        final CardView cardView = viewHolder.cardView;


        tv_princi_class_name= (TextView) cardView.findViewById(R.id.tv_princi_class_name);
        tv_princi_class_name.setText(list.get(i).getS().toString());



        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent( context,StudentList_Princi.class );
                intent.putExtra( "ClassID",list.get( i ).getS() );
                intent.putExtra( "AdminID",AdminID );
                Log.e( "ClassId" ,list.get( i ).getS().toString());
                context.startActivity(intent);

/*
                Intent intent = new Intent(context, ClasstList_Card_princi.class);
                intent.putExtra("AdminID",list.get(i).getUserid());
                Log.e("AdminID",list.get(i).getUserid());
                context.startActivity(intent);*/
            }
        });

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.princi_cardview_forclass);

        }
    }

    @Override
    public int getItemCount() {
        int len = list.size();
        return len;
    }
}
