package com.errorstation.sportstv;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import tcking.github.com.giraffeplayer.GiraffePlayer;

public class TVPlayerActivity extends AppCompatActivity {
  String channelURL;
  GiraffePlayer player;
  LinearLayout tvLayout;
  NativeExpressAdView mAdView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tvplayer);
    //tvLayout = (LinearLayout) findViewById(R.id.tvLayout);
    mAdView = (NativeExpressAdView) findViewById(R.id.adViewF4);
    Intent intent = getIntent();
    int channelCode = intent.getIntExtra("channel", 0);
    SharedPreferences sharedPref = getSharedPreferences("channelList", Context.MODE_PRIVATE);
    if (channelCode == 1) {
      channelURL = sharedPref.getString("channel1", "");
    } else if (channelCode == 2) {
      channelURL = sharedPref.getString("channel2", "");
    }
    MobileAds.initialize(this, "ca-app-pub-4958954259926855~7413974922");

    AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        .addTestDevice("EB7E6FA39C4BDD75B5A17F5285A52364")
        .build();
    mAdView.loadAd(adRequest);

    Log.d("SplashScreen", String.valueOf(channelCode));
    Log.d("SplashScreen", String.valueOf(channelURL));
    player = new GiraffePlayer(this);
    player.play(channelURL);
  }
  @Override
  protected void onPause() {
    super.onPause();
    if (player != null) {
      player.onPause();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (player != null) {
      player.onResume();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (player != null) {
      player.onDestroy();
    }
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    if (player != null) {
      player.onConfigurationChanged(newConfig);
    }
  }

  @Override
  public void onBackPressed() {
    if (player != null && player.onBackPressed()) {
      return;
    }
    super.onBackPressed();
  }
}
