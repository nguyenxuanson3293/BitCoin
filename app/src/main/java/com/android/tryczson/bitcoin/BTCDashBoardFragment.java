package com.android.tryczson.bitcoin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tryczson.bitcoin.Customview.NoSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

public class BTCDashBoardFragment extends Fragment {

    private ViewPagerAdapter mMainAdapter;
    private NoSwipeViewPager mViewpager;
    private TabLayout mTab;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dash_board, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewpager = (NoSwipeViewPager) view.findViewById(R.id.vp_page2);
        mTab = (TabLayout) view.findViewById(R.id.tablayout2);

        mMainAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mMainAdapter.addFragment(new BTCChartFragment(), "1H");
        mMainAdapter.addFragment(new BTCChartFragment(), "1D");
        mMainAdapter.addFragment(new BTCChartFragment(), "1W");
        mMainAdapter.addFragment(new BTCChartFragment(), "1M");
        mMainAdapter.addFragment(new BTCChartFragment(), "1Y");
        mMainAdapter.addFragment(new BTCChartFragment(), "ALL");
        mViewpager.setAllowedSwipeDirection(NoSwipeViewPager.SwipeDirection.none);
        mViewpager.setOffscreenPageLimit(1);
        mViewpager.setAdapter(mMainAdapter);
        mViewpager.setCurrentItem(2);
        mTab.setupWithViewPager(mViewpager);
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
