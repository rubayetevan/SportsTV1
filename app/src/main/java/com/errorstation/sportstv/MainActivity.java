package com.errorstation.sportstv;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    Toolbar mainTB;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        mainTB = (Toolbar) findViewById(R.id.mainTB);
        setSupportActionBar(mainTB);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LiveTVFragment(), getResources().getString(R.string.live));
        adapter.addFragment(new ScheduleFragment(), getResources().getString(R.string.schedule));
        adapter.addFragment(new NewsFragment(), getResources().getString(R.string.news));
        viewPager.setAdapter(adapter);
    }
}
