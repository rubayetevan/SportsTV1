package com.errorstation.tigerslive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Rubayet on 16-Dec-16.
 */

public class LiveTVFragment extends Fragment {
  View view;
  LinearLayout channel1LL, channel2LL;
  String liveStatus, c1Logo, c2Logo, c1Name, c2Name;
  FrameLayout msgFL;
  TextView msgTV, team1TV, score1TV, over1TV, team2TV, score2TV, over2TV, live1TV, live2TV,
      channel1TV, channel2TV, vsTV;
  Typeface typeFace;
  ImageView chLogo2IMGV, chLogo1IMGV, team1IMGV, team2IMGV;
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  ScheduledFuture<?> beeperHandle;
  CardView scoreCRDV;
  NativeExpressAdView mAdView;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_live_tv, container, false);
    channel1LL = (LinearLayout) view.findViewById(R.id.channel1LL);
    channel2LL = (LinearLayout) view.findViewById(R.id.channel2LL);
    msgFL = (FrameLayout) view.findViewById(R.id.msgFL);
    msgTV = (TextView) view.findViewById(R.id.msgTV);

    scoreCRDV = (CardView) view.findViewById(R.id.scoreCRDV);

    team1TV = (TextView) view.findViewById(R.id.team1TV);
    score1TV = (TextView) view.findViewById(R.id.score1TV);
    over1TV = (TextView) view.findViewById(R.id.over1TV);
    team2TV = (TextView) view.findViewById(R.id.team2TV);
    score2TV = (TextView) view.findViewById(R.id.score2TV);
    over2TV = (TextView) view.findViewById(R.id.over2TV);
    live1TV = (TextView) view.findViewById(R.id.live1TV);
    live2TV = (TextView) view.findViewById(R.id.live2TV);
    channel1TV = (TextView) view.findViewById(R.id.channel1TV);
    channel2TV = (TextView) view.findViewById(R.id.channel2TV);
    vsTV = (TextView) view.findViewById(R.id.vsTV);
    chLogo2IMGV = (ImageView) view.findViewById(R.id.chLogo2IMGV);
    chLogo1IMGV = (ImageView) view.findViewById(R.id.chLogo1IMGV);
    mAdView = (NativeExpressAdView) view.findViewById(R.id.adViewF3);
    team1IMGV = (ImageView) view.findViewById(R.id.team1IMGV);
    team2IMGV = (ImageView) view.findViewById(R.id.team2IMGV);
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    SharedPreferences sharedPref =
        getActivity().getSharedPreferences("channelList", Context.MODE_PRIVATE);
    typeFace = Typeface.createFromAsset(getActivity().getAssets(), "Siyamrupali.ttf");
    msgTV.setTypeface(typeFace);

    team1TV.setTypeface(typeFace);
    score1TV.setTypeface(typeFace);
    over1TV.setTypeface(typeFace);
    team2TV.setTypeface(typeFace);
    score2TV.setTypeface(typeFace);
    over2TV.setTypeface(typeFace);
    live1TV.setTypeface(typeFace);
    live2TV.setTypeface(typeFace);
    channel1TV.setTypeface(typeFace);
    channel2TV.setTypeface(typeFace);
    vsTV.setTypeface(typeFace);
    scoreCRDV.setVisibility(View.GONE);
    liveStatus = sharedPref.getString("liveStatus", "");
    c1Logo = sharedPref.getString("c1Logo", "");
    c2Logo = sharedPref.getString("c2Logo", "");
    c1Name = sharedPref.getString("c1Name", "");
    c2Name = sharedPref.getString("c2Name", "");


    MobileAds.initialize(getActivity(), "ca-app-pub-4958954259926855~7413974922");

    AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        .addTestDevice("EB7E6FA39C4BDD75B5A17F5285A52364")
        .build();
    mAdView.loadAd(adRequest);

    showLogo(chLogo1IMGV, c1Logo);
    showLogo(chLogo2IMGV, c2Logo);

    channel1TV.setText(c1Name);
    channel2TV.setText(c2Name);


    if (liveStatus.equals("0")) {
      scoreCRDV.setVisibility(View.GONE);
      stopShowingScores();
      msgFL.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_home_message_red));
      msgTV.setText(getActivity().getResources().getString(R.string.noLive));
      live1TV.setVisibility(View.INVISIBLE);
      live2TV.setVisibility(View.INVISIBLE);

    } else if (liveStatus.equals("1")) {
      scoreCRDV.setVisibility(View.VISIBLE);
      stopShowingScores();
      showScores();
      msgFL.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_home_message_green));
      msgTV.setText(getActivity().getResources().getString(R.string.liveOn));
      live1TV.setVisibility(View.VISIBLE);
      live2TV.setVisibility(View.VISIBLE);
    }

    channel1LL.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        goToPlayer(1, getActivity());
      }
    });

    channel2LL.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        goToPlayer(2, getActivity());
      }
    });
  }

  private void showLogo(ImageView ImageView, String URL) {
    Glide.with(getActivity())
        .load(URL)
        .placeholder(R.drawable.ic_tv_channel_icon)
        .crossFade()
        .into(ImageView);
  }

  private void goToPlayer(int i, Activity activity) {

    Intent intent = new Intent(activity, TVPlayerActivity.class);
    intent.putExtra("channel", i);
    activity.startActivity(intent);
  }

  @Override public void onPause() {
    stopShowingScores();
    super.onPause();
  }

  @Override public void onResume() {
    super.onResume();
    stopShowingScores();
    if(liveStatus.equals("1")) {
      showScores();
    }
  }

  public void showScores() {
    final Runnable beeper = new Runnable() {
      public void run() {

        API.Factory.getInstance().getScores().enqueue(new Callback<LiveScores>() {
          @Override public void onResponse(Call<LiveScores> call, Response<LiveScores> response) {
            String success = response.body().getSuccess();

            String team1 = response.body().getTeam1();
            String team2 = response.body().getTeam2();

            String team1logo = response.body().getScores().get(0).getTeam1logo();
            String team1runs = response.body().getScores().get(0).getTeam1runs();
            String team1wickets = response.body().getScores().get(0).getTeam1wickets();
            String team1overs = response.body().getScores().get(0).getTeam1overs();

            String team2logo = response.body().getScores().get(0).getTeam2logo();
            String team2runs = response.body().getScores().get(0).getTeam2runs();
            String team2wickets = response.body().getScores().get(0).getTeam2wickets();
            String team2overs = response.body().getScores().get(0).getTeam2overs();

            showLogo(team1IMGV,team1logo);
            team1TV.setText(team1);
            score1TV.setText(team1runs+"/"+team1wickets);
            over1TV.setText("ওভ: "+team1overs);
            showLogo(team2IMGV,team2logo);
            team2TV.setText(team2);
            score2TV.setText(team2runs+"/"+team2wickets);
            over2TV.setText("ওভ: "+team2overs);
          }

          @Override public void onFailure(Call<LiveScores> call, Throwable t) {

          }
        });
      }
    };
    beeperHandle = scheduler.scheduleAtFixedRate(beeper, 0, 120, SECONDS);
  }

  public void stopShowingScores() {
    if (beeperHandle != null) {
      beeperHandle.cancel(true);
    }
  }
}
