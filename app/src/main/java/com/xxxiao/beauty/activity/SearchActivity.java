package com.xxxiao.beauty.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.xxxiao.beauty.R;
import com.xxxiao.beauty.base.BaseActivity;
import com.xxxiao.beauty.constant.API;
import com.xxxiao.beauty.fragment.MainFragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchActivity extends BaseActivity {

    private FragmentManager mFragmentManager;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                try {
                    String keyword = URLEncoder.encode(query, "UTF-8");
                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.container, MainFragment.newInstance(API.SEARCH + keyword))
                            .commit();
                    return true;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}
