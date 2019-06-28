package com.vidya.vidyaacademy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wowza.gocoder.sdk.api.WowzaGoCoder;
import com.wowza.gocoder.sdk.api.player.WOWZPlayerConfig;
import com.wowza.gocoder.sdk.api.player.WOWZPlayerView;
import com.wowza.gocoder.sdk.api.status.WOWZStatus;
import com.wowza.gocoder.sdk.api.status.WOWZStatusCallback;

public class LiveStreaming extends AppCompatActivity {
WOWZPlayerView mStreamPlayerView;
WOWZPlayerConfig mStreamPlayerConfig;
    private static final String SDK_SAMPLE_APP_LICENSE_KEY = "GOSK-8B46-010C-C885-FAA2-D611";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_streaming);
       WowzaGoCoder sGoCoderSDK = WowzaGoCoder.init(this, SDK_SAMPLE_APP_LICENSE_KEY);
       if (sGoCoderSDK!=null) {
           mStreamPlayerView = (WOWZPlayerView) findViewById(R.id.vwStreamPlayer);
           mStreamPlayerConfig = new WOWZPlayerConfig();

           mStreamPlayerConfig.setIsPlayback(true);
           mStreamPlayerConfig.setHostAddress("7c2fec.entrypoint.cloud.wowza.com");
           mStreamPlayerConfig.setApplicationName("app-7a3d");
           mStreamPlayerConfig.setStreamName("310d994c");
           mStreamPlayerConfig.setPortNumber(1935);
           mStreamPlayerConfig.setUsername("client43185");
           mStreamPlayerConfig.setPassword("eca5730c");
           // mStreamPlayerConfig.setHLSEnabled(true);
           mStreamPlayerConfig.setVideoEnabled(true);
           //  mStreamPlayerConfig.setHLSBackupURL("https://wowzaprod155-i.akamaihd.net/hls/live/824078/c3b20fb4/playlist.m3u8");

           WOWZStatusCallback statusCallback = new StatusCallback();
           mStreamPlayerView.play(mStreamPlayerConfig, statusCallback);
       }
    }
    class StatusCallback implements WOWZStatusCallback {
        @Override
        public void onWZStatus(WOWZStatus wzStatus) {
Log.e("Nadeem",wzStatus.toString());

        }
        @Override
        public void onWZError(WOWZStatus wzStatus) {
            Log.e("Nadeem",wzStatus.toString());
        }
    }




}
