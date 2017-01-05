package com.errorstation.cricbd;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

public class NewsActivity extends AppCompatActivity {
  TextView newsDheadingTV, newsDsourceTV, newsDDesTV;
  ImageView newsDIMGV,backIMGV;
  NativeExpressAdView mAdView,mAdView1;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news);
    newsDheadingTV = (TextView) findViewById(R.id.newsDheadingTV);
    newsDsourceTV = (TextView) findViewById(R.id.newsDsourceTV);
    newsDDesTV = (TextView) findViewById(R.id.newsDDesTV);
    newsDIMGV = (ImageView) findViewById(R.id.newsDIMGV);
    backIMGV = (ImageView) findViewById(R.id.backIMGV);


    mAdView = (NativeExpressAdView)findViewById(R.id.adViewF1);
    mAdView1= (NativeExpressAdView)findViewById(R.id.adViewF2);
    AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        .addTestDevice("EB7E6FA39C4BDD75B5A17F5285A52364")
        .build();
    mAdView.loadAd(adRequest);
    mAdView1.loadAd(adRequest);

    backIMGV.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        onBackPressed();
      }
    });
    Typeface typeFace = Typeface.createFromAsset(getAssets(), "Siyamrupali.ttf");
    newsDheadingTV.setTypeface(typeFace);
    newsDsourceTV.setTypeface(typeFace);
    newsDDesTV.setTypeface(typeFace);

    Intent intent = getIntent();
    String heading = intent.getStringExtra("heading");
    String source = intent.getStringExtra("source");
    String description = intent.getStringExtra("description");
    String imageLink = intent.getStringExtra("imageLink");

    Glide.with(this).load(imageLink).override(720, 400).into(newsDIMGV);

    newsDheadingTV.setText(heading);
    newsDsourceTV.setText(source);
    newsDDesTV.setText(description);
  }
}
