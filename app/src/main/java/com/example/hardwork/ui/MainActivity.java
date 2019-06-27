package com.example.hardwork.ui;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hardwork.R;
import com.example.hardwork.database.DataGet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager fragmentManager;
    private RelativeLayout rl_content;
    private TextView item1_tv,item2_tv,item3_tv;
    private LinearLayout item1,item2,item3;
    private TextView[] tvs;
    private FragmentA fa ;
    private FragmentB fb;
    private FragmentC fc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void initListener() {
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
    }

    private void initView() {
        rl_content = (RelativeLayout) findViewById(R.id.rl_content);
        item1_tv = (TextView) findViewById(R.id.item1_tv);
        item1 = (LinearLayout) findViewById(R.id.item1);
        item2_tv = (TextView) findViewById(R.id.item2_tv);
        item2 = (LinearLayout) findViewById(R.id.item2);
        item3_tv = (TextView) findViewById(R.id.item3_tv);
        item3 = (LinearLayout) findViewById(R.id.item3);
        tvs = new TextView[]{item1_tv,item2_tv,item3_tv};
        ShowFragmentA();
        setCheck(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item1: {
                ShowFragmentA();
                setCheck(0);//自定义方法
                break;
            }
            case R.id.item2: {
                ShowFragmentB();
                setCheck(1);
                break;
            }
            case R.id.item3: {
                ShowFragmentC();
                setCheck(2);
                break;
            }
            default:break;
        }
    }

    private void ShowFragmentA(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(fa == null){
            fa = FragmentA.newInstance();
            transaction.add(R.id.rl_content,fa);
        }

        hideAllFragement();
        transaction.show(fa);
        transaction.commit();

    }

    private void ShowFragmentB(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(fb == null){
            fb = FragmentB.newInstance();
            transaction.add(R.id.rl_content,fb);
        }

        hideAllFragement();
        transaction.show(fb);
        transaction.commit();

    }

    private void ShowFragmentC(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(fc == null){
            fc = FragmentC.newInstance();
            transaction.add(R.id.rl_content,fc);
        }

        hideAllFragement();
        transaction.show(fc);
        transaction.commit();

    }

    public void hideAllFragement(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(fa != null){
            transaction.hide(fa);
        }
        if(fb != null){
            transaction.hide(fb);
        }
        if(fc != null){
            transaction.hide(fc);
        }
        transaction.commit();
    }

    public void setCheck(int itemId){
        //这个方法设置底部导航栏选中时的效果
        for (int i = 0; i < 3; i++) {
            tvs[i].setTextColor(Color.parseColor("#0f0f0f"));
        }
        tvs[itemId].setTextColor(Color.BLUE);
    }
}