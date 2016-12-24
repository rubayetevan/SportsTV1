package com.errorstation.sportstv;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class ScheduleFragment  extends Fragment{

    View view;
    TextView schLoadingTV;
    Typeface typeFace;
    List<Matchinfo> schedulesList = new ArrayList<Matchinfo>();
    RecyclerView schRV;
    ProgressBar schPB;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_schedule, container, false);
      schRV = (RecyclerView) view.findViewById(R.id.schedulesRV);
      schPB = (ProgressBar) view.findViewById(R.id.schedulesPB);
      schLoadingTV = (TextView) view.findViewById(R.id.schedulesLoadingTV);
        return view;
    }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    typeFace = Typeface.createFromAsset(getActivity().getAssets(), "Siyamrupali.ttf");
    schPB.setVisibility(View.VISIBLE);
    schLoadingTV.setVisibility(View.VISIBLE);
    API.Factory.getInstance().getSchedules().enqueue(new Callback<CricketSchedules>() {
      @Override
      public void onResponse(Call<CricketSchedules> call, Response<CricketSchedules> response) {
        String Success = response.body().getSuccess();
        schedulesList = response.body().getMatchinfo();
        SchedulesAdapter adapter = new SchedulesAdapter(getActivity(),schedulesList,getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        schRV.setLayoutManager(layoutManager);
        schPB.setVisibility(View.GONE);
        schLoadingTV.setVisibility(View.GONE);
        schRV.setAdapter(adapter);
      }

      @Override public void onFailure(Call<CricketSchedules> call, Throwable t) {
        schPB.setVisibility(View.GONE);
        schLoadingTV.setVisibility(View.GONE);
      }
    });
  }
}
