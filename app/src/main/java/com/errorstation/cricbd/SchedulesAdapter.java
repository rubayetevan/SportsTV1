package com.errorstation.cricbd;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rubayet on 24-Dec-16.
 */

public class SchedulesAdapter extends RecyclerView.Adapter<SchedulesAdapter.SchedulesHolder> {

  Context context;
  List<Matchinfo> schedulesList = new ArrayList<Matchinfo>();
  Activity activity;
  Typeface typeFace;

  public SchedulesAdapter(Context context, List<Matchinfo> schedulesList, Activity activity) {
    this.context = context;
    this.schedulesList = schedulesList;
    this.activity = activity;
    typeFace = Typeface.createFromAsset(context.getAssets(), "Siyamrupali.ttf");
  }

  @Override public SchedulesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.schedules_list, parent, false);
    return new SchedulesAdapter.SchedulesHolder(itemView);
  }

  @Override public void onBindViewHolder(SchedulesHolder holder, int position) {
    holder.teamsTV.setTypeface(typeFace);
    holder.matchTV.setTypeface(typeFace);
    holder.placeTV.setTypeface(typeFace);
    holder.timeTV.setTypeface(typeFace);



    holder.teamsTV.setText(schedulesList.get(position).getTeam1()+" বনাম "+schedulesList.get(position).getTeam2());
    holder.matchTV.setText(schedulesList.get(position).getMatchno()+" খেলা, "+schedulesList.get(position).getMatchtype()+", " +schedulesList.get(position).getStartdate());
    holder.placeTV.setText(schedulesList.get(position).getVenue());
    holder.timeTV.setText(schedulesList.get(position).getStarttime());
    Glide
        .with(context)
        .load(schedulesList.get(position).getTeam1logo())
        .placeholder(R.drawable.ic_tv_channel_icon)
        .crossFade()
        .into(holder.team1LogoIMGV);
    Glide
        .with(context)
        .load(schedulesList.get(position).getTeam2logo())
        .placeholder(R.drawable.ic_tv_channel_icon)
        .crossFade()
        .into(holder.team2LogoIMGV);




  }

  @Override public int getItemCount() {
    return schedulesList.size();
  }

  public class SchedulesHolder extends RecyclerView.ViewHolder {

    public ImageView team1LogoIMGV;
    public ImageView team2LogoIMGV;
    public TextView teamsTV;
    public TextView matchTV;
    public TextView placeTV;
    public TextView timeTV;

    public SchedulesHolder(View itemView) {
      super(itemView);
      team1LogoIMGV = (ImageView) itemView.findViewById(R.id.team1LogoIMGV);
      team2LogoIMGV = (ImageView) itemView.findViewById(R.id.team2LogoIMGV);
      teamsTV = (TextView) itemView.findViewById(R.id.teamsTV);
      matchTV = (TextView) itemView.findViewById(R.id.matchTV);
      placeTV = (TextView) itemView.findViewById(R.id.placeTV);
      timeTV = (TextView) itemView.findViewById(R.id.timeTV);
    }
  }
}
