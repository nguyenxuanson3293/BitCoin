package com.android.tryczson.bitcoin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.tryczson.bitcoin.Customview.NoSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPagerAdapter mMainAdapter;
    private NoSwipeViewPager mViewpager;
    private TabLayout mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dashboard");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mViewpager = (NoSwipeViewPager) findViewById(R.id.vp_page);
        mTab = (TabLayout) findViewById(R.id.tablayout);

        mMainAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mMainAdapter.addFragment(new BTCDashBoardFragment(), "BTC");
        mMainAdapter.addFragment(new ETHDashBoardFragment(), "ETH");
        mMainAdapter.addFragment(new LTCDashBoardFragment(), "LTC");
        mViewpager.setAllowedSwipeDirection(NoSwipeViewPager.SwipeDirection.none);
        mViewpager.setOffscreenPageLimit(1);
        mViewpager.setAdapter(mMainAdapter);
        mViewpager.setCurrentItem(0);
        mTab.setupWithViewPager(mViewpager);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.notification) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.wallet1) {
            Intent intent = new Intent(this, PersonalActivity.class);
            intent.putExtra("type", "btc");
            startActivity(intent);
        } else if (id == R.id.wallet2) {
            Intent intent = new Intent(this, PersonalActivity.class);
            intent.putExtra("type", "ltc");
            startActivity(intent);
        } else if (id == R.id.wallet3) {
            Intent intent = new Intent(this, PersonalActivity.class);
            intent.putExtra("type", "eth");
            startActivity(intent);
        } else if (id == R.id.buy) {
            int i = mViewpager.getCurrentItem();
            String type;
            if (i == 0)
                type = "btc";
            else if (i == 1)
                type = "eth";
            else type = "ltc";
            Intent intent = new Intent(this, BuyActivity.class);
            intent.putExtra("type", type);
            startActivity(intent);
        } else if (id == R.id.sell) {
            int i = mViewpager.getCurrentItem();
            String type;
            if (i == 0)
                type = "btc";
            else if (i == 1)
                type = "eth";
            else type = "ltc";
            Intent intent = new Intent(this, SellActivity.class);
            intent.putExtra("type", type);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
