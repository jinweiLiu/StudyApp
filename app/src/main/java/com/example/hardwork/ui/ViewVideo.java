package com.example.hardwork.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.hardwork.R;

public class ViewVideo extends AppCompatActivity {
    TextView title;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        title = (TextView)findViewById(R.id.video_title);
        videoView = (VideoView)findViewById(R.id.main_video);

        Intent intent = getIntent();
        Bundle data=intent.getExtras();
        String get_title = data.getString("title");
        String get_content = data.getString("content");
        String get_date = data.getString("date");

        title.setText(get_title+" "+get_date);
        MediaController controller = new MediaController(this);//实例化控制器
        videoView.setVideoURI(Uri.parse(get_content));
        //将控制器和播放器进行互相关联
        controller.setMediaPlayer(videoView);
        videoView.setMediaController(controller);
    }
}
