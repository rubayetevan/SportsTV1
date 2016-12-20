package com.errorstation.sportstv;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import tcking.github.com.giraffeplayer.GiraffePlayer;

public class TVPlayerActivity extends AppCompatActivity {
    String channelURL;
    GiraffePlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvplayer);

        Intent intent = getIntent();
        int channelCode = intent.getIntExtra("channel",0);
        SharedPreferences sharedPref = getSharedPreferences(
                "channelList", Context.MODE_PRIVATE);
        if (channelCode==1)
        {
             channelURL = sharedPref.getString("channel1","");
        }
        else if(channelCode==2)
        {
            channelURL = sharedPref.getString("channel2","");
        }
        Log.d("SplashScreen",String.valueOf(channelCode));
        Log.d("SplashScreen",String.valueOf(channelURL));
        player = new GiraffePlayer(this);
        player.play(channelURL);
    }
    @Override
    protected void onPause() {
        if (this.player != null) {
            this.player.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (this.player != null) {
            this.player.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.player != null) {
            this.player.onResume();
        }
    }
    @Override
    public void onBackPressed() {
        if (this.player == null || !this.player.onBackPressed()) {
            super.onBackPressed();
        }
    }
    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            player.setScaleType(GiraffePlayer.SCALETYPE_FITPARENT);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400));
            player.setScaleType(GiraffePlayer.SCALETYPE_FITPARENT);
        }


    }*/
}
