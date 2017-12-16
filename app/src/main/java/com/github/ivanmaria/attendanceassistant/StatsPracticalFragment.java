package com.github.ivanmaria.attendanceassistant;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatsPracticalFragment extends Fragment {
    public static String[] pracList = new String[25];
    public static int[] percent = new int[25];
    public int pracCount = 0;
    ListView lv;
    Context context;
    SwipeRefreshLayout mSwipeRefreshLayout;


    public StatsPracticalFragment() {
        // Required empty public constructor
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight() + 15;
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
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
        lv.setAdapter(new CustomAdapter(main, pracCount, pracList, percent));
        setListViewHeightBasedOnChildren(lv);
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
