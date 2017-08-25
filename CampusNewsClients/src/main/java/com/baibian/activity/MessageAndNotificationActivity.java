package com.baibian.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.baibian.R;

import java.util.ArrayList;
import java.util.List;

public class MessageAndNotificationActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private ViewPager mViewPager;

    private List<String> mIndicatorTexts;
    private List<Fragment> mFragments;
    private FragmentPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_and_notification);

        mTabLayout = (TabLayout) findViewById(R.id.messy_fiction_tab);
        mViewPager = (ViewPager) findViewById(R.id.messy_fiction_view_pager);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);

        mIndicatorTexts = new ArrayList<>();
        mFragments = new ArrayList<>();
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mIndicatorTexts.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        
    }
}
