package com.example.hardwork.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hardwork.R;

public class PersonActivity extends AppCompatActivity {

    EditText name,email,age,career,graduate;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        name = findViewById(R.id.usr_name);
        email = findViewById(R.id.usr_email);
        age = findViewById(R.id.usr_age);
        career = findViewById(R.id.usr_career);
        graduate = findViewById(R.id.usr_graduate);
        save = findViewById(R.id.btnSave);
        name.setText("我爱学习");
        email.setText("jwliu0213@163.com");
        age.setText("20");
        career.setText("自由职业");
        graduate.setText("本科");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
