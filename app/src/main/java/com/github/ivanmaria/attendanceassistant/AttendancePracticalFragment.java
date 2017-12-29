package com.github.ivanmaria.attendanceassistant;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendancePracticalFragment extends Fragment {
    SearchableSpinner SpinnerSubject;
    int pracCount = 0;
    AppCompatButton button;
    public AttendancePracticalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_attendance_practical, container, false);

        SavePref pref = new SavePref(getContext());
        pracCount = pref.getInt("pracnum");
        String[] SPINNER_PRAC = new String[pracCount];
        for (int i = 1; i <= pracCount; i++)
            SPINNER_PRAC[i - 1] = pref.getVal("prac" + i);

        button = v.findViewById(R.id.takeprac);

        SpinnerSubject = v.findViewById(R.id.selSub);

        SpinnerSubject.setTitle("Select Subject");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_dropdown_item_1line, SPINNER_PRAC);

        SpinnerSubject.setAdapter(adapter1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SpinnerSubject.getSelectedItem() == null) {
                    TextView errorText = (TextView) SpinnerSubject.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                }
                if (SpinnerSubject.getSelectedItem() != null) {
                    String subject = SpinnerSubject.getSelectedItem().toString();
                    Toast.makeText(getContext(), "Sub: " + subject, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

}
