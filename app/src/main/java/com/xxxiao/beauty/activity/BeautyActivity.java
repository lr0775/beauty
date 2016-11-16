package com.xxxiao.beauty.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.xxxiao.beauty.R;
import com.xxxiao.beauty.base.BaseActivity;
import com.xxxiao.beauty.constant.API;
import com.xxxiao.beauty.fragment.MainFragment;

public class BeautyActivity extends BaseActivity {

    private static final String[] CATEGORY = new String[]{
            "最新美女",
            "性感美女",
            "少女萝莉",
            "美乳香臀",
            "丝袜美腿",
            "唯美写真",
            "女神合集",
            "美女壁纸",
    };

    private static final String[] METHOD = new String[]{
            API.ZXMN,
            API.XGMN,
            API.SNLL,
            API.MRXT,
            API.SWMT,
            API.WMXZ,
            API.NSHJ,
            API.MNBZ,
    };

    private TabPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);

        mPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public class TabPagerAdapter extends FragmentPagerAdapter {

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MainFragment.newInstance(METHOD[position]);
        }

        @Override
        public int getCount() {
            return CATEGORY.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CATEGORY[position];
        }
    }
}
