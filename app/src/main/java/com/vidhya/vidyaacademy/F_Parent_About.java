package com.vidhya.vidyaacademy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class F_Parent_About extends Fragment {

    ImageView about_parent_img;
    TextView about_parent;
    ImageButton about_parent_img_fb, about_parent_img_youtube, about_parent_img_web;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_parent_about, container, false );

        about_parent_img = view.findViewById( R.id.about_parent_img );
        about_parent = view.findViewById( R.id.about_parent );
        about_parent_img_fb = view.findViewById( R.id.about_parent_img_fb );
        about_parent_img_youtube = view.findViewById( R.id.about_parent_img_youtube );
        about_parent_img_web = view.findViewById( R.id.about_parent_img_web );


        about_parent_img_fb.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facebookPageUrl = "https://www.facebook.com/VidhyaIAS/?ref=page_internal";
                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( facebookPageUrl ) );
                startActivity( intent );

            }
        } );

        about_parent_img_youtube.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse( "https://www.youtube.com/channel/UCH-xAllACDaIJSX7WXaHX4Q" ) ) );

            }
        } );

        about_parent_img_web.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse( "http://www.vidhyastudies.com/?fbclid=IwAR37HWhH_tCq9Yul8hd64MUdp_XRJSTqM-O6QBl1qc5nWUl8MLo89h4YXWw" );
                Intent webIntent = new Intent( Intent.ACTION_VIEW, webpage );
                startActivity( webIntent );
            }

        } );
        return view;
    }

}



