package com.aesean.gankio;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.aesean.gankio.api.GankRequest;
import com.aesean.gankio.base.AppBaseActivity;
import com.aesean.gankio.gank.GankFragment;

/**
 * 项目入口
 */
public class MainActivity extends AppBaseActivity {
    PagerAdapter mPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount());
        mViewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            SearchView searchView = (SearchView) searchItem.getActionView();
            if (searchView != null) {
                searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Fragment fragment = mPagerAdapter.getItem(mViewPager.getCurrentItem());
                        if (fragment instanceof SearchView.OnQueryTextListener) {
                            ((SearchView.OnQueryTextListener) fragment).onQueryTextSubmit(query);
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        Fragment fragment = mPagerAdapter.getItem(mViewPager.getCurrentItem());
                        if (fragment instanceof SearchView.OnQueryTextListener) {
                            ((SearchView.OnQueryTextListener) fragment).onQueryTextChange(newText);
                        }
                        return false;
                    }
                });
            }
        }
        return true;
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        private int[] titles = new int[]{R.string.tab_title_android, R.string.tab_title_ios
                , R.string.tab_title_front};
        private Fragment[] mFragments = new Fragment[3];

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                if (mFragments[0] == null) {
                    mFragments[0] = GankFragment.newInstance(GankRequest.TYPE_ANDROID);
                }
                return mFragments[0];
            }
            if (position == 1) {
                if (mFragments[1] == null) {
                    mFragments[1] = GankFragment.newInstance(GankRequest.TYPE_IOS);
                }
                return mFragments[1];
            }
            if (position == 2) {
                if (mFragments[2] == null) {
                    mFragments[2] = GankFragment.newInstance(GankRequest.TYPE_FRONT);
                }
                return mFragments[2];
            }
            throw new IllegalArgumentException("错误的Position，position = " + position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(titles[position]);
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
}
