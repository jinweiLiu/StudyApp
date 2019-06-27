package com.example.hardwork.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hardwork.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {

    EditText name,passwd;
    Button login;
    String result = null;
    String user = null;
    String pass = null;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.input_username);
        passwd = findViewById(R.id.input_password);
        login = findViewById(R.id.btn_login);

        handler = new Handler() {
            public void handleMessage(Message msg)
            {
                Bundle data = msg.getData();
                //从data中拿出存的数据
                String val = data.getString("userpass");
                if(val.equals(pass)){
                    Intent intent = new Intent(getApplication(),MainActivity.class);
                    intent.putExtra("name",user);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplication(),"密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = name.getText().toString();
                pass = passwd.getText().toString();
                String url = "http://129.204.21.235:8080/app-Test/login/"+user;
                SendByHttpClient(url);
            }
        });
    }

    public void SendByHttpClient(final String urlPath){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result = readParse(urlPath);
                    Message msg = Message.obtain();
                    Bundle response = new Bundle();
                    JsonObject jsonObj = (JsonObject)new JsonParser().parse(result);
                    String getpass = jsonObj.get("userpass").getAsString();
                    response.putString("userpass", getpass);
                    msg.setData(response);
                    handler.sendMessage(msg);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static String readParse(String urlPath) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inStream = conn.getInputStream();
        while ((len = inStream.read(data)) != -1) {
            outStream.write(data, 0, len);
        }
        inStream.close();
        return new String(outStream.toByteArray());//通过out.Stream.toByteArray获取到写的数据
    }
}
