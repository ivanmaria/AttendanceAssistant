package com.github.ivanmaria.attendanceassistant;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatsPracticalFragment extends Fragment {
    public static String[] pracList = new String[25];
    public static String[] hours = new String[25];
    public static int[] percent = new int[25];
    public int pracCount = 0;
    ListView lv;
    Context context;
    SwipeRefreshLayout mSwipeRefreshLayout;
    float height;


    public StatsPracticalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_stats_practical, container, false);
        context=getContext();
        SavePref pref = new SavePref(context);
        pracCount = pref.getInt("pracnum");
        for (int i = 1; i <= pracCount; i++)
            pracList[i - 1] = pref.getVal("prac" + i);
        MainActivity main = (MainActivity) getActivity();
        lv = v.findViewById(R.id.listViewPrac);
        height = pracCount * 60 * getContext().getResources().getDisplayMetrics().density;
        lv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int)height));
        lv.setAdapter(new CustomAdapter(main, pracCount, pracList, hours, percent));
        mSwipeRefreshLayout = v.findViewById(R.id.swipeToRefreshPrac);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        return v;
    }

}
