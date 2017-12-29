package com.github.ivanmaria.attendanceassistant;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceLectureFragment extends Fragment {
    SearchableSpinner SpinnerSubject;
    SearchableSpinner SpinnerClass;
    AppCompatButton btn;
    RadioGroup radio;
    RadioButton rb1, rb2, rb3;
    String temp = "null";
    int subCount = 0, classCount = 0;
    public AttendanceLectureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_attendance_lecture, container, false);
        final View x = v;
        btn = v.findViewById(R.id.takelec);
        radio = v.findViewById(R.id.radio);
        RadioButton rb1 = v.findViewById(R.id.radioButton);
        RadioButton rb2 = v.findViewById(R.id.radioButton2);
        final RadioButton rb3 = v.findViewById(R.id.radioButton3);

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


        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = x.findViewById(checkedId);
                temp = rb.getText().toString();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SpinnerClass.getSelectedItem() == null) {
                    TextView errorText = (TextView) SpinnerClass.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);
                }
                if (SpinnerSubject.getSelectedItem() == null) {
                    TextView errorText = (TextView) SpinnerSubject.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);
                }
                if (radio.getCheckedRadioButtonId() == -1) {
                    rb3.setError("");
                } else
                    rb3.setError(null);

                if (SpinnerClass.getSelectedItem() != null && SpinnerSubject.getSelectedItem() != null && radio.getCheckedRadioButtonId() != -1) {
                    String subject = SpinnerSubject.getSelectedItem().toString();
                    String classroom = SpinnerClass.getSelectedItem().toString();

                    Toast.makeText(getContext(), "Sub: " + subject + "\nClass: " + classroom + "\nLec: " + temp, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

}
