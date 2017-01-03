package com.errorstation.tigerslive;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rubayet on 20-Dec-16.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
  Context context;
  List<Newsfeed> newsList = new ArrayList<Newsfeed>();
  Activity activity;
  Typeface typeFace;


  public NewsAdapter(Context context, List<Newsfeed> newsList,Activity activity) {
    this.context = context;
    this.newsList = newsList;
    this.activity =activity;
    typeFace = Typeface.createFromAsset(context.getAssets(), "Siyamrupali.ttf");
  }

  public class NewsHolder extends RecyclerView.ViewHolder {

    public ImageView newsListIMGV;
    public TextView headingTV;
    public TextView sourceTV;
    public TextView smallDesTV;
    public FrameLayout newsListCard;

    public NewsHolder(View itemView) {
      super(itemView);
      newsListIMGV = (ImageView) itemView.findViewById(R.id.newsListIMGV);
      headingTV = (TextView) itemView.findViewById(R.id.headingTV);
      sourceTV = (TextView) itemView.findViewById(R.id.sourceTV);
      smallDesTV = (TextView) itemView.findViewById(R.id.smallDesTV);
      newsListCard = (FrameLayout) itemView.findViewById(R.id.newsListCard);
    }
  }

  @Override
  public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.news_list, parent, false);
    return new NewsHolder(itemView);
  }

  @Override
  public void onBindViewHolder(final NewsHolder holder, final int position) {

    holder.headingTV.setTypeface(typeFace);
    holder.sourceTV.setTypeface(typeFace);
    holder.smallDesTV.setTypeface(typeFace);

    holder.headingTV.setText(Html.fromHtml(newsList.get(position).getHeadline()));
    holder.sourceTV.setText(context.getResources().getString(R.string.somoy)+" "+Html.fromHtml(newsList.get(position).getCreatedon())+" / "+context.getResources().getString(R.string.sutro)+" "+Html.fromHtml(newsList.get(position).getSource()));
    holder.smallDesTV.setText(getFirst10Words(String.valueOf(Html.fromHtml(newsList.get(position).getDescription()))));
    Glide.with(context)
        .load(newsList.get(position).getImagelink())
        .override(720, 230)
        .into(holder.newsListIMGV);

    holder.newsListCard.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(activity,NewsActivity.class);
        intent.putExtra("heading",newsList.get(position).getHeadline());
        intent.putExtra("source",context.getResources().getString(R.string.somoy)+" "+Html.fromHtml(newsList.get(position).getCreatedon())+" / "+context.getResources().getString(R.string.sutro)+" "+Html.fromHtml(newsList.get(position).getSource()));
        intent.putExtra("description",newsList.get(position).getDescription());
        intent.putExtra("imageLink",newsList.get(position).getImagelink());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          Bundle bundle1 = ActivityOptions.makeSceneTransitionAnimation(activity, holder.newsListCard, holder.newsListCard
              .getTransitionName()).toBundle();
          context.startActivity(intent, bundle1);
        } else {
          context.startActivity(intent);
        }
      }
    });

  }

  @Override
  public int getItemCount() {
    return newsList.size();
  }

  public String getFirst10Words(String arg) {
    Pattern pattern = Pattern.compile("([\\S]+\\s*){1,14}");
    Matcher matcher = pattern.matcher(arg);
    matcher.find();
    return matcher.group()+"....";
  }

}
