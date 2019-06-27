package com.example.hardwork.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hardwork.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment implements View.OnClickListener{

    private FragmentManager fragmentManager;
    private RelativeLayout fragment_content;
    private TextView videoStudy,textStudy;
    private LinearLayout aitem1,aitem2;
    private TextView[] tvs;
    View v;
    private VideoFragment vf;
    private TextFragment tf;

    public FragmentA() {
        // Required empty public constructor
    }

    public static FragmentA  newInstance(){
        FragmentA fa = new FragmentA();
        return fa;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_, null);
        initView();
        initListener();
        return v;
    }

    private void initListener() {
        aitem1.setOnClickListener(this);
        aitem2.setOnClickListener(this);
    }

    private void initView() {
        fragmentManager = getChildFragmentManager();
        fragment_content = (RelativeLayout) v.findViewById(R.id.fragment_content);
        textStudy = (TextView) v.findViewById(R.id.textStudy);
        aitem1 = (LinearLayout) v.findViewById(R.id.aitem1);
        videoStudy = (TextView) v.findViewById(R.id.videoStudy);
        aitem2 = (LinearLayout) v.findViewById(R.id.aitem2);
        tvs = new TextView[]{textStudy,videoStudy};
        ShowText();
        setCheck(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.aitem1: {
                ShowText();
                setCheck(0);//自定义方法
                break;
            }
            case R.id.aitem2: {
                ShowVideo();
                setCheck(1);
                break;
            }
            default:break;
        }
    }

    private void ShowText(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(tf == null){
            tf = TextFragment.newInstance();
            transaction.add(R.id.fragment_content,tf);
        }

        hideAllFragement();
        transaction.show(tf);
        transaction.commit();

    }

    private void ShowVideo(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(vf == null){
            vf = VideoFragment.newInstance();
            transaction.add(R.id.fragment_content,vf);
        }

        hideAllFragement();
        transaction.show(vf);
        transaction.commit();

    }

    public void hideAllFragement(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(tf != null){
            transaction.hide(tf);
        }
        if(vf != null){
            transaction.hide(vf);
        }
        transaction.commit();
    }

    public void setCheck(int itemId){
        //这个方法设置顶部导航栏选中时的效果
        for (int i = 0; i < 2; i++) {
            tvs[i].setTextColor(Color.parseColor("#0f0f0f"));
        }
        tvs[itemId].setTextColor(Color.BLUE);
    }

}

