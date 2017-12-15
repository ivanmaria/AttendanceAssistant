package com.github.ivanmaria.attendanceassistant;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatsFragment extends Fragment {
    ListView lv, lvp;
    Context context;
    public static String [] subList={"CN","OS","MP","SOOAD"};
    public static String [] hours={"18/25","16/18","13/20","17/18"};
    public static int [] percent={75,80,65,95};

    public static String [] subListP={"CN","OS","MP","SOOAD","WT"};
    public static String [] hoursP={"4/8","8/10","7/10","3/10","10/10"};
    public static int [] percentP={50,80,70,30,100};

    public StatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stats, container, false);
        context=getContext();
        MainActivity main = (MainActivity) getActivity();
        lv=(ListView) v.findViewById(R.id.listView);
        lvp=(ListView) v.findViewById(R.id.ListViewP);
        lv.setAdapter(new CustomAdapter(main, subList,hours,percent));
        lvp.setAdapter(new CustomAdapter(main, subListP,hoursP,percentP));

    return v;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Statistics");
    }
}
