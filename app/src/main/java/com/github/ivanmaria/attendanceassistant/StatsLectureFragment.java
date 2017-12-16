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
    ListView lv;
    Context context;
    SwipeRefreshLayout mSwipeRefreshLayout;
    public int subCount=4;
    float height;
    public static String [] subList={"CN","OS","MP","SOOAD"};
    public static String [] hours={"18/25","16/18","13/20","17/18"};
    public static int [] percent={75,80,65,95};

    public StatsLectureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stats_lecture, container, false);
        context=getContext();
        MainActivity main = (MainActivity) getActivity();
        lv=(ListView) v.findViewById(R.id.listView);
        height = subCount * 60 * getContext().getResources().getDisplayMetrics().density;
        lv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int)height));
        lv.setAdapter(new CustomAdapter(main, subList,hours,percent));
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeToRefresh);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        return v;
    }

}
