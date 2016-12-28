package com.errorstation.sportstv;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import tcking.github.com.giraffeplayer.GiraffePlayer;

public class TVPlayerActivity extends AppCompatActivity {
  String channelURL, liveStatus;
  GiraffePlayer player;
  LinearLayout tvLayout;
  NativeExpressAdView mAdView;
  TextView liveTV, teamsTV, matchTV, placeTV;
  Typeface typeFace;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tvplayer);
    //tvLayout = (LinearLayout) findViewById(R.id.tvLayout);
    mAdView = (NativeExpressAdView) findViewById(R.id.adViewF4);

    liveTV = (TextView) findViewById(R.id.liveTV);
    teamsTV = (TextView) findViewById(R.id.teamsTV);
    matchTV = (TextView) findViewById(R.id.matchTV);
    placeTV = (TextView) findViewById(R.id.placeTV);

    typeFace = Typeface.createFromAsset(getAssets(), "Siyamrupali.ttf");

    liveTV.setTypeface(typeFace);
    teamsTV.setTypeface(typeFace);
    matchTV.setTypeface(typeFace);
    placeTV.setTypeface(typeFace);

    Intent intent = getIntent();
    int channelCode = intent.getIntExtra("channel", 0);
    SharedPreferences sharedPref = getSharedPreferences("channelList", Context.MODE_PRIVATE);
    if (channelCode == 1) {
      channelURL = sharedPref.getString("channel1", "");
    } else if (channelCode == 2) {
      channelURL = sharedPref.getString("channel2", "");
    }

    liveStatus = sharedPref.getString("liveStatus", "");



    if (liveStatus.equals("0")) {
      liveTV.setVisibility(View.GONE);
      teamsTV.setVisibility(View.GONE);
      matchTV.setVisibility(View.GONE);
      placeTV.setVisibility(View.GONE);


    } else if (liveStatus.equals("1")) {
      String team1="",team2="",matchNo="",place="",matchType="";
      team1 = sharedPref.getString("team1", "");
      team2 = sharedPref.getString("team2", "");
      matchNo = sharedPref.getString("matchNo", "");
      place = sharedPref.getString("place", "");
      matchType = sharedPref.getString("matchType", "");


      liveTV.setVisibility(View.VISIBLE);
      teamsTV.setVisibility(View.VISIBLE);
      teamsTV.setText(team1+" বনাম "+team2);
      matchTV.setVisibility(View.VISIBLE);
      matchTV.setText(matchNo+" খেলা, "+matchType);
      placeTV.setVisibility(View.VISIBLE);
      placeTV.setText(place);

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

  @Override protected void onPause() {
    super.onPause();
    if (player != null) {
      player.onPause();
    }
  }

  @Override protected void onResume() {
    super.onResume();
    if (player != null) {
      player.onResume();
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (player != null) {
      player.onDestroy();
    }
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    if (player != null) {
      player.onConfigurationChanged(newConfig);
    }
  }

  @Override public void onBackPressed() {
    if (player != null && player.onBackPressed()) {
      return;
    }
    super.onBackPressed();
  }
}
