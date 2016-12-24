package com.errorstation.sportstv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

/**
 * Created by Rubayet on 16-Dec-16.
 */

public class LiveTVFragment extends Fragment {
  View view;
  LinearLayout channel1LL, channel2LL;
  String liveStatus,c1Logo,c2Logo,c1Name,c2Name;
  FrameLayout msgFL;
  TextView msgTV,team1TV,score1TV,over1TV,team2TV,score2TV,over2TV,live1TV,live2TV,channel1TV,channel2TV,vsTV;
  Typeface typeFace;
  ImageView chLogo2IMGV ,chLogo1IMGV;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_live_tv, container, false);
    channel1LL = (LinearLayout) view.findViewById(R.id.channel1LL);
    channel2LL = (LinearLayout) view.findViewById(R.id.channel2LL);
    msgFL = (FrameLayout) view.findViewById(R.id.msgFL);
    msgTV = (TextView) view.findViewById(R.id.msgTV);

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

    liveStatus = sharedPref.getString("liveStatus", "");
    c1Logo = sharedPref.getString("c1Logo", "");
    c2Logo = sharedPref.getString("c2Logo", "");
    c1Name = sharedPref.getString("c1Name", "");
    c2Name = sharedPref.getString("c2Name", "");

    Glide
        .with(getActivity())
        .load(c1Logo)
        .placeholder(R.drawable.ic_tv_channel_icon)
        .crossFade()
        .into(chLogo1IMGV);

    Glide
        .with(getActivity())
        .load(c2Logo)
        .placeholder(R.drawable.ic_tv_channel_icon)
        .crossFade()
        .into(chLogo2IMGV);

    channel1TV.setText(c1Name);
    channel2TV.setText(c2Name);






    if(liveStatus.equals("0"))
    {
      msgFL.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_home_message_red));
      msgTV.setText(getActivity().getResources().getString(R.string.noLive));
      live1TV.setVisibility(View.INVISIBLE);
      live2TV.setVisibility(View.INVISIBLE);
    }
    else if(liveStatus.equals("1"))
    {
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

  private void goToPlayer(int i, Activity activity) {

    Intent intent = new Intent(activity, TVPlayerActivity.class);
    intent.putExtra("channel", i);
    activity.startActivity(intent);
  }
}
