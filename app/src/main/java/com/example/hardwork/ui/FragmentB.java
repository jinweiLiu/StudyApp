package com.example.hardwork.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hardwork.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {

    View v;
    Button testButton,exButton;

    public FragmentB() {
        // Required empty public constructor
    }

    public static  FragmentB newInstance(){
        FragmentB fb = new FragmentB();
        return fb;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment_b, container, false);
        testButton = (Button)v.findViewById(R.id.test);
        exButton =(Button)v.findViewById(R.id.exercise);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TestActivity.class);
                startActivity(intent);
            }
        });
        exButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExerciseActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

}