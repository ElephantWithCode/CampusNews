package com.baibian.activity;

import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.baibian.R;
import com.baibian.fragment.users_imformation.HisPresentationFragment;
import com.baibian.fragment.users_imformation.TopicFragment;
import com.baibian.fragment.users_imformation.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class HisFocusActivity extends AppCompatActivity{

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FocusUserContentPagerAdapter mPagerAdapter;
    private Toolbar mToolbar;

    private List<String> mTabIndicators;
    private List<Fragment> mFragments;

    private Fragment mTestFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_focus);

        initViews();

        initToolbar();

        initIndicators();

        initFragments();

        initTabLayoutWithViewPager();
    }

    private void initViews() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("他的关注");
        }
    }

    private void initTabLayoutWithViewPager() {
        mPagerAdapter = new FocusUserContentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.gray_dark), ContextCompat.getColor(this, R.color.colorAccent));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(HisPresentationFragment.newInstance(1));
        mFragments.add(TopicFragment.newInstance(1));
        mFragments.add(UserFragment.newInstance(1));
    }

    private void initIndicators() {
        mTabIndicators = new ArrayList<>();
        mTabIndicators.add("His Presentation");
        mTabIndicators.add("His Topics");
        mTabIndicators.add("His Users");
    }


    class FocusUserContentPagerAdapter extends FragmentPagerAdapter{

        public FocusUserContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabIndicators.get(position);
        }

        @Override
        public int getCount() {
            return mTabIndicators.size();
        }
    }
}
