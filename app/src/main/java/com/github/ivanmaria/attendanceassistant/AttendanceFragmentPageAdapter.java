package com.github.ivanmaria.attendanceassistant;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
public class AttendanceFragmentPageAdapter extends FragmentPagerAdapter{
    private static final int FRAGMENT_COUNT = 2;
    public AttendanceFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AttendanceLectureFragment();
            case 1:
                return new AttendancePracticalFragment();
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