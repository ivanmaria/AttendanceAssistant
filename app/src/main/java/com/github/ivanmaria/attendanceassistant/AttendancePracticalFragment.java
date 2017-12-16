package com.github.ivanmaria.attendanceassistant;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendancePracticalFragment extends Fragment {
    MaterialBetterSpinner SpinnerSubject ;
    MaterialBetterSpinner SpinnerClass ;
    String[] SPINNER_SUB = {"OS","MP","CN","SOOAD"};
    String[] SPINNER_CLASS = {"cr1","cr2","cr3","cr4"};
    EditText min, max;
    AppCompatButton button;
    public AttendancePracticalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_attendance_practical, container, false);

        min = (EditText) v.findViewById(R.id.minroll);
        max = (EditText) v.findViewById(R.id.maxroll);
        button = (AppCompatButton) v.findViewById(R.id.takebtn);

        SpinnerSubject = (MaterialBetterSpinner)v.findViewById(R.id.selSub);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_dropdown_item_1line, SPINNER_SUB);

        SpinnerSubject.setAdapter(adapter1);

        SpinnerClass = (MaterialBetterSpinner)v.findViewById(R.id.selClass);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_dropdown_item_1line, SPINNER_CLASS);

        SpinnerClass.setAdapter(adapter2);

        return v;
    }

}
