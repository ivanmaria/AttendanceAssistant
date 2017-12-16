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
public class StatsLectureFragment extends Fragment {
    public static String[] subList = new String[25];
    public static String[] hours = new String[25];
    public static int[] percent = new int[25];
    public int subCount = 0;
    ListView lv;
    Context context;
    SwipeRefreshLayout mSwipeRefreshLayout;
    float height;

    public StatsLectureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stats_lecture, container, false);
        context=getContext();
        SavePref pref = new SavePref(context);
        subCount = pref.getInt("subnum");
        for (int i = 1; i <= subCount; i++)
            subList[i - 1] = pref.getVal("sub" + i);

        MainActivity main = (MainActivity) getActivity();
        lv = v.findViewById(R.id.listView);
        height = subCount * 60 * getContext().getResources().getDisplayMetrics().density;
        lv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int)height));
        lv.setAdapter(new CustomAdapter(main, subCount, subList, hours, percent));
        mSwipeRefreshLayout = v.findViewById(R.id.swipeToRefresh);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        return v;
    }

}
