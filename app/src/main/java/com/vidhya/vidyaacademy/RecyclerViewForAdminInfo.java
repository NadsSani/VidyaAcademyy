package com.vidhya.vidyaacademy;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewForAdminInfo extends  RecyclerView.Adapter<RecyclerViewForAdminInfo.ViewHolder> {


    TextView tv_card_name_admin ;
    CircleImageView iv_card_photo_admin;
    ArrayList<Princi_Adminlist_Adapter> list;
    Context context;
    ImageButton ib_edit_AdminList,ib_delete_AdminList;
    F_ClasstList_princi classtList_princi;



    public RecyclerViewForAdminInfo(Context context, ArrayList<Princi_Adminlist_Adapter> arrayList) {
        this.list = arrayList;
        this.context = context;



    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView view = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_princi_card_admin, viewGroup, false);
        ib_edit_AdminList=view.findViewById( R.id.ib_edit_AdminList );


        return new ViewHolder(view);
    }



    @Override

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final int j;
        final CardView cardView = viewHolder.cardView;


        tv_card_name_admin= cardView.findViewById(R.id.tv_card_name_admin);
        iv_card_photo_admin=cardView.findViewById( R.id.iv_card_photo_admin );
        tv_card_name_admin.setText(list.get(i).getName().toString());

        Glide.with( context ).load( list.get( i ).getImage().toString()).into(iv_card_photo_admin  );



        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              /*  Intent intent = new Intent(context, F_ClasstList_princi.class);
                intent.putExtra("AdminID",list.get(i).getUserid());
                Log.e("AdminID",list.get(i).getUserid());
                context.startActivity(intent);*/


                Bundle bundle3=new Bundle();
                bundle3.putString("AdminID",list.get( i ).getUserid());
                //classtList_princi.setArguments(bundle3);
                Log.e("AdminID",list.get( i ).getUserid());

                Fragment fragment = new F_ClasstList_princi();
                //FragmentManager fragmentManager = getFragmentManager();;
                fragment.setArguments( bundle3 );
                FragmentManager fragmentManager =  ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_princi, fragment);
                fragmentTransaction.addToBackStack(null);
               // fragmentTransaction.commit();*/

                fragmentTransaction.commit();


            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.cardview_forAdmin);

        }
    }

    @Override
    public int getItemCount() {
        int len = list.size();
        return len;
    }

}
