package com.errorstation.sportstv;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Toolbar mainTB;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView headingTV;
    Typeface typeFace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializer();
        changeTabsFont();
    }

    private void initializer() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        mainTB = (Toolbar) findViewById(R.id.mainTB);
        setSupportActionBar(mainTB);
        getSupportActionBar().setTitle("");
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        headingTV = (TextView) findViewById(R.id.headingTV);
        typeFace = Typeface.createFromAsset(getAssets(), "Siyamrupali.ttf");
        headingTV.setTypeface(typeFace);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LiveTVFragment(), getResources().getString(R.string.live));
        adapter.addFragment(new ScheduleFragment(), getResources().getString(R.string.schedule));
        adapter.addFragment(new NewsFragment(), getResources().getString(R.string.news));
        viewPager.setAdapter(adapter);
    }

    private void changeTabsFont() {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(typeFace);
                }
            }
        }
    }
}
