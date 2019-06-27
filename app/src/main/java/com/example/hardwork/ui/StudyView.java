package com.example.hardwork.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.hardwork.R;

public class StudyView extends AppCompatActivity {
    TextView title;
    TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_view);
        title = (TextView)findViewById(R.id.item_title);
        content = (TextView)findViewById(R.id.item_content);
        Intent intent = getIntent();
        Bundle data=intent.getExtras();
        String get_title = data.getString("title");
        String get_content = data.getString("content");
        title.setText(get_title);
        content.setText(get_content);
    }
}
