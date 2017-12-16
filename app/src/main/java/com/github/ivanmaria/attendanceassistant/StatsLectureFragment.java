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
public class StatsLectureFragment extends Fragment {
    public static String[] subList = new String[25];
    public static int[] percent = new int[25];
    public int subCount = 0;
    ListView lv;
    Context context;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public StatsLectureFragment() {
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
        View v = inflater.inflate(R.layout.fragment_stats_lecture, container, false);
        context=getContext();
        SavePref pref = new SavePref(context);
        subCount = pref.getInt("subnum");
        for (int i = 1; i <= subCount; i++) {
            subList[i - 1] = pref.getVal("sub" + i);
            try {
                float x = (float) pref.getInt("sub" + i + "_present");
                float y = (float) pref.getInt("sub" + i + "_total");
                percent[i - 1] = (int) (x / y * 100);
            } catch (Exception e) {
                percent[i - 1] = 10;
            }
        }

        MainActivity main = (MainActivity) getActivity();
        lv = v.findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(main, subCount, subList, percent));
        mSwipeRefreshLayout = v.findViewById(R.id.swipeToRefresh);
        setListViewHeightBasedOnChildren(lv);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SavePref pref = new SavePref(getContext());
                GetSubject get = new GetSubject();
                get.getSubject(getContext(), pref.getVal("id"));
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        return v;
    }
}
