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

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_news, container, false);
    newsRV = (RecyclerView) view.findViewById(R.id.newsRV);
    newsPB = (ProgressBar) view.findViewById(R.id.newsPB);
    newsLoadingTV = (TextView) view.findViewById(R.id.newsLoadingTV);
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    typeFace = Typeface.createFromAsset(getActivity().getAssets(), "Siyamrupali.ttf");
    newsPB.setVisibility(View.VISIBLE);
    newsLoadingTV.setVisibility(View.VISIBLE);
    API.Factory.getInstance().getNews().enqueue(new Callback<NewsModel>() {
      @Override public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
        String Success = response.body().getSuccess();
        newsList = response.body().getNewsfeed();
        Log.d("NewsFragment", "newsList: "+String.valueOf(newsList.size()));
        NewsAdapter newsAdapter = new NewsAdapter(getActivity(), newsList, getActivity());
        LinearLayoutManager  layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        newsRV.setLayoutManager(layoutManager);
        newsPB.setVisibility(View.GONE);
        newsLoadingTV.setVisibility(View.GONE);
        newsRV.setAdapter(newsAdapter);
      }

      @Override public void onFailure(Call<NewsModel> call, Throwable t) {
        newsPB.setVisibility(View.GONE);
        newsLoadingTV.setVisibility(View.GONE);
      }
    });
  }
}
