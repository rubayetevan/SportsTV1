package com.errorstation.sportstv;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rubayet on 16-Dec-16.
 */

public class NewsFragment extends Fragment {
  View view;
  TextView newsLoadingTV;
  Typeface typeFace;
  List<Newsfeed> newsList = new ArrayList<Newsfeed>();
  RecyclerView newsRV;
  ProgressBar newsPB;
  AdView mAdView;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_news, container, false);
    newsRV = (RecyclerView) view.findViewById(R.id.newsRV);
    newsPB = (ProgressBar) view.findViewById(R.id.newsPB);
    mAdView = (AdView) view.findViewById(R.id.newsBannerADV);
    newsLoadingTV = (TextView) view.findViewById(R.id.newsLoadingTV);
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    typeFace = Typeface.createFromAsset(getActivity().getAssets(), "Siyamrupali.ttf");
    newsPB.setVisibility(View.VISIBLE);
    newsLoadingTV.setVisibility(View.VISIBLE);

    MobileAds.initialize(getActivity(), "ca-app-pub-4958954259926855~7413974922");

    AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        .addTestDevice("EB7E6FA39C4BDD75B5A17F5285A52364")
        .build();
    mAdView.loadAd(adRequest);

    API.Factory.getInstance().getNews().enqueue(new Callback<NewsModel>() {
      @Override public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
        String Success = response.body().getSuccess();
        newsList = response.body().getNewsfeed();
        Log.d("NewsFragment", "newsList: " + String.valueOf(newsList.size()));
        NewsAdapter newsAdapter = new NewsAdapter(getActivity(), newsList, getActivity());
        LinearLayoutManager layoutManager =
            new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        newsRV.setLayoutManager(layoutManager);
        newsPB.setVisibility(View.GONE);
        newsLoadingTV.setVisibility(View.GONE);
        newsRV.setAdapter(newsAdapter);
      }

      @Override public void onFailure(Call<NewsModel> call, Throwable t) {
        newsPB.setVisibility(View.GONE);
        newsLoadingTV.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "সার্ভার রক্ষনাবেক্ষনের কাজ চলছে!  সাময়িক অসুবিধার জন্য আমরা আন্তরিক ভাবে দুঃখিত।", Toast.LENGTH_LONG).show();
      }
    });
  }

  @Override public void onPause() {
    if (mAdView != null) {
      mAdView.pause();
    }
    super.onPause();
  }

  /** Called when returning to the activity */
  @Override public void onResume() {
    super.onResume();
    if (mAdView != null) {
      mAdView.resume();
    }
  }

  /** Called before the activity is destroyed */
  @Override public void onDestroy() {
    if (mAdView != null) {
      mAdView.destroy();
    }
    super.onDestroy();
  }
}
