package com.vidhya.vidyaacademy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wowza.gocoder.sdk.api.player.WOWZPlayerConfig;
import com.wowza.gocoder.sdk.api.player.WOWZPlayerView;
import com.wowza.gocoder.sdk.api.status.WOWZStatus;
import com.wowza.gocoder.sdk.api.status.WOWZStatusCallback;

public class LiveStreaming extends AppCompatActivity {
WOWZPlayerView mStreamPlayerView;
WOWZPlayerConfig mStreamPlayerConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_streaming);

        mStreamPlayerView = (WOWZPlayerView) findViewById(R.id.vwStreamPlayer);
        mStreamPlayerConfig = new WOWZPlayerConfig();
        mStreamPlayerConfig.setIsPlayback(true);
        mStreamPlayerConfig.setHostAddress("rtsp://7c2fec.entrypoint.cloud.wowza.com/app-7a3d");
        mStreamPlayerConfig.setApplicationName("Vidya Live Stream");
        mStreamPlayerConfig.setStreamName("310d994c");
        mStreamPlayerConfig.setPortNumber(1935);
        WOWZStatusCallback statusCallback = new StatusCallback();
        mStreamPlayerView.play(mStreamPlayerConfig, statusCallback);

    }
    class StatusCallback implements WOWZStatusCallback {
        @Override
        public void onWZStatus(WOWZStatus wzStatus) {
        }
        @Override
        public void onWZError(WOWZStatus wzStatus) {
        }
    }




}
