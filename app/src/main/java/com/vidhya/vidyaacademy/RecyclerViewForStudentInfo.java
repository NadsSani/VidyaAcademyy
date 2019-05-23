package com.vidhya.vidyaacademy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class RecyclerViewForStudentInfo extends RecyclerView.Adapter<RecyclerViewForStudentInfo.ViewHolder> {


TextView Student_Name,Register_No,Total_Grade;
ImageView photo;
ArrayList<Admin_Stud_CardDetails> list;
Context context;

    public RecyclerViewForStudentInfo(Context context, ArrayList<Admin_Stud_CardDetails> arrayList){
            this.list = arrayList;
            this.context = context;
    }


    @NonNull
    @Override
    public RecyclerViewForStudentInfo.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView view = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.admin_card_stud,viewGroup,false);
        return new RecyclerViewForStudentInfo.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewForStudentInfo.ViewHolder viewHolder, final int i) {
        final int j;

        final CardView cardView = viewHolder.cardView;
        final Context context = cardView.getContext();
        Student_Name = (TextView)cardView.findViewById(R.id.tv_card_name);
        Register_No = (TextView)cardView.findViewById(R.id.tv_card_reg_no);
        Total_Grade = (TextView)cardView.findViewById(R.id.tv_card__grade);

        Student_Name.setText(list.get(i).getName().toString());
        Register_No.setText(list.get(i).getAddress().toString());
        Total_Grade.setText( list.get(i).getParent_name().toString());

/*
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(context, Enter_Student_Details.class);
              intent.putExtra("Student Name",list.get(i).getName());
              context.startActivity(intent);
            }
        });
*/

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.cardview_forStudent);

        }
    }
    @Override
    public int getItemCount() {
        int len = list.size();
        return len;
    }
}
