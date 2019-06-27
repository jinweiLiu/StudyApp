package com.example.hardwork.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.MediaController;

import com.example.hardwork.Bean.Study;
import com.example.hardwork.R;
import com.example.hardwork.adapter.MyCommendAdapter;
import com.example.hardwork.adapter.MyVideoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {

    public ListView mListView;
    View v;
    MyVideoAdapter mAdapter;
    List<Study> mList;

    public VideoFragment() {
        // Required empty public constructor
    }

    public static VideoFragment  newInstance(){
        VideoFragment vf = new VideoFragment();
        return vf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_video, container, false);
        mListView = (ListView) v.findViewById(R.id.videoList);
        initData();
        initAdapter();
        return v;
    }

    private void initData() {
        mList = new ArrayList<>();
        Study study = new Study();
        study.setTitle("视频");
        study.setDate("2019-6-25");
        study.setContent("http://129.204.21.235:8080/videos/class.mp4");
        study.setAuthor("video");
        mList.add(study);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(getContext(), ViewVideo.class);
                Bundle bundle=new Bundle();;
                bundle.putString("title", mList.get(i).getTitle());
                bundle.putString("content", mList.get(i).getContent());
                bundle.putString("date",mList.get(i).getDate());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    private void initAdapter() {
        //传两个参数过去 1、上下文 2、集合
        mAdapter = new MyVideoAdapter(getActivity(), mList);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

}
