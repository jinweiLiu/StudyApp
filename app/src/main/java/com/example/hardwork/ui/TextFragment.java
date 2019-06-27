package com.example.hardwork.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hardwork.Bean.Study;
import com.example.hardwork.R;
import com.example.hardwork.adapter.MyCommendAdapter;
import com.example.hardwork.database.DataGet;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextFragment extends Fragment {

    public ListView mListView;
    View v;
    MyCommendAdapter mAdapter;
    List<Study> mList;
    String result = null;
    Handler handler;

    public TextFragment() {
        // Required empty public constructor
    }

    public static TextFragment  newInstance(){
        TextFragment tf = new TextFragment();
        return tf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_text, container, false);
        mListView = (ListView) v.findViewById(R.id.textList);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                List<Map<String,Object>> listitem = (List<Map<String, Object>>) bundle.getSerializable("list");
                for(int i=0;i<listitem.size();i++){
                    mList.add((Study)listitem.get(i));
                }
                //Log.d("测试",mList.size()+"");
                initAdapter();
            }
        };
        SendByHttpClient("http://129.204.21.235:8080/app-Test/study");
        initData();
        //Log.d("测试",mList.size()+"");
        initAdapter();
        return v;
    }

    private void initData() {
        mList = new ArrayList<>();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(getContext(), StudyView.class);
                Bundle bundle=new Bundle();;
                bundle.putString("title", mList.get(i).getTitle());
                bundle.putString("content", mList.get(i).getContent());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    private void initAdapter() {
        //传两个参数过去 1、上下文 2、集合
        mAdapter = new MyCommendAdapter(getActivity(), mList);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }


    public void SendByHttpClient(final String urlPath){
        final ArrayList<Study> list = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result = new DataGet().readParse(urlPath);
                    JSONArray jsonArray = new JSONArray(result);
                    for(int i=0;i<jsonArray.length();i++){
                        Study s = new Study();
                        s.setTitle(jsonArray.getJSONObject(i).get("title").toString());
                        s.setAuthor(jsonArray.getJSONObject(i).get("author").toString());
                        s.setContent(jsonArray.getJSONObject(i).get("content").toString());
                        s.setDate(jsonArray.getJSONObject(i).get("date").toString());
                        list.add(s);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("list",list);
                Message message=new Message();
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }).start();
    }

}
