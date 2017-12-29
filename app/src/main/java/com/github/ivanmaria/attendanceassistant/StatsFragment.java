package com.github.ivanmaria.attendanceassistant;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatsFragment extends Fragment {
    SwipeRefreshLayout mSwipeRefreshLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public StatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stats, container, false);

        tabLayout = v.findViewById(R.id.tabs_stats);
        viewPager = v.findViewById(R.id.view_pager_stats);

        viewPager.setAdapter(new StatsFragmentPageAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        mSwipeRefreshLayout = v.findViewById(R.id.swipeDown);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SavePref pref = new SavePref(getContext());
                GetSubject get = new GetSubject();
                get.getSubject(getContext(), pref.getVal("id"));

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        viewPager.setAdapter(new StatsFragmentPageAdapter(getChildFragmentManager()));
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    return v;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Statistics");
    }
}
