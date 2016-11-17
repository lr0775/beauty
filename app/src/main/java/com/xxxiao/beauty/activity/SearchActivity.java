package com.xxxiao.beauty.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.xxxiao.beauty.R;
import com.xxxiao.beauty.base.BaseActivity;
import com.xxxiao.beauty.constant.API;
import com.xxxiao.beauty.fragment.MainFragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchActivity extends BaseActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mFragmentManager = getSupportFragmentManager();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final SearchView searchView = (SearchView) toolbar.findViewById(R.id.search_view);
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.onActionViewCollapsed();
                try {
                    String keyword = URLEncoder.encode(query, "UTF-8");
                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.container, MainFragment.newInstance(API.SEARCH + keyword))
                            .commit();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

}
