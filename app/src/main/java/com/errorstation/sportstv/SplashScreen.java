package com.errorstation.sportstv;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_splash_screen);
    Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);
    ImageView imageView = (ImageView) findViewById(R.id.splashLogo);
    imageView.startAnimation(anim);
    anim.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {

      }

      @Override public void onAnimationEnd(Animation animation) {
        API.Factory.getInstance().getTVChannels().enqueue(new Callback<TVChannel>() {
          @Override public void onResponse(Call<TVChannel> call, Response<TVChannel> response) {

            String liveStatus = response.body().getLivestatus();
            String startTime = response.body().getStarttime();
            String channel1 = response.body().getChannels().get(0).getUrl();
            String channel2 = response.body().getChannels().get(1).getUrl();
            String c1Logo = response.body().getChannels().get(0).getLogo();
            String c2Logo = response.body().getChannels().get(1).getLogo();
            String c1Name = response.body().getChannels().get(0).getChannelname();
            String c2Name = response.body().getChannels().get(1).getChannelname();

            SharedPreferences sharedPref =
                getSharedPreferences("channelList", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("channel1", channel1);
            editor.putString("channel2", channel2);
            editor.putString("liveStatus", liveStatus);
            editor.putString("startTime", startTime);
            editor.putString("c1Logo", c1Logo);
            editor.putString("c2Logo", c2Logo);
            editor.putString("c1Name", c1Name);
            editor.putString("c2Name", c2Name);
            editor.commit();

            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
          }

          @Override public void onFailure(Call<TVChannel> call, Throwable t) {

          }
        });
      }

      @Override public void onAnimationRepeat(Animation animation) {

      }
    });
  }
}
