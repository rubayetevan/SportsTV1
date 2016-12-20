package com.errorstation.sportstv;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class NewsActivity extends AppCompatActivity {
  TextView newsDheadingTV, newsDsourceTV, newsDDesTV;
  ImageView newsDIMGV;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news);
    newsDheadingTV = (TextView) findViewById(R.id.newsDheadingTV);
    newsDsourceTV = (TextView) findViewById(R.id.newsDsourceTV);
    newsDDesTV = (TextView) findViewById(R.id.newsDDesTV);
    newsDIMGV = (ImageView) findViewById(R.id.newsDIMGV);
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
