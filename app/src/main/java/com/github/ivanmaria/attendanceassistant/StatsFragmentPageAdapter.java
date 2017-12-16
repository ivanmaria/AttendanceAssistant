package com.github.ivanmaria.attendanceassistant;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class StatsFragmentPageAdapter extends FragmentPagerAdapter{
    private static final int FRAGMENT_COUNT = 2;
    public StatsFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new StatsLectureFragment();
            case 1:
                return new StatsPracticalFragment();
        }
        return null;
    }
    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Lecture";
            case 1:
                return "Practical";
        }
        return null;
    }
}