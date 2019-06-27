package com.example.hardwork.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hardwork.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentC extends Fragment {

    TextView login;
    Button info,exit;
    View v;
    boolean flag = false;

    public FragmentC() {
        // Required empty public constructor
    }

    public static FragmentC newInstance(){
        FragmentC fc = new FragmentC();
        return fc;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment_c, container, false);
        login = v.findViewById(R.id.user_name);
        info = v.findViewById(R.id.usr_info);
        exit = v.findViewById(R.id.exit);

        Intent intent = getActivity().getIntent();
        String name = intent.getStringExtra("name");
        if(name!=null){
            login.setText(name);
            flag = true;
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag){
                    startActivity(new Intent(getActivity(),Login.class));
                }
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    startActivity(new Intent(getActivity(),PersonActivity.class));
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag =false;
                login.setText("登录/注册");
            }
        });
        return v;
    }

}
