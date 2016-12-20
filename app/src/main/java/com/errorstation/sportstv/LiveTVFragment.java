package com.errorstation.sportstv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Rubayet on 16-Dec-16.
 */

public class LiveTVFragment extends Fragment {
    View view;
    LinearLayout channel1LL,channel2LL;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_live_tv, container, false);
        channel1LL = (LinearLayout) view.findViewById(R.id.channel1LL);
        channel2LL = (LinearLayout) view.findViewById(R.id.channel2LL);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        channel1LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPlayer(1,getActivity());
            }
        });
        channel2LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPlayer(2,getActivity());
            }
        });
    }

    private void goToPlayer(int i, Activity activity) {

        Intent intent = new Intent(activity,TVPlayerActivity.class);
        intent.putExtra("channel",i);
        activity.startActivity(intent);

    }
}
