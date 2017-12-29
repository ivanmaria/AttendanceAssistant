package com.github.ivanmaria.attendanceassistant;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceLectureFragment extends Fragment {
    SearchableSpinner SpinnerSubject;
    SearchableSpinner SpinnerClass;
    int subCount = 0, classCount = 0;
    public AttendanceLectureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_attendance_lecture, container, false);

        SavePref pref = new SavePref(getContext());
        subCount = pref.getInt("subnum");
        classCount = pref.getInt("classnum");

        String[] SPINNER_SUB = new String[subCount];
        String[] SPINNER_CLASS = new String[classCount];

        for (int i = 1; i <= subCount; i++)
            SPINNER_SUB[i - 1] = pref.getVal("sub" + i);

        for (int i = 1; i <= classCount; i++)
            SPINNER_CLASS[i - 1] = pref.getVal("class" + i);


        SpinnerSubject = v.findViewById(R.id.selSub);

        SpinnerSubject.setTitle("Select Subject");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_dropdown_item_1line, SPINNER_SUB);

        SpinnerSubject.setAdapter(adapter1);

        SpinnerClass = v.findViewById(R.id.selClass);

        SpinnerClass.setTitle("Select Class");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_dropdown_item_1line, SPINNER_CLASS);

        SpinnerClass.setAdapter(adapter2);


        return v;
    }

}
